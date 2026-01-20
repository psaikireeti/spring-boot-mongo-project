package com.kkindustry.alpha.service;

import com.kkindustry.alpha.entity.Appointment;
import com.kkindustry.alpha.repository.AppointmentRepository;
import com.kkindustry.alpha.util.Utils;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
  private final AppointmentRepository appointmentRepository;

  @Autowired
  public AppointmentService(AppointmentRepository appointmentRepository) {
    this.appointmentRepository = appointmentRepository;
  }

  public String saveAppointment(Appointment appointment) {
    try {
      if (appointment == null) {
        return "Appointment cannot be empty";
      }

      if (appointment.getId() == null || appointment.getId().isEmpty()) {
        appointment.setId(Utils.generateUUID());
      }

      // Check for conflicts
      Appointment existing =
          appointmentRepository.findByDoctorIdAndAppointmentDateAndAppointmentTime(
              appointment.getDoctorId(),
              appointment.getAppointmentDate(),
              appointment.getAppointmentTime());
      if (existing != null && !existing.getId().equals(appointment.getId())) {
        return "Doctor already has an appointment at this time";
      }

      if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {
        appointment.setStatus("SCHEDULED");
      }

      appointment.setCreatedDate(new Date());
      appointment.setUpdatedDate(new Date());
      appointmentRepository.save(appointment);
      return "Appointment scheduled successfully";
    } catch (Exception e) {
      return "Error saving appointment: " + e.getMessage();
    }
  }

  public Optional<Appointment> getAppointmentById(String id) {
    return appointmentRepository.findById(id);
  }

  public List<Appointment> getAllAppointments() {
    return appointmentRepository.findAll();
  }

  public List<Appointment> getAppointmentsByPatientId(String patientId) {
    return appointmentRepository.findByPatientId(patientId);
  }

  public List<Appointment> getAppointmentsByDoctorId(String doctorId) {
    return appointmentRepository.findByDoctorId(doctorId);
  }

  public List<Appointment> getAppointmentsByDateAndStatus(Date date, String status) {
    return appointmentRepository.findByAppointmentDateAndStatus(date, status);
  }

  public String updateAppointment(Appointment appointment) {
    try {
      if (appointment == null || appointment.getId() == null) {
        return "Appointment ID is required for update";
      }

      Optional<Appointment> existing = appointmentRepository.findById(appointment.getId());
      if (existing.isEmpty()) {
        return "Appointment not found";
      }

      // Check for conflicts if time/date changed
      if (appointment.getAppointmentDate() != null && appointment.getAppointmentTime() != null) {
        Appointment conflict =
            appointmentRepository.findByDoctorIdAndAppointmentDateAndAppointmentTime(
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime());
        if (conflict != null && !conflict.getId().equals(appointment.getId())) {
          return "Doctor already has an appointment at this time";
        }
      }

      appointment.setUpdatedDate(new Date());
      appointmentRepository.save(appointment);
      return "Appointment updated successfully";
    } catch (Exception e) {
      return "Error updating appointment: " + e.getMessage();
    }
  }

  public String cancelAppointment(String id) {
    try {
      Optional<Appointment> appointment = appointmentRepository.findById(id);
      if (appointment.isEmpty()) {
        return "Appointment not found";
      }

      Appointment appt = appointment.get();
      appt.setStatus("CANCELLED");
      appt.setUpdatedDate(new Date());
      appointmentRepository.save(appt);
      return "Appointment cancelled successfully";
    } catch (Exception e) {
      return "Error cancelling appointment: " + e.getMessage();
    }
  }

  public void deleteAppointment(String id) {
    appointmentRepository.deleteById(id);
  }
}
