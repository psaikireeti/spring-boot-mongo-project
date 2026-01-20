package com.kkindustry.alpha.repository;

import com.kkindustry.alpha.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
  @Query("{ 'email' : ?0 }")
  Patient findByEmail(String email);

  @Query("{ 'userId' : ?0 }")
  Patient findByUserId(String userId);

  @Query("{ 'phoneNumber' : ?0 }")
  Patient findByPhoneNumber(String phoneNumber);
}
