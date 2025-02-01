package com.kkindustry.alpha.repository;

import com.kkindustry.alpha.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, String> {
  @Query("{ 'email' : ?0 }")
  User findByEmail(String email);

  @Query("{ 'roles': { $in: ['ROLE_ADMIN'] } }")
  User existsByRolesContainingRoleAdmin();
}
