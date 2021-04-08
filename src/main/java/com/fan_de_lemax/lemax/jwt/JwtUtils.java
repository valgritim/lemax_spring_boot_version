package com.fan_de_lemax.lemax.jwt;

import com.fan_de_lemax.lemax.auth.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.LocalDate;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

   private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

   @Value("${lemax.app.jwtSecret}")
   private String jwtSecret;


   public String generateJwtToken(Authentication authentication){

      UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

      return Jwts.builder()
          .setSubject(userPrincipal.getUsername())
          .setIssuedAt(new Date())
          .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
          .signWith(SignatureAlgorithm.HS256, jwtSecret)
          .compact();
   }

   public String getUserNameFromJwtToken(String token){
      return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject(); //Subject = username
   }

   public boolean validateJwtToken(String authToken) {
      try {
         Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
         return true;

      } catch (SignatureException e) {
         logger.error("Invalid JWT signature: {}" + e.getMessage());
      } catch (MalformedJwtException j){
         logger.error("Invalid JWT Token: {}" + j.getMessage());
      } catch(ExpiredJwtException k){
         logger.error("Expired JWT Token: {}" + k.getMessage());
      }catch (UnsupportedJwtException l) {
         logger.error("JWT token is unsupported: {}", l.getMessage());
      } catch (IllegalArgumentException m) {
         logger.error("JWT claims string is empty: {}", m.getMessage());
      }
      return false;
   }
}
