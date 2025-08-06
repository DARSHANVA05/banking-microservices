# Orchestrator Service

This service acts as the central coordinator in a microservices-based banking system. It communicates with other services (such as account, transaction, and notification services) through REST APIs and ActiveMQ messaging. It also includes scheduled tasks for background operations.

---

## Responsibilities

- Coordinate workflows between services
- Handle incoming client requests and dispatch them to respective services
- Communicate asynchronously via ActiveMQ
- Schedule recurring tasks such as input data validation, automated account creation, and message queuing for downstream services.
- Central logging and request tracking

---

## Technologies Used

- Java, Spring Boot
- REST APIs (Spring Web)
- ActiveMQ (Message Queue)
- Spring Scheduler
- PostgreSQL (data persistence)
- Spring Data JPA
- Swagger for API documentation
- Logging (SLF4J / Logback)

---

## Inter-Service Communication

- **Synchronous:** via REST APIs using `RestTemplate` or `WebClient`
- **Asynchronous:** via ActiveMQ topics and queues using `JmsTemplate` or listeners

---

## Scheduled Tasks

  The orchestrator includes background scheduled jobs using `@Scheduled`:
- Periodic data refresh or cleanup
- Trigger automated workflows
- Reset or update account creation system

---

## How to Run

1. Clone the repository and navigate to this service
2. Configure your database, ActiveMQ, and logging in `application.properties`
3. Run the application
