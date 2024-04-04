# Spring Security User and Product Project

A simple project designed to showcase the functionalities of security and authentication using Spring Boot and Spring Security. This example uses Spring Security version 3.0, considering the deprecation of features in previous versions.

## Features

- **MySQL Database Integration:** The application uses a MySQL database to store user data. Data is managed via Spring JPA, and passwords are securely stored using hashed passwords thanks to the PasswordEncoder interface from Spring.

- **REST API Testing with Postman:** The project demonstrates how to test a POST request to create and persist a new user in the project using Postman. This endpoint is accessible to all users.

- **Role-Based Access Control:** Implements secured endpoints that restrict access based on the user's role. Specific endpoints are accessible only to users with ADMIN or USER roles, ensuring appropriate access control and security.

## Screenshots

### MySQL Database and Password Hashing
![MySQL Database and Password Hashing](https://github.com/Devin-Diaz/Spring-Security-Practice/assets/114879075/17cdfad4-a3a5-42d1-b0f0-20dc55beb6af)

### Testing POST Request with Postman
![Testing POST Request with Postman](https://github.com/Devin-Diaz/Spring-Security-Practice/assets/114879075/02184f1e-6549-4bee-9080-5597d3103581)

### ADMIN Role Secured Endpoint
![ADMIN Role Secured Endpoint](https://github.com/Devin-Diaz/Spring-Security-Practice/assets/114879075/38fbbe52-6587-43e8-8c4b-fcd1af29b278)

### USER Role Secured Endpoint
![USER Role Secured Endpoint](https://github.com/Devin-Diaz/Spring-Security-Practice/assets/114879075/f1502584-56d0-4612-88cc-3eba0b42a45b)
