# Employee Management API

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.0-brightgreen.svg)
![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-green.svg)
![JPA / Hibernate](https://img.shields.io/badge/JPA_/_Hibernate-6.x-orange.svg)
![Authentication](https://img.shields.io/badge/Authentication-JWT-red.svg)
![Database](https://img.shields.io/badge/Database-H2-lightgrey.svg)
![Build](https://img.shields.io/badge/Build-Maven-critical.svg)

---

## 📌 Overview

This project is a secure RESTful API for managing employee data, built with Spring Boot.  
It serves as a robust backend foundation, demonstrating professional practices in API development, including:
- Stateless authentication with JSON Web Tokens (JWT)
- Role-Based Access Control (RBAC)
- A clear, layered architecture

The API is documented interactively using **Swagger (OpenAPI 3)**.

---

## ✨ Core Features

✅ **CRUD Operations for Employees**  
Full support for creating, reading, updating, and deleting employee records.

✅ **Secure Endpoints**  
All employee data endpoints are protected and require a valid JWT.

✅ **Stateless Authentication**  
Uses JWT for authentication, making the API stateless and scalable.

✅ **Role-Based Access Control (RBAC)**
- `USER` role: Can perform read operations (`GET`)
- `ADMIN` role: Can perform all CRUD operations, including `DELETE`

✅ **User Registration and Login**  
Endpoints `/api/auth/register` and `/api/auth/login` to manage users and issue tokens.

✅ **Centralized Exception Handling**  
A `@ControllerAdvice` handles all application-specific and security exceptions gracefully.

✅ **Interactive API Documentation**  
Swagger UI is available for easy testing and exploration of the API.

---

## 🗂️ Architecture

The application follows a classic layered architecture to ensure separation of concerns and maintainability:

- **Controller Layer**: Handles incoming HTTP requests, validates input, and delegates business logic to the service layer.
- **Service Layer**: Contains the core business logic.
- **Repository Layer (DAO)**: Manages data persistence using Spring Data JPA.
- **Security Layer**: Integrated throughout the application using Spring Security, with custom filters and configurations for JWT.

```plaintext
Request -> Filter Chain (Security) -> DispatcherServlet -> Controller -> Service -> Repository -> Database
```
## 🧩 Technologies Used

- **Framework**: Spring Boot 3.5.0
- **Language**: Java 21
- **Security**: Spring Security 6.x
- **Authentication**: JWT (JSON Web Tokens) with `jjwt` library
- **Password Encoding**: `BCryptPasswordEncoder`
- **Database**:
    - **Persistence**: Spring Data JPA (Hibernate)
    - **In-memory DB**: H2 Database Engine (for development and testing)
- **API Documentation**: Springdoc OpenAPI (Swagger 3)
- **Build Tool**: Maven
- **Utilities**: Lombok

---

## 🚀 Setup and Running the Project

### ✅ Prerequisites

- Java JDK 21 or later
- Apache Maven 3.6+
- An IDE like IntelliJ IDEA or VS Code

### ⚙️ Clone the Repository

```bash
git clone <your-repository-url>
cd employee
```
### 📦 Build the Project

```bash
mvn clean install
```
### 🏃‍♂️ Run the Application

Run the main class EmployeeApplication.java from your IDE or use Maven:
```bash
mvn spring-boot:run
```
The application will start on http://localhost:8080.

📚 API Endpoints
This section details all the available endpoints to interact with the application.

📖 API Documentation (Swagger UI)
The primary way to interact with the API is through the interactive Swagger documentation.
URL: http://localhost:8080/docs

🗄️ H2 Database Console
You can access the in-memory database console to view the tables (_USER, EMPLOYE) and their data directly.
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:file:./data/employeedb
Username: sa
Password: password

        Good luck with your project! If you have any questions or need further assistance, feel free to ask.