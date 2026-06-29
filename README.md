# MediTrack – Microservices-Based Healthcare Appointment System

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Microservices](https://img.shields.io/badge/Architecture-Microservices-orange)
![Docker](https://img.shields.io/badge/Container-Docker-blue)
![AWS](https://img.shields.io/badge/Cloud-AWS-orange)
![CI/CD](https://img.shields.io/badge/CI/CD-Bitbucket%20Pipelines-purple)
![License](https://img.shields.io/badge/License-MIT-green)

---
## Overview

MediTrack is a production-style backend system built using Spring Boot microservices that enables secure user management, doctor availability tracking, and appointment scheduling. It follows modern distributed architecture with centralized authentication, API Gateway routing, event-driven communication, and cloud deployment.

---

## Architecture Diagram

<img width="6352" height="3764" alt="image" src="https://github.com/user-attachments/assets/23c2c070-a77e-4e1b-a453-6c95d0ce96f7" />


### 🧾 Registration Flow

Client → Auth Server → Auth DB  
→ Feign → User Service → User DB  

---

### Login Flow

Client → Auth Server → JWT Token  

---

### API Request Flow

Client → API Gateway → Microservice  
→ JWT Validation → Authorization  

---

### Service-to-Service Flow

Service → Auth Server → Token  
→ Feign → Target Service  

---

## Security

- OAuth2 Authorization Server (Spring Authorization Server)
- JWT-based authentication and authorization
- Custom claims (`authorities`) added to token
- Role-based access using `@PreAuthorize`
- Resource server validation using `issuer-uri`
- Service-to-service authentication using OAuth2 client credentials flow  

---

## Core Features

- Service discovery using Eureka for dynamic microservice registration  
- User registration and authentication  
- Doctor availability management  
- Appointment booking and scheduling  
- API Gateway routing
- Inter-service communication using OpenFeign clients  
- Event-driven communication using Kafka for asynchronous workflows (e.g., notifications, inter-service communication)  

---

## Resilience & Performance

- Resilience4j Circuit Breaker with fallback support  
- Redis Rate Limiting for API protection at Gateway  
- Redis Caching for performance optimization in services   

---

## Observability & Monitoring

- Spring Boot Actuator (health, metrics)  
- Prometheus + Grafana for real-time metrics and dashboards  
- Zipkin for distributed request tracing across microservices  
- ELK Stack for centralized logging and debugging  

---

## Deployment & DevOps

- Dockerized microservices  
- CI/CD pipeline using Bitbucket Pipelines  
- AWS ECS (Fargate) deployment  
- Docker images stored in AWS ECR  
- Automated deployment using ECS task definitions

---

## Deployment Flow

1. Build application JAR  
2. Build Docker image  
3. Push image to AWS ECR  
4. Register ECS Task Definition  
5. Update ECS Service  
6. Automatic deployment via CI/CD pipeline  

---

## Tech Stack

| Category | Technology |
|----------|-----------|
| Language | Java 21 |
| Backend | Spring Boot |
| Security | OAuth2, JWT |
| Gateway | Spring Cloud Gateway |
| Discovery | Eureka |
| ORM | JPA / Hibernate |
| Communication | Feign |
| Database | PostgreSQL |
| Messaging | Kafka |
| Cache | Redis |
| Resilience | Resilience4j |
| DevOps | Docker, AWS ECS, Bitbucket Pipelines |
| Monitoring | Prometheus, Grafana, Zipkin, ELK |

---

## 📂 Project Structure

```
meditrack/
├── api-gateway
├── auth-server
├── user-service
├── availability-service
├── appointment-service
├── notification-service
├── discovery-server
├── docker-compose.yml
└── docs/
```
---

## Highlights

- Production-style microservices architecture  
- OAuth2 + JWT security implementation  
- API Gateway with rate limiting  
- Kafka-based event-driven communication  
- Circuit breaker and resilience patterns  
- Full observability (metrics, tracing, logging)  
- CI/CD pipeline with AWS ECS deployment
- Designed using domain-based microservice separation with independent databases per service  

---

## Future Improvements

- AWS Application Load Balancer (ALB)  
- AWS RDS integration  
- Auto-scaling ECS services  
- Distributed transactions (Saga pattern)  
- Secrets management (AWS Secrets Manager)  

---

## Author

**Tejesh Ankem**

Backend Developer | Java | Spring Boot | Microservices | AWS
- LinkedIn: www.linkedin.com/in/tejesh-ankem-a3772a1a3
- GitHub: [https://github.com/tejesh6835](https://github.com/tejesh6835/ )


---
