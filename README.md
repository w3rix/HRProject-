# HR Project Backend

This is the backend implementation of a Human Resources (HR) system, built with **Spring Boot** and **Spring Security**, using **JWT** authentication and **PostgreSQL** database integration.

## ✅ Features Implemented (So Far)

* 🔐 User registration & login (with JWT token generation)
* 🧾 Role-based user handling (ADAY, ADMIN, TEKNIK\_SORUMLU)
* 🗂 Gradle project structure with clean DTO usage

---

## 🚀 Technologies Used

* Java 21
* Spring Boot 3.5+
* Spring Security
* Spring Data JPA
* JWT (io.jsonwebtoken)
* PostgreSQL
* Lombok
* Gradle

---

## 🧪 API Endpoints

### 📌 Base URL

```
http://localhost:8080/api/auth
```

---

### 🟢 Register

```
POST /register
Content-Type: application/json
```

#### 🔸 Request Body

```json
{
  "email": "test@example.com",
  "password": "123456",
  "role": "ADAY"
}
```

#### ✅ Response

```json
"User registered successfully"
```

---

### 🟢 Login

```
POST /login
Content-Type: application/json
```

#### 🔸 Request Body

```json
{
  "email": "test@example.com",
  "password": "123456"
}
```

#### ✅ Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Use this token in the `Authorization` header to access secured endpoints:

```
Authorization: Bearer <token>
```

---

## 🛠️ Setup Instructions

1. **Clone the project**:

```bash
git clone https://github.com/your-username/hrproject.git
cd hrproject
```

2. **Start PostgreSQL (locally or via Docker)**

3. **Configure `application.properties`**:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hr_system
spring.datasource.username=devuser
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

4. **Build the project**:

```bash
./gradlew build
```

5. **Run**:

```bash
./gradlew bootRun
```

---

## 📌 Note

This is still a work in progress. The core authentication functionality is working, and other HR features (candidate evaluation, exams, admin panels etc.) will be added iteratively.

---

## 📬 Contact

Developed by Meriç Cengiz [@w3rix](https://github.com/mericcode)! 

---
🔒 All rights reserved.
This project is the property of Meric. Unauthorized use, distribution or modification is prohibited.
---

> This README will grow as the project evolves. Keep pushing 👊
