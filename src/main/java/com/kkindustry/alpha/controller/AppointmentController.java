package com.kkindustry.alpha.controller;

import com.kkindustry.alpha.entity.Appointment;
import com.kkindustry.alpha.service.AppointmentService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {
  private final AppointmentService appointmentService;

  @Autowired
  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @PostMapping
  public ResponseEntity<String> saveAppointment(@RequestBody Appointment appointment) {
    return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
  }

  @GetMapping
  public ResponseEntity<List<Appointment>> getAllAppointments() {
    return ResponseEntity.ok(appointmentService.getAllAppointments());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Appointment>> getAppointmentById(@PathVariable("id") String id) {
    return ResponseEntity.ok(appointmentService.getAppointmentById(id));
  }

  @GetMapping(value = "/patient/{patientId}")
  public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(
      @PathVariable("patientId") String patientId) {
    return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId));
  }

  @GetMapping(value = "/doctor/{doctorId}")
  public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(
      @PathVariable("doctorId") String doctorId) {
    return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
  }

  @GetMapping(value = "/date/{date}/status/{status}")
  public ResponseEntity<List<Appointment>> getAppointmentsByDateAndStatus(
      @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
      @PathVariable("status") String status) {
    return ResponseEntity.ok(appointmentService.getAppointmentsByDateAndStatus(date, status));
  }

  @PutMapping
  public ResponseEntity<String> updateAppointment(@RequestBody Appointment appointment) {
    return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
  }

  @PutMapping(value = "/cancel/{id}")
  public ResponseEntity<String> cancelAppointment(@PathVariable("id") String id) {
    return ResponseEntity.ok(appointmentService.cancelAppointment(id));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteAppointment(@PathVariable("id") String id) {
    appointmentService.deleteAppointment(id);
    return ResponseEntity.ok("Appointment deleted successfully");
  }
}
