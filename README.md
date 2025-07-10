# 🧠 Smart Health Predictor – Spring Boot Microservices + ML

Smart Health Predictor is a full-stack microservices-based healthcare application that allows users to enter symptoms, receive AI-driven disease predictions, and book doctor appointments — all routed through a scalable, secure API gateway.

> 🔬 ML meets scalable microservices — enabling intelligent diagnosis and seamless healthcare access.

---

## 🔧 Tech Stack

- **Backend:** Spring Boot, Spring Cloud Gateway, Eureka Discovery
- **Machine Learning:** Flask (Python), scikit-learn, RandomForestClassifier
- **Database:** PostgreSQL
- **Inter-service Communication:** Feign Clients + REST
- **Authentication (Coming up):** JWT with Role-Based Access
- **Other:** Lombok, Actuator, Devtools

---

## 🚀 Features

✅ Microservices architecture  
✅ Symptom-based disease prediction using a real ML model  
✅ Appointment booking with doctor-patient linkage  
✅ Real-time notifications per user  
✅ Service discovery via Eureka  
✅ Centralized routing through API Gateway  
✅ PostgreSQL persistence for all services  
✅ Future: Role-based access, Dockerization, frontend UI

---

## 🏗️ Microservices Breakdown

| Service          | Responsibility                         |
|------------------|------------------------------------------|
| `user-service`   | Handles users, roles, notifications       |
| `doctor-service` | Manages doctor info and availability      |
| `appointment-service` | Booking logic, triggers ML prediction |
| `ml-predictor-service` | Flask ML API that returns predictions |
| `api-gateway`    | Entry point, routes requests to services  |
| `discovery-server` | Eureka registry for service lookup      |

---

## ⚙️ How to Run This Project Locally

> Ensure you have **Java 17**, **Maven**, **Python 3**, and **PostgreSQL** installed.

### 🐘 1. Set up PostgreSQL

- Create DBs: `user_db`, `doctor_db`, `appointment_db`
- Set your DB username/password in each service's `application.properties`

### 🐍 2. Run the ML Predictor (Flask)

```bash
cd symptom-ml-flask
pip install -r requirements.txt
python app.py
```

✅ Flask will run on [http://localhost:5000](http://localhost:5000)

---

### ☕ 3. Start Spring Boot Services

Start the services in this order (all ports are customizable):

-  `discovery-server` → `http://localhost:8761`
-  `api-gateway` → `http://localhost:8080`
-  `user-service` → `http://localhost:8081`
-  `doctor-service` → `http://localhost:8082`
-  `appointment-service` → `http://localhost:8083`
-  `ml-predictor-service` → `http://localhost:8084`

 📍 All registered services can be viewed at: 👉 [http://localhost:8761](http://localhost:8761)

---

### 🔍 Sample Endpoints

| API Endpoint                  | Method | Description                         |
|------------------------------|--------|-------------------------------------|
| `/appointments`              | POST   | Book an appointment                 |
| `/appointments`              | GET    | Get all appointments                |
| `/notifications/{userId}`    | GET    | Fetch notifications for a user      |
| `/users/{id}`                | GET    | Get user info                       |
| `/doctors/{id}`              | GET    | Get doctor info                     |
| `/predict` *(ML - Flask)*    | POST   | Send symptoms, get predicted disease|

---

### 📊 Machine Learning Info

- **Model:** `RandomForestClassifier`  
- **Dataset:** Symptom-to-Disease (synthetic/trained)  
- **Accuracy:** ~90% (on test data)  
- **Input:** List of symptoms  
- **Output:** Predicted disease (e.g., `"Diabetes"`)


