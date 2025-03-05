# Intelligent Internship Management Platform

This project, developed as part of the **PIDev (Projet Intégré)** course at **Esprit School of Engineering**, aims to provide a comprehensive platform for managing internships. The platform connects **students**, **university**, and **companies** in a streamlined way to improve the internship application process.

**For Students**: The platform allows students to create and manage their profiles, apply for internships based on their skills and interests, and track the status of their applications.

**For University**: The platform enables teachers to supervise and evaluate student progress and internship applications, ensuring students are well-prepared for their professional experiences.

**For Companies**: The platform allows companies to post internship offers, review student applications, and manage the recruitment process, thus helping them find the best candidates for their needs.

This project consists of two repositories:
1. **Frontend**: Developed with **Angular** for a dynamic and responsive user interface.
2. **ackend**: Developed with **Spring Boot**, using a **microservice architecture** to manage various components like user management, internship offers, and applications.

## Microservice Architecture for Backend

The backend is organized into multiple **microservices**, each responsible for a specific domain or functionality. This allows for independent development, deployment, and scaling of different parts of the application.


## Features
- **Student Profiles**: Create and manage student profiles with academic and skill details.
- **Internship Applications**: Apply for internships, track application statuses.
- **Teacher Dashboard**: Teachers can view and evaluate student progress.
- **Company Dashboard**: Companies can post internship offers and evaluate applications from students.

## Tech Stack
### Frontend
- **Angular** (for building a responsive, dynamic user interface)
- **AdminLTE** (for UI Template)

### Backend
- **Spring Boot** (for the Java backend API and business logic)
- **MySQL** (for database management)

### Other Tools
- **GitHub** (for version control and collaboration)


## Directory Structure
- `IntershipPlateform_Frontend`: Contains Angular code for the frontend application.
- `IntershipPlateform_Backend-`: Contains Spring Boot code for the backend API and services.

## Getting Started
To get started, clone both the frontend and backend repositories and follow the instructions to run each part of the application:

1. Clone the repositories:
```bash
git clone https://github.com/Touterima/IntershipPlateform_Frontend
git clone https://github.com/Touterima/IntershipPlateform_Backend-

2. For the frontend, navigate to the frontend folder and install dependencies:
npm install
ng serve

