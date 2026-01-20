# Hospital Management System

A comprehensive **Hospital Management System** built using **Spring Boot**, **MongoDB**, and **Java**. This system is designed to manage hospital operations efficiently, including user roles, patient records, appointments, prescriptions, and more.

---

## Features

- **User Roles**: Supports multiple user roles such as Admin, Doctor, Receptionist, Pharmacist, and Patient.
- **Role-Based Access Control**: Redirects users to specific dashboards based on their roles after login.
- **Patient Management**: Add, update, and manage patient records with complete medical information.
- **Appointment Scheduling**: Schedule and manage appointments for patients with doctors.
- **Prescription Management**: Doctors can create prescriptions with medications, and patients/pharmacists can view them.
- **MongoDB Integration**: Uses MongoDB for flexible and scalable data storage.
- **Spring Security**: Implements secure authentication and authorization with role-based access.

---

## Technologies Used

- **Backend**: Spring Boot 3.4.1, Java 17
- **Database**: MongoDB
- **Security**: Spring Security
- **Frontend**: HTML, CSS, JavaScript (for static pages)
- **Build Tool**: Maven
- **Email**: Spring Mail (for notifications)

---

## User Roles and Dashboards

The system supports the following user roles, each with a dedicated dashboard:

| Role           | Dashboard Path         | Description                              |
|----------------|------------------------|------------------------------------------|
| Admin          | `/admin-home.html`     | Manages hospital operations and users.   |
| Doctor         | `/doctor-home.html`    | Views and manages patient appointments and creates prescriptions.  |
| Receptionist   | `/receptionist-home.html` | Handles patient registration and appointment scheduling. |
| Pharmacist     | `/pharmacy-home.html`  | Manages pharmacy operations and views prescriptions.             |
| Patient        | `/patient-home.html`   | Views appointments and medical prescriptions.  |

---

## Installation

Follow these steps to set up and run the project locally:

### Prerequisites

- Java 17 or higher
- MongoDB installed and running (default port: 27017)
- Maven installed
- Git (optional, for cloning)

### Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/psaikireeti/spring-boot-mongo-project.git
   cd spring-boot-mongo-project
   ```

2. **Configure MongoDB**:
   - Ensure MongoDB is running on `localhost:27017`
   - The application will automatically create the database `hospital` and required collections on first run

3. **Configure Email (Optional)**:
   - Update email settings in `src/main/resources/application.properties`:
     ```properties
     spring.mail.username=your-email@gmail.com
     spring.mail.password=your-app-password
     ```

4. **Build the Project**:
   ```bash
   mvn clean install
   ```

5. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR file:
   ```bash
   java -jar target/alpha-0.0.1-SNAPSHOT.jar
   ```

6. **Access the Application**:
   - Open your browser and navigate to: `http://localhost:9090/alpha/login.html`
   - Default admin credentials:
     - **Username/Email**: `admin@alpha.com`
     - **Password**: `admin123`

---

## API Endpoints

### User Management
- `POST /alpha/user` - Create a new user (Admin only)
- `GET /alpha/user` - Get all users
- `GET /alpha/user/{id}` - Get user by ID
- `DELETE /alpha/user/{id}` - Delete user by ID
- `DELETE /alpha/user` - Bulk delete users

### Patient Management
- `POST /alpha/patient` - Create a new patient
- `GET /alpha/patient` - Get all patients
- `GET /alpha/patient/{id}` - Get patient by ID
- `GET /alpha/patient/user/{userId}` - Get patient by user ID
- `GET /alpha/patient/email/{email}` - Get patient by email
- `PUT /alpha/patient` - Update patient
- `DELETE /alpha/patient/{id}` - Delete patient

### Appointment Management
- `POST /alpha/appointment` - Schedule a new appointment
- `GET /alpha/appointment` - Get all appointments
- `GET /alpha/appointment/{id}` - Get appointment by ID
- `GET /alpha/appointment/patient/{patientId}` - Get appointments by patient ID
- `GET /alpha/appointment/doctor/{doctorId}` - Get appointments by doctor ID
- `GET /alpha/appointment/date/{date}/status/{status}` - Get appointments by date and status
- `PUT /alpha/appointment` - Update appointment
- `PUT /alpha/appointment/cancel/{id}` - Cancel appointment
- `DELETE /alpha/appointment/{id}` - Delete appointment

### Prescription Management
- `POST /alpha/prescription` - Create a new prescription
- `GET /alpha/prescription` - Get all prescriptions
- `GET /alpha/prescription/{id}` - Get prescription by ID
- `GET /alpha/prescription/patient/{patientId}` - Get prescriptions by patient ID
- `GET /alpha/prescription/doctor/{doctorId}` - Get prescriptions by doctor ID
- `GET /alpha/prescription/appointment/{appointmentId}` - Get prescription by appointment ID
- `PUT /alpha/prescription` - Update prescription
- `DELETE /alpha/prescription/{id}` - Delete prescription

