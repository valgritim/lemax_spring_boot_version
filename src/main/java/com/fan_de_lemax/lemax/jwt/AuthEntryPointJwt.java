package com.fan_de_lemax.lemax.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

   private static Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

   @Override
   public void commence(HttpServletRequest request,HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
      logger.error("Unauthorized error: ", e.getMessage());

      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: unauthorized");
   }
}
