package com.kkindustry.alpha.service;

import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUser(String id) {
    return userRepository.getUserDetail(id);
  }
}
