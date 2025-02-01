package com.kkindustry.alpha.security;

import com.kkindustry.alpha.enums.RoleEnum;
import com.kkindustry.alpha.util.RoleUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authentication)
      throws IOException, ServletException {
    AuthenticationSuccessHandler.super.onAuthenticationSuccess(
        request, response, chain, authentication);
  }

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {

    String REDIRECT_ENDPOINT = "/home.html";

    RoleEnum role = RoleUtil.getUserRole();
    switch (role) {
      case ROLE_ADMIN -> REDIRECT_ENDPOINT = "/admin-home.html";
      case ROLE_DOCTOR -> REDIRECT_ENDPOINT = "/doctor-home.html";
      case ROLE_RECEPTIONIST -> REDIRECT_ENDPOINT = "/receptionist-home.html";
      case ROLE_PHARMACIST -> REDIRECT_ENDPOINT = "/pharmacy-home.html";
    }
    response.sendRedirect(request.getContextPath() + REDIRECT_ENDPOINT);
  }
}
