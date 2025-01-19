package com.kkindustry.alpha.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

  @GetMapping("/home")
  public void redirectBasedOnRole(Authentication authentication, HttpServletResponse response)
      throws IOException {
    for (GrantedAuthority authority : authentication.getAuthorities()) {
      String role = authority.getAuthority();
      switch (role) {
        case "ROLE_USER":
          response.sendRedirect("/alpha/admin-home.html"); // Redirect to admin page
          return;
        case "ROLE_RECEPTIONIST":
          response.sendRedirect("/alpha/receptionist"); // Redirect to receptionist page
          return;
        case "ROLE_DOCTOR":
          response.sendRedirect("/alpha/doctor"); // Redirect to doctor page
          return;
        case "ROLE_PATIENT":
          response.sendRedirect("/alpha/patient"); // Redirect to patient page
          return;
        case "ROLE_MEDICAL_STORE":
          response.sendRedirect("alpha/medical-store"); // Redirect to medical store page
          return;
      }
    }
    response.sendRedirect("/"); // Fallback if no role matches
  }
}
