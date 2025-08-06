# Account Service - Microservice Component

This microservice handles account-related operations such as **client validation** and **account creation**. It processes messages from an **ActiveMQ** queue and persists validated data into a **PostgreSQL** database.

## Tech Stack

- Java, Spring Boot
- Spring Data JPA, PostgreSQL
- ActiveMQ (Message Queue)
- Spring JMS (Message Consumer)
- Spring Scheduler for periodic tasks
- REST APIs (Spring MVC)
- SLF4J Logging
- Swagger for API Documentation

## Core Features

- Listens to `validationQueue` and processes incoming client data
- Logs message details and handles data persistence
- Validates input data (e.g., contact number length)
- Exception handling via custom exception classes
- Future support for Dead Letter Queues (DLQ) and retry mechanisms
- PostgreSQL integration with JPA and Hibernate

## ️ How to Run

1. **Clone the Repository**
    - Open a terminal and run:
      ```bash
      git clone <repository-url>
      cd account-service
      ```

2. **Configure `application.properties`**
    - Open `src/main/resources/application.properties`
    - Update with your local database and ActiveMQ credentials:
      ```properties
      # PostgreSQL Configuration
      spring.datasource.url=jdbc:postgresql://localhost:5432/microservice
      spring.datasource.username=your_username
      spring.datasource.password=your_password
 
      # ActiveMQ Configuration
      spring.activemq.broker-url=tcp://localhost:61616
      spring.activemq.username=admin
      spring.activemq.password=admin
      spring.activemq.packages.trust-all=true
      ```

3. **Start Apache ActiveMQ (Message Queue)**
    - Download from: https://activemq.apache.org/
    - Start the broker:
        - On Linux/macOS: `bin/activemq start`
        - On Windows: run the ActiveMQ service or `activemq.bat`
    - Access the ActiveMQ Web Console at: [http://localhost:8161](http://localhost:8161)
    - Default credentials: `admin / admin`

4. **Run the Spring Boot Application**

5. **Verify Functionality**
    - The service will:
        - Automatically connect to the `validationQueue`
        - Log and validate any client data received from the queue
        - Persist valid entries to the PostgreSQL database
        - Log or handle invalid entries via exception handling

