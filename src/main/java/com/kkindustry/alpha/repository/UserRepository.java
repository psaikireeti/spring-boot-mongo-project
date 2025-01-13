package com.kkindustry.alpha.repository;

import com.kkindustry.alpha.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  public User getUserDetail(String id) {

    return new User();
  }
}
