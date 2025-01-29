package com.kkindustry.alpha.repository;

import com.kkindustry.alpha.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, String> {
  @Query("SELECT * FROM Employee WHERE username = :email")
  User findByUsername(@Param("email") String email);
}
