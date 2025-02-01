package com.kkindustry.alpha.util;

import com.kkindustry.alpha.repository.UserRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

public class Utils {

  @Autowired UserRepository userRepository;

  public static String generateUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
