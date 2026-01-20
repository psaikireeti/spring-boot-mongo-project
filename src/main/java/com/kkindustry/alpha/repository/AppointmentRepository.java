package com.kkindustry.alpha.repository;

import com.kkindustry.alpha.entity.Appointment;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
  @Query("{ 'patientId' : ?0 }")
  List<Appointment> findByPatientId(String patientId);

  @Query("{ 'doctorId' : ?0 }")
  List<Appointment> findByDoctorId(String doctorId);

  @Query("{ 'appointmentDate' : ?0, 'status' : ?1 }")
  List<Appointment> findByAppointmentDateAndStatus(Date appointmentDate, String status);

  @Query("{ 'doctorId' : ?0, 'appointmentDate' : ?1, 'appointmentTime' : ?2 }")
  Appointment findByDoctorIdAndAppointmentDateAndAppointmentTime(
      String doctorId, Date appointmentDate, String appointmentTime);
}
