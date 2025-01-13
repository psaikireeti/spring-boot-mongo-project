package com.kkindustry.alpha.controller;

import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController(value = "/user")
public class UserController {
  UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") String id) {
    return ResponseEntity.ok(userService.getUser(id));
  }
}
