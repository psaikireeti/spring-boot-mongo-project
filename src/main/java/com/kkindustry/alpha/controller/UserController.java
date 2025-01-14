package com.kkindustry.alpha.controller;

import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
  UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.saveUser(user));
  }

  @GetMapping
  public ResponseEntity<List<User>> getUser() {
    return ResponseEntity.ok(userService.getAllUser());
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteUserById(@PathVariable("id") String id){
    userService.deleteUser(id);
    return ResponseEntity.ok("User deleted Successfully");
  }
}
