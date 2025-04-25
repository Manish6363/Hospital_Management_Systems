# Hospital_Management_System

## Technologies Used [JDBC + JSP-SERVLET]
  **_JSP (JavaServer Pages):_** Dynamic content display on the patient side, doctor side and admin side.
  
  **_Servlets:_** Admin, Doctor, and Patient registration with appointment requests processing.
  
  **_JDBC (Java Database Connectivity):_** To manage database connections and execute SQL queries.
  
  **_MySQL:_** For data storage and management.
  
  **_HTML/CSS:_** For frontend design and layout.
  
  **_JavaScript:_** For interactive elements and dynamic updates on the UI.


## Objective 
The Hospital Management System (HMS) project is designed to automate and streamline different hospital functions to enhance efficiency, minimize errors, and enable smooth management of patient, doctor, and appointment records. By incorporating technology in hospital management, this system provides improved patient care, streamlined administrative processes, and easier access to information for healthcare professionals. The system is aimed at enabling hospitals to conduct patient registrations, doctor information, appointment booking, and other important activities in an orderly and secure manner.

The long-term aim is to substitute conventional paper-based management systems with an easy-to-use, user-friendly web application that guarantees proper record-keeping, fast data retrieval, and real-time updation.

![Screenshot 2025-04-24 161350](https://github.com/user-attachments/assets/14923233-cbd7-4106-b6db-c00961c7a712)

![Screenshot 2025-04-24 161425](https://github.com/user-attachments/assets/1b4a9fe9-3ac6-4e90-97a0-b2518d84c602)

![Screenshot 2025-04-24 161549](https://github.com/user-attachments/assets/bd4041a1-a284-4e57-9816-3ed202b53f7b)

![Screenshot 2025-04-24 161653](https://github.com/user-attachments/assets/fcacc53b-558a-4af4-8d49-30cf444087a7)


## Modules of the Project:

## 1. Patient Management Module:
**Objective:**
To manage all patient registration, profile management, and appointment request tasks in an efficient and accurate way so that patients can easily interact with the hospital system.

**Features:**

   **_Patient Registration:_** Patients can register by providing their details like username, password, email, phone number, address, date of birth, etc. After registration, patients are given a unique identifier (username) through which they can manage their account.

   **_Profile Management:_** Patients are able to update their personal data, such as contact information and password, so it is always fresh. A safe password-changing method is provided.

   **_Appointment Requests:_** Patients can request an appointment with stating the reason for the appointment and the admin will assign one. The date and time preference are recorded and the request saved in the database.

   **_Appointment History:_** A history of their past and future appointments can be viewed by patients. They are able to see the status of every request (whether pending or approved).

   **_Dashboard:_** The patient is able to view an interactive dashboard, presenting key information such as future appointments, health record, and requesting new appointments.

![Screenshot 2025-04-24 160921](https://github.com/user-attachments/assets/619c7764-e8ea-4aa8-8757-9823118cbe9d)

![Screenshot 2025-04-24 161015](https://github.com/user-attachments/assets/f0a0c1b1-7ed5-42a0-9b00-eb8b529fcb34)

![Screenshot 2025-04-24 161038](https://github.com/user-attachments/assets/18e45638-ceb6-479e-9e54-c86b5779e421)

![Screenshot 2025-04-24 161144](https://github.com/user-attachments/assets/e90b2c41-2ac9-428b-8ba8-3608677997c6)


## 2. Doctor Management Module:
**_Objective:_**
To handle doctor registrations, their profiles, availability, and appointment scheduling, ensuring that the medical staff of the hospital is well-organized and available to patients.

**Features:**

**_Doctor Registration:_** Doctors can register by entering professional information, including name, specialization, years of experience, contact details, and other qualifications.

**_Profile Management:_** Doctors can modify their personal and professional information, including their specialization, years of experience, availability, and passwords.

**_Appointment Management:_** The doctors are able to see only all the approved appointments of patients which are assigned to him by admin. After completion of treatment he can add the remarks for future use by which a report wil be generated.

**_Doctor Dashboard:_** Doctors are able to see a dashboard that shows them relevant information like his profile, scheduled appointments of patient.

![Screenshot 2025-04-24 160736](https://github.com/user-attachments/assets/3a236588-e21a-4c8a-884b-98614e32b26e)

![Screenshot 2025-04-24 160813](https://github.com/user-attachments/assets/8bc5be76-7073-4872-9335-ea63f6912a3c)



## 3. Admin Management Module:
**Objective:**
To centralize the operation of the whole hospital, such as patient and doctor record control, approval of appointments, and report generation to manage hospital activities in an efficient manner.

**Features:**

**_Admin Login & Dashboard:_** Admin logs into the system via secure credentials. After login, they are provided with a rich dashboard that indicates hospital activity and health system.

**_User Management:_** Admins can manage both doctor and patient records. Admins are entitled to delete, and retrieve every record pertaining to patients and doctors.

**_Appointment Approval:_** Admins can approve or decline patient appointment requests. On approval, an appointment date is created, and a doctor is assigned. Admins also keep a check on appointment status (pending or approved).

**_System Maintenance:_** Admins can track system performance, maintain database connections, manage user roles and permissions, and maintain data integrity.

![Screenshot 2025-04-24 160304](https://github.com/user-attachments/assets/3d1f3cf1-8f8d-4274-98eb-b0958bd81a1b)

![Screenshot 2025-04-24 160426](https://github.com/user-attachments/assets/cbdb9bef-9aaf-473d-a411-c6e5887704c4)

![Screenshot 2025-04-24 160509](https://github.com/user-attachments/assets/8f41c349-bcbc-438a-b88d-394de9640adf)

![Screenshot 2025-04-24 160554](https://github.com/user-attachments/assets/89de47db-640f-42b4-8b8b-d220e89f29de)

![Screenshot 2025-04-24 161144](https://github.com/user-attachments/assets/e90b2c41-2ac9-428b-8ba8-3608677997c6)


## 4. Appointment Scheduling Module:
**Objective:**
To make the process of patients asking for, doctors sanctioning, and the admin managing appointment scheduling, with no conflicts and giving proper visibility to all involved.

**Features:**

**_Appointment Request by Patients:_** Patients are able to request appointments by entering a form detailing the reason for visit. The request is saved in the system as "pending."

**_Approval and Assignment:_** Appointment requests are reviewed by admins, approved and assigned to an available specilist doctor. The doctor is notified, and a confirmed appointment date is scheduled to the assigned releated doctor.

**_View Appointments:_** Patients and doctors can view the appointment status. Patients can view if their request is pending, or approved while doctors can view their scheduled appointments.

**_Appointment Notifications:_** Doctors and patients are notified of appointment status updates or when an appointment is imminent.

![Screenshot 2025-04-24 160554](https://github.com/user-attachments/assets/d5982e2c-7adc-4602-8138-e73e0d90a801)


## 5. Security and Authentication Module:
**Objective:**
To provide protection for sensitive hospital information and allow only legitimate users (patients, doctors, admins) to access certain functionalities.

**Features:**

**_User Authentication:_** Patients, doctors, and admins need to log in using valid credentials before they can access their respective features.

**_Role-Based Access Control:_** Each user role (patient, doctor, admin) has limited access to certain parts of the system.

**_Password Encryption:_** Passwords are encrypted securely with encryption algorithms to prevent any unauthorized access to sensitive accounts.

**_Session Management:_** Session management is employed to monitor active user sessions and avoid unauthorized access after login.

## 6. User Interface (UI) and User Experience (UX) Module:
**Objective:**
To offer an intuitive, user-friendly interface that makes it easy for all users (patients, doctors, and admins) to navigate the system and complete their tasks without any issues.

**Features:**

**_Responsive Design:_** The system is responsive and can be used on a range of devices, making it possible for users to access it from desktops, tablets, or smartphones.

**_Interactive Dashboards:_** Separate dashboards are available for patients, physicians, and admins, each with simple access to key functions such as appointment management, profile settings, and notifications.

**_Intuitive Navigation:_** The layout of the system is simple, with simple-to-use navigation and intuitive controls for appointment management, profiles, and data.
