package com.kkindustry.alpha;

import com.kkindustry.alpha.entity.User;
import com.kkindustry.alpha.repository.UserRepository;
import com.kkindustry.alpha.service.UserService;
import com.kkindustry.alpha.util.Utils;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartUpInitializer {
  private static final Logger logger = LoggerFactory.getLogger(StartUpInitializer.class);

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
    logger.info("Checking collections in MongoDB...");

    if (!collectionNames.contains("users")) {
      mongoTemplate.createCollection("users", CollectionOptions.empty());
      logger.info("Created 'users' collection.");
    }

    if (!collectionNames.contains("patients")) {
      mongoTemplate.createCollection("patients", CollectionOptions.empty());
      logger.info("Created 'patients' collection.");
    }

    if (!collectionNames.contains("appointments")) {
      mongoTemplate.createCollection("appointments", CollectionOptions.empty());
      logger.info("Created 'appointments' collection.");
    }

    if (!collectionNames.contains("prescriptions")) {
      mongoTemplate.createCollection("prescriptions", CollectionOptions.empty());
      logger.info("Created 'prescriptions' collection.");
    }

    logger.info("Collections initialization completed.");
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
