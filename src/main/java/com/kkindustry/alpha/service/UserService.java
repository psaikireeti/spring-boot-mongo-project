package com.kkindustry.alpha.service;

import com.kkindustry.alpha.constant.SecurityConstants;
import com.kkindustry.alpha.constant.StringConstants;
import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.enums.RoleEnum;
import com.kkindustry.alpha.repository.UserRepository;
import com.kkindustry.alpha.service.notification.EmailService;
import com.kkindustry.alpha.util.Regex;
import com.kkindustry.alpha.util.RoleUtil;
import com.kkindustry.alpha.util.Utils;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private final EmailService emailService;

  @Autowired
  public UserService(
      UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.emailService = emailService;
  }

  public String saveUser(User user) {
    try {
      if (user == null) {
        return "user cannot be empty";
      }

      List<String> error = validateUser(user);
      if (!error.isEmpty()) {
        return error.get(0);
      }

      if (user.getId().isEmpty()) {
        user.setId(Utils.generateUUID());
      }
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      if (RoleUtil.checkAdminAccess()) {
        userRepository.save(user);
        emailService.constructAndSendEmail(user.getEmail(), user.getUsername(), "WELCOME");
      } else {
        return StringConstants.INVALID_DATA_ACCESS;
      }
    } catch (Exception e) {
      System.out.println("error : " + e.getMessage());
    }

    return "User Saved Successfully..";
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

  public boolean checkAdminExist() {
    User user = userRepository.existsByRolesContainingRoleAdmin();

    return user != null && user.getRoles().contains(SecurityConstants.ROLE_ADMIN);
  }

  private List<String> validateUser(User user) {

    List<String> error = new ArrayList<>();

    if (user == null) {
      return null;
    }

    if (!Pattern.compile(Regex.EMAIL_REGEX).matcher(user.getEmail()).matches()) {
      error.add("Invalid Email..");
    }

    User u = userRepository.findByEmail(user.getEmail());

    if (u != null) {
      error.add("Email already Exist.");
    }

    if (user.getRoles().contains(RoleEnum.ROLE_ADMIN.toString())) {
      error.add("you cannot create an Admin.");
    }

    if (!RoleEnum.roleList.contains(user.getRoles().get(0))) {
      error.add("given role not Allowed.");
    }

    return error;
  }
}
