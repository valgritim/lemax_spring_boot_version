package com.fan_de_lemax.lemax.jwt;

import com.fan_de_lemax.lemax.forms.LoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
   @Autowired
   private final AuthenticationManager authenticationManager;

   private String secretKey = "securesecuresecuresecuresecuresecuresecure";

   public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager) {
      this.authenticationManager = authenticationManager;
   }

   @Override
   public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       try {
          //Je mappe le corps de la requete en login form
            LoginForm authenticationRequest = new ObjectMapper()
                .readValue(request.getInputStream(), LoginForm.class);
            //Je prépare l'authentification
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
            );
            final Authentication finalAuthenticate = authenticationManager.authenticate(authentication);
            return finalAuthenticate;

         } catch (IOException e) {
            throw  new RuntimeException(e);
         }
   }

   /**
    * Si l'authentification est un succès, la méthode suivante sera exécutée
    * @param request
    * @param response
    * @param chain
    * @param authResult
    * @throws IOException
    * @throws ServletException
    */
   @Override
   protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain, Authentication authResult) throws IOException, ServletException {

      final String token = Jwts.builder()
          .setSubject(authResult.getName())
          .claim("authorities", authResult.getAuthorities())
          .setIssuedAt(new Date())
          .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
          .signWith(SignatureAlgorithm.HS256, secretKey)
          .compact();

      response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+ token);
   }
}
