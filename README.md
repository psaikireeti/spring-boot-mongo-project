# spring-boot-mongo-project


# Hospital Management System

A comprehensive **Hospital Management System** built using **Spring Boot**, **MongoDB**, and **Java**. This system is designed to manage hospital operations efficiently, including user roles, patient records, appointments, and more.

---

## Features

- **User Roles**: Supports multiple user roles such as Admin, Doctor, Receptionist, Pharmacist, and Patient.
- **Role-Based Access Control**: Redirects users to specific dashboards based on their roles after login.
- **Patient Management**: Add, update, and manage patient records.
- **Appointment Scheduling**: Schedule and manage appointments for patients.
- **MongoDB Integration**: Uses MongoDB for flexible and scalable data storage.
- **Spring Security**: Implements secure authentication and authorization.

---

## Technologies Used

- **Backend**: Spring Boot, Java
- **Database**: MongoDB
- **Security**: Spring Security
- **Frontend**: HTML, CSS, JavaScript (for static pages)
- **Build Tool**: Maven

---

## User Roles and Dashboards

The system supports the following user roles, each with a dedicated dashboard:

| Role           | Dashboard Path         | Description                              |
|----------------|------------------------|------------------------------------------|
| Admin          | `/admin-home.html`     | Manages hospital operations and users.   |
| Doctor         | `/doctor-home.html`    | Views and manages patient appointments.  |
| Receptionist   | `/receptionist-home.html` | Handles patient registration and appointments. |
| Pharmacist     | `/pharmacy-home.html`  | Manages pharmacy operations.             |
| Patient        | `/patient-home.html`   | Views appointments and medical records.  |

---

## Installation

Follow these steps to set up and run the project locally:

### Prerequisites
- Java 17 or higher
- MongoDB installed and running
- Maven installed

### Steps
1. **Clone the Repository**:
   ```bash
   https://github.com/psaikireeti/spring-boot-mongo-project.git