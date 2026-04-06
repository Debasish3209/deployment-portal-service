# Deployment Portal Backend

## 📌 Overview
This service allows developers to register a new microservice and automatically generates infrastructure templates.

---

## 🚀 API

### Register Service
**POST** `/deploy/register-service`

Request:
```json
{
  "serviceName": "order-service",
  "teamName": "payments",
  "repoUrl": "https://github.com/example/order-service"
}



💻 Tech Stack
Java
Spring Boot

👨‍💻 Author

Debasish Nayak