---

## Usage Guide

### Admin Dashboard
- **Manage Users**: Create new users (Doctors, Receptionists, Pharmacists, Patients)
- **View Patients**: View all patient records in the system
- **View Appointments**: Monitor all appointments
- **System Analytics**: View system statistics (coming soon)

### Doctor Dashboard
- **View Appointments**: View scheduled appointments
- **Add Prescription**: Create prescriptions with medications for patients
- **View Prescriptions**: View previously created prescriptions

### Receptionist Dashboard
- **Manage Patients**: Register new patients and update patient information
- **Schedule Appointments**: Book appointments between patients and doctors
- **View Appointments**: View all scheduled appointments

### Pharmacist Dashboard
- **Search Prescriptions**: Search prescriptions by patient ID
- **View All Prescriptions**: View all prescriptions in the system

### Patient Dashboard
- **View Appointments**: View your scheduled appointments
- **View Prescriptions**: View your medical prescriptions

---

## Database Schema

### Users Collection
- `id`: Unique identifier
- `username`: Username
- `email`: Email address (used for login)
- `password`: Encrypted password
- `roles`: List of roles (ROLE_ADMIN, ROLE_DOCTOR, etc.)
- `createdDate`: Account creation date

### Patients Collection
- `id`: Unique identifier
- `firstName`, `lastName`: Patient name
- `email`: Email address
- `phoneNumber`: Contact number
- `dateOfBirth`: Date of birth
- `gender`: Gender
- `bloodGroup`: Blood group
- `address`: Address
- `emergencyContactName`, `emergencyContactPhone`: Emergency contact
- `userId`: Reference to User entity
- `createdDate`, `updatedDate`: Timestamps

### Appointments Collection
- `id`: Unique identifier
- `patientId`: Reference to Patient
- `doctorId`: Reference to Doctor (User)
- `appointmentDate`: Appointment date
- `appointmentTime`: Appointment time
- `status`: SCHEDULED, COMPLETED, CANCELLED, RESCHEDULED
- `reason`: Appointment reason
- `notes`: Additional notes
- `createdBy`: Who created the appointment
- `createdDate`, `updatedDate`: Timestamps

### Prescriptions Collection
- `id`: Unique identifier
- `patientId`: Reference to Patient
- `doctorId`: Reference to Doctor (User)
- `appointmentId`: Reference to Appointment (optional)
- `medications`: List of medications with dosage, frequency, duration, instructions
- `diagnosis`: Diagnosis information
- `notes`: Additional notes
- `prescriptionDate`: Prescription date
- `createdDate`, `updatedDate`: Timestamps

---

## Security Configuration

- **Authentication**: Form-based login using Spring Security
- **Password Encryption**: BCrypt password encoder
- **Role-Based Access**: Different endpoints accessible based on user roles
- **Session Management**: 5-minute session timeout (configurable)

---

## Troubleshooting

### MongoDB Connection Issues
- Ensure MongoDB is running: `mongod` or `brew services start mongodb-community` (macOS)
- Check MongoDB port (default: 27017)
- Verify database name in `application.properties`

### Port Already in Use
- Change the port in `application.properties`: `server.port=9090`
- Or stop the process using port 9090

### Login Issues
- Default admin credentials: `admin@alpha.com` / `admin123`
- Ensure MongoDB is running and collections are created
- Check application logs for errors

---

## Development

### Project Structure
```
src/
├── main/
│   ├── java/com/kkindustry/alpha/
│   │   ├── controller/     # REST controllers
│   │   ├── entity/         # MongoDB entities
│   │   ├── repository/     # MongoDB repositories
│   │   ├── service/        # Business logic
│   │   ├── security/       # Security configuration
│   │   └── util/           # Utility classes
│   └── resources/
│       ├── static/         # HTML pages
│       └── application.properties
└── test/                   # Test files
```

### Running Tests
```bash
mvn test
```

### Building for Production
```bash
mvn clean package
```

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## License

This project is licensed under the MIT License.

---

## Contact

For questions or support, please contact the development team.

---

## Future Enhancements

- [ ] Real-time notifications
- [ ] Advanced analytics dashboard
- [ ] PDF report generation
- [ ] Mobile app integration
- [ ] Multi-language support
- [ ] Advanced search and filtering
- [ ] Appointment reminders via SMS/Email
- [ ] Integration with payment gateways
