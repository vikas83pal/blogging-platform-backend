# User Management API

This is a Spring Boot-based User Management API that provides functionalities to create, update, delete, and fetch user details.

## Features
- Create a new user
- Fetch user details by ID
- Update user information
- Delete a user

## Technologies Used
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL/PostgreSQL (or any preferred database)
- REST API

## Installation

### Prerequisites
- Java 17+
- Maven
- MySQL or PostgreSQL

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/user-management-api.git
   cd user-management-api
   ```
2. Configure the `application.properties` file for your database settings.
3. Build and run the application using:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints

### Create a New User
**POST** `/api/users`  
**Request Body**:
```json
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "securepassword",
  "about": "Software Developer"
}
```
**Response**:
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com",
  "about": "Software Developer"
}
```

### Get User by ID
**GET** `/api/users/{id}`  

### Update User
**PUT** `/api/users/{id}`  
**Request Body** (Fields to update):
```json
{
  "name": "Updated Name",
  "email": "updated@example.com",
  "about": "Updated About Info"
}
```

### Delete User
**DELETE** `/api/users/{id}`  

## Error Handling
The API uses standard HTTP response codes to indicate success or failure.

- `404 Not Found`: If a user is not found.
- `500 Internal Server Error`: If an error occurs during database operations.

## License
This project is under Maintance if you want to work please make a pull request with changes with brach bug-fix or feature
