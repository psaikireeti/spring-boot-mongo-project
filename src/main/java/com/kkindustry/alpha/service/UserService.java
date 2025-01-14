package com.kkindustry.alpha.service;

import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.repository.UserRepository;
import com.kkindustry.alpha.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User saveUser(User user) {
    if(user.getId().isEmpty()){
      user.setId(Utils.generateUUID());
    }
    return userRepository.save(user);
  }

  public Optional<User> getUserById(String id){
    return userRepository.findById(id);
  }

  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  public void deleteUser(String id){
     userRepository.deleteById(id);
  }

  public void bulkDeleteUsers(List<String> ids){
    userRepository.deleteAllById(ids);
  }
}
