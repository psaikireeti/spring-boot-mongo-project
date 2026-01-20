package com.kkindustry.alpha.controller;

import com.kkindustry.alpha.entity.Prescription;
import com.kkindustry.alpha.service.PrescriptionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/prescription")
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @Autowired
  public PrescriptionController(PrescriptionService prescriptionService) {
    this.prescriptionService = prescriptionService;
  }

  @PostMapping
  public ResponseEntity<String> savePrescription(@RequestBody Prescription prescription) {
    return ResponseEntity.ok(prescriptionService.savePrescription(prescription));
  }

  @GetMapping
  public ResponseEntity<List<Prescription>> getAllPrescriptions() {
    return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Prescription>> getPrescriptionById(@PathVariable("id") String id) {
    return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
  }

  @GetMapping(value = "/patient/{patientId}")
  public ResponseEntity<List<Prescription>> getPrescriptionsByPatientId(
      @PathVariable("patientId") String patientId) {
    return ResponseEntity.ok(prescriptionService.getPrescriptionsByPatientId(patientId));
  }

  @GetMapping(value = "/doctor/{doctorId}")
  public ResponseEntity<List<Prescription>> getPrescriptionsByDoctorId(
      @PathVariable("doctorId") String doctorId) {
    return ResponseEntity.ok(prescriptionService.getPrescriptionsByDoctorId(doctorId));
  }

  @GetMapping(value = "/appointment/{appointmentId}")
  public ResponseEntity<Prescription> getPrescriptionByAppointmentId(
      @PathVariable("appointmentId") String appointmentId) {
    return ResponseEntity.ok(prescriptionService.getPrescriptionByAppointmentId(appointmentId));
  }

  @PutMapping
  public ResponseEntity<String> updatePrescription(@RequestBody Prescription prescription) {
    return ResponseEntity.ok(prescriptionService.updatePrescription(prescription));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deletePrescription(@PathVariable("id") String id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.ok("Prescription deleted successfully");
  }
}
