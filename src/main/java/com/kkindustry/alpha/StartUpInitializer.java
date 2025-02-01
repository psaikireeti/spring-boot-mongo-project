package com.kkindustry.alpha;

import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.repository.UserRepository;
import com.kkindustry.alpha.service.UserService;
import com.kkindustry.alpha.util.Utils;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartUpInitializer {

  @Autowired MongoTemplate mongoTemplate;
  @Autowired UserService userService;
  @Autowired UserRepository userRepository;
  @Autowired PasswordEncoder passwordEncoder;

  @PostConstruct
  public void init() {
    initializeCollections();
    createAdminUserIfNotExist();
  }

  public void initializeCollections() {
    Set<String> collectionNames = mongoTemplate.getCollectionNames();
    if (collectionNames.isEmpty()) {
      System.out.println("No collections found in MongoDB. Creating required collections...");

      mongoTemplate.createCollection("users", CollectionOptions.empty());

      System.out.println("Collections created successfully.");

    } else {
      System.out.println("Collections already exist.");
    }
  }

  public void createAdminUserIfNotExist() {
    if (!userService.checkAdminExist()) {
      User user = new User();
      user.setId(Utils.generateUUID());
      user.setEmail("admin@alpha.com");
      user.setPassword(passwordEncoder.encode("admin123"));
      user.setUsername("admin");
      user.setRoles(List.of("ROLE_ADMIN"));
      userRepository.save(user);
    }
  }
}
