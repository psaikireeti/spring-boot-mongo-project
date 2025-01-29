package com.kkindustry.alpha;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StartUpProcess {

  @PostConstruct
  public void init() {
    createAdminUserIfNotExist();
  }

  public void createAdminUserIfNotExist() {}
}
