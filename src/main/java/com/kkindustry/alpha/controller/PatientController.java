package com.kkindustry.alpha.controller;

import com.kkindustry.alpha.entity.Patient;
import com.kkindustry.alpha.service.PatientService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
  private final PatientService patientService;

  @Autowired
  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping
  public ResponseEntity<String> savePatient(@RequestBody Patient patient) {
    return ResponseEntity.ok(patientService.savePatient(patient));
  }

  @GetMapping
  public ResponseEntity<List<Patient>> getAllPatients() {
    return ResponseEntity.ok(patientService.getAllPatients());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable("id") String id) {
    return ResponseEntity.ok(patientService.getPatientById(id));
  }

  @GetMapping(value = "/user/{userId}")
  public ResponseEntity<Patient> getPatientByUserId(@PathVariable("userId") String userId) {
    return ResponseEntity.ok(patientService.getPatientByUserId(userId));
  }

  @GetMapping(value = "/email/{email}")
  public ResponseEntity<Patient> getPatientByEmail(@PathVariable("email") String email) {
    return ResponseEntity.ok(patientService.getPatientByEmail(email));
  }

  @PutMapping
  public ResponseEntity<String> updatePatient(@RequestBody Patient patient) {
    return ResponseEntity.ok(patientService.updatePatient(patient));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deletePatient(@PathVariable("id") String id) {
    patientService.deletePatient(id);
    return ResponseEntity.ok("Patient deleted successfully");
  }
}
