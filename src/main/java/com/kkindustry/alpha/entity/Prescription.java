package com.kkindustry.alpha.entity;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prescriptions")
public class Prescription {
  @Id private String id;
  private String patientId;
  private String doctorId;
  private String appointmentId;
  private List<Medication> medications;
  private String diagnosis;
  private String notes;
  private Date prescriptionDate = new Date();
  private Date createdDate = new Date();
  private Date updatedDate = new Date();

  public Prescription() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public String getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(String doctorId) {
    this.doctorId = doctorId;
  }

  public String getAppointmentId() {
    return appointmentId;
  }

  public void setAppointmentId(String appointmentId) {
    this.appointmentId = appointmentId;
  }

  public List<Medication> getMedications() {
    return medications;
  }

  public void setMedications(List<Medication> medications) {
    this.medications = medications;
  }

  public String getDiagnosis() {
    return diagnosis;
  }

  public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Date getPrescriptionDate() {
    return prescriptionDate;
  }

  public void setPrescriptionDate(Date prescriptionDate) {
    this.prescriptionDate = prescriptionDate;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public static class Medication {
    private String medicineName;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;

    public Medication() {}

    public String getMedicineName() {
      return medicineName;
    }

    public void setMedicineName(String medicineName) {
      this.medicineName = medicineName;
    }

    public String getDosage() {
      return dosage;
    }

    public void setDosage(String dosage) {
      this.dosage = dosage;
    }

    public String getFrequency() {
      return frequency;
    }

    public void setFrequency(String frequency) {
      this.frequency = frequency;
    }

    public String getDuration() {
      return duration;
    }

    public void setDuration(String duration) {
      this.duration = duration;
    }

    public String getInstructions() {
      return instructions;
    }

    public void setInstructions(String instructions) {
      this.instructions = instructions;
    }
  }
}
