package com.kkindustry.alpha.service;

import com.kkindustry.alpha.entity.Patient;
import com.kkindustry.alpha.repository.PatientRepository;
import com.kkindustry.alpha.util.Regex;
import com.kkindustry.alpha.util.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
  private final PatientRepository patientRepository;

  @Autowired
  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public String savePatient(Patient patient) {
    try {
      if (patient == null) {
        return "Patient cannot be empty";
      }

      List<String> errors = validatePatient(patient);
      if (errors != null && !errors.isEmpty()) {
        return errors.get(0);
      }

      if (patient.getId() == null || patient.getId().isEmpty()) {
        patient.setId(Utils.generateUUID());
      }
      patient.setCreatedDate(new Date());
      patient.setUpdatedDate(new Date());
      patientRepository.save(patient);
      return "Patient saved successfully";
    } catch (Exception e) {
      return "Error saving patient: " + e.getMessage();
    }
  }

  public Optional<Patient> getPatientById(String id) {
    return patientRepository.findById(id);
  }

  public List<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  public Patient getPatientByUserId(String userId) {
    return patientRepository.findByUserId(userId);
  }

  public Patient getPatientByEmail(String email) {
    return patientRepository.findByEmail(email);
  }

  public void deletePatient(String id) {
    patientRepository.deleteById(id);
  }

  public String updatePatient(Patient patient) {
    try {
      if (patient == null || patient.getId() == null) {
        return "Patient ID is required for update";
      }

      Optional<Patient> existingPatient = patientRepository.findById(patient.getId());
      if (existingPatient.isEmpty()) {
        return "Patient not found";
      }

      List<String> errors = validatePatientForUpdate(patient);
      if (errors != null && !errors.isEmpty()) {
        return errors.get(0);
      }

      patient.setUpdatedDate(new Date());
      patientRepository.save(patient);
      return "Patient updated successfully";
    } catch (Exception e) {
      return "Error updating patient: " + e.getMessage();
    }
  }

  private List<String> validatePatient(Patient patient) {
    List<String> errors = new ArrayList<>();

    if (patient.getEmail() != null
        && !Pattern.compile(Regex.EMAIL_REGEX).matcher(patient.getEmail()).matches()) {
      errors.add("Invalid email format");
    }

    if (patient.getEmail() != null) {
      Patient existingPatient = patientRepository.findByEmail(patient.getEmail());
      if (existingPatient != null && !existingPatient.getId().equals(patient.getId())) {
        errors.add("Email already exists");
      }
    }

    if (patient.getPhoneNumber() != null) {
      Patient existingPatient = patientRepository.findByPhoneNumber(patient.getPhoneNumber());
      if (existingPatient != null && !existingPatient.getId().equals(patient.getId())) {
        errors.add("Phone number already exists");
      }
    }

    return errors;
  }

  private List<String> validatePatientForUpdate(Patient patient) {
    return validatePatient(patient);
  }
}
