package com.kkindustry.alpha.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

  @GetMapping("/home")
  public void redirectBasedOnRole(Authentication authentication, HttpServletResponse response)
      throws IOException {
    if (authentication == null || !authentication.isAuthenticated()) {
      response.sendRedirect("/login.html");
      return;
    }

    String role = authentication.getAuthorities().iterator().next().getAuthority();
    String redirectPath = "/home.html";

    switch (role) {
      case "ROLE_ADMIN":
        redirectPath = "/admin-home.html";
        break;
      case "ROLE_DOCTOR":
        redirectPath = "/doctor-home.html";
        break;
      case "ROLE_RECEPTIONIST":
        redirectPath = "/receptionist-home.html";
        break;
      case "ROLE_PHARMACIST":
        redirectPath = "/pharmacy-home.html";
        break;
      case "ROLE_PATIENT":
        redirectPath = "/patient-home.html";
        break;
      default:
        redirectPath = "/home.html";
    }

    response.sendRedirect(redirectPath);
  }

  @GetMapping("/interview")
  public ResponseEntity<List<Object>> getData() {
    RestTemplate template = new RestTemplate();
    String Url = "http://interview.surya-digital.in/get-electronics";
    ResponseEntity<List<Object>> res =
        (ResponseEntity<List<Object>>) template.getForObject(Url, List.class);
    List<Object> data = res.getBody();
    return ResponseEntity.ok(data);
  }
}
