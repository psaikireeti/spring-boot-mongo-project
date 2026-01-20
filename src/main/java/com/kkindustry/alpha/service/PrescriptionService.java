package com.kkindustry.alpha.service;

import com.kkindustry.alpha.entity.Prescription;
import com.kkindustry.alpha.repository.PrescriptionRepository;
import com.kkindustry.alpha.util.Utils;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
  private final PrescriptionRepository prescriptionRepository;

  @Autowired
  public PrescriptionService(PrescriptionRepository prescriptionRepository) {
    this.prescriptionRepository = prescriptionRepository;
  }

  public String savePrescription(Prescription prescription) {
    try {
      if (prescription == null) {
        return "Prescription cannot be empty";
      }

      if (prescription.getId() == null || prescription.getId().isEmpty()) {
        prescription.setId(Utils.generateUUID());
      }

      prescription.setPrescriptionDate(new Date());
      prescription.setCreatedDate(new Date());
      prescription.setUpdatedDate(new Date());
      prescriptionRepository.save(prescription);
      return "Prescription saved successfully";
    } catch (Exception e) {
      return "Error saving prescription: " + e.getMessage();
    }
  }

  public Optional<Prescription> getPrescriptionById(String id) {
    return prescriptionRepository.findById(id);
  }

  public List<Prescription> getAllPrescriptions() {
    return prescriptionRepository.findAll();
  }

  public List<Prescription> getPrescriptionsByPatientId(String patientId) {
    return prescriptionRepository.findByPatientId(patientId);
  }

  public List<Prescription> getPrescriptionsByDoctorId(String doctorId) {
    return prescriptionRepository.findByDoctorId(doctorId);
  }

  public Prescription getPrescriptionByAppointmentId(String appointmentId) {
    return prescriptionRepository.findByAppointmentId(appointmentId);
  }

  public String updatePrescription(Prescription prescription) {
    try {
      if (prescription == null || prescription.getId() == null) {
        return "Prescription ID is required for update";
      }

      Optional<Prescription> existing = prescriptionRepository.findById(prescription.getId());
      if (existing.isEmpty()) {
        return "Prescription not found";
      }

      prescription.setUpdatedDate(new Date());
      prescriptionRepository.save(prescription);
      return "Prescription updated successfully";
    } catch (Exception e) {
      return "Error updating prescription: " + e.getMessage();
    }
  }

  public void deletePrescription(String id) {
    prescriptionRepository.deleteById(id);
  }
}
