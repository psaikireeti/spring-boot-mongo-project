package com.kkindustry.alpha.controller;

import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<List<User>> getUser(HttpServletResponse response) {
    return ResponseEntity.ok(userService.getAllUser(response));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") String id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteUserById(@PathVariable("id") String id) {
    userService.deleteUser(id);
    return ResponseEntity.ok("User deleted Successfully");
  }

  @DeleteMapping
  public ResponseEntity<String> bulkDeleteUsers(@RequestBody List<String> ids) {

    userService.bulkDeleteUsers(ids);
    return ResponseEntity.ok("Users deleted Successfully");
  }
}
