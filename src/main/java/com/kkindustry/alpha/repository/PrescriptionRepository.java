package com.kkindustry.alpha.repository;

import com.kkindustry.alpha.entity.Prescription;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
  @Query("{ 'patientId' : ?0 }")
  List<Prescription> findByPatientId(String patientId);

  @Query("{ 'doctorId' : ?0 }")
  List<Prescription> findByDoctorId(String doctorId);

  @Query("{ 'appointmentId' : ?0 }")
  Prescription findByAppointmentId(String appointmentId);
}
