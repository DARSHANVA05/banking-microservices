# Banking Microservices System

This project is a modular banking system consisting of two Spring Boot microservices: **Account Service** and **Orchestrator Service**. The system uses Java, PostgreSQL, and ActiveMQ to demonstrate inter-service communication via REST APIs and message queues.

---

## Prerequisites

To run these services, you'll need the following installed:

* **Java 17+**
* **Maven**
* **PostgreSQL**
* **ActiveMQ (v5.x)**
* **Git**
* **Postman / cURL** for API testing

---

## 1. Orchestrator Service

The **Orchestrator Service** acts as the central coordinator for the entire system. It handles incoming client requests and dispatches them to the appropriate services, like the Account Service, using both synchronous REST APIs and asynchronous ActiveMQ messaging. It also manages scheduled tasks for background operations.

### Key Features

* **Central Coordination:** Manages workflows between all microservices.
* **Request Dispatch:** Directs client requests to the correct service.
* **Asynchronous Communication:** Uses ActiveMQ queues and topics for reliable messaging.
* **Scheduled Tasks:** Runs periodic jobs for validation, account creation, and queue reprocessing.
* **Centralized Logging:** Tracks and logs all requests for monitoring.

### Technologies Used

* Java, Spring Boot
* REST APIs (Spring Web)
* ActiveMQ (Message Queue)
* Spring Scheduler
* PostgreSQL & Spring Data JPA
* Swagger for API documentation
* SLF4J / Logback for logging

### Running the Service

1.  **Clone the repository** and navigate to the `orchestrator-service` directory.
    ```bash
    git clone <repository-url>
    cd orchestrator-service
    ```
2.  **Configure `application.properties`** with your database and ActiveMQ credentials.
    ```properties
    # PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/microservice
    spring.datasource.username=your_username
    spring.datasource.password=your_password

    # ActiveMQ
    spring.activemq.broker-url=tcp://localhost:61616
    spring.activemq.username=admin
    spring.activemq.password=admin
    spring.activemq.packages.trust-all=true
    ```
3.  **Start ActiveMQ** on your local machine.
4.  **Run the application** from the command line.
    ```bash
    mvn spring-boot:run
    ```
5.  **Access API Docs** at `http://localhost:<port>/swagger-ui.html`.

---

## 2. Account Service

The **Account Service** handles all account-related operations, including client validation, account creation, and data persistence. It listens to a dedicated message queue for new client data, validates it, and saves the information to a PostgreSQL database.

### Key Features

* **Queue Listener:** Listens to the `validationQueue` for incoming client data.
* **Data Validation:** Verifies client data for correctness (e.g., contact number length, required fields).
* **Data Persistence:** Persists valid client data into a PostgreSQL database using Spring Data JPA.
* **Error Handling:** Uses custom exception classes for robust error management.
* **Scheduled Jobs:** Runs background tasks for validation and data cleanup.

### Technologies Used

* Java, Spring Boot
* Spring Data JPA
* PostgreSQL
* ActiveMQ (Message Queue)
* Spring JMS
* Spring Scheduler
* REST APIs (Spring MVC)
* Swagger for API documentation
* SLF4J for logging

### Running the Service

1.  **Clone the repository** and navigate to the `account-service` directory.
    ```bash
    git clone <repository-url>
    cd account-service
    ```
2.  **Configure `application.properties`** with your database and ActiveMQ credentials, similar to the Orchestrator Service.
3.  **Start ActiveMQ** on your local machine (if not already running).
4.  **Run the application** from the command line.
    ```bash
    mvn spring-boot:run
    ```
5.  **Access API Docs** at `http://localhost:<port>/swagger-ui.html`.

### How to Test

To test the Account Service's queue-listening functionality:

1.  Open the **ActiveMQ Web Console** at `http://localhost:8161` (default credentials: `admin`/`admin`).
2.  Go to the `Queues` tab and select the `validationQueue`.
3.  Use the console to **push a test message** with client data.
4.  Monitor the logs of the running Account Service to see the message being consumed and processed, and check your PostgreSQL database for the new record.