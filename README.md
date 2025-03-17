# Overview

This repository contains the implementation of the Junior-level requirements for the TCS technical assessment. The functionalities have been developed following the given instructions, focusing on best practices and code maintainability.

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Docker & Docker Compose
- Lombok
- Maven

### Setup Instructions

#### Start the databases with Docker compose
docker-compose up -d

##### Database Access
Two MySQL databases were planned due to the microservices architecture. 
The MySQL instances runs inside a Docker container. You can connect to it using the following credentials:

Host: localhost
Port: 3308 for accounts, 3307 for clients
Username: root
Password: root

### Run Services
Execute ms-accounts(8080) service and ms-clients(8081) service with any Java IDE

### Notes

- The project follows a layered architecture, separating concerns between controllers, services, and repositories.
- Exception handling has been implemented using @ControllerAdvice.
- Comunication between client and account modules is set with Open Feign
- Api Gateway is not finished
- Database scripts are in database folder
