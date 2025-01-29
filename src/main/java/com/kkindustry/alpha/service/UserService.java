package com.kkindustry.alpha.service;

import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.repository.UserRepository;
import com.kkindustry.alpha.util.Utils;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  UserRepository userRepository;
  PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User saveUser(User user) {
    if (user.getId().isEmpty()) {
      user.setId(Utils.generateUUID());
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public Optional<User> getUserById(String id) {
    return userRepository.findById(id);
  }

  public List<User> getAllUser(HttpServletResponse response) {
    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    return userRepository.findAll();
  }

  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }

  public void bulkDeleteUsers(List<String> ids) {
    userRepository.deleteAllById(ids);
  }
}
