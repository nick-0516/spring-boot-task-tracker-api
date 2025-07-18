# Spring Boot Task Tracker API

A complete Java Spring Boot REST API that manages Projects and their Tasks, built to showcase backend development skills using:

**Spring Boot 3.2.4**, **Spring Data JPA**, **Hibernate**  
**Entity relationships**: One-to-Many `Project → Tasks`  
**DTOs** for clean JSON responses  
**Validation** with `@Valid`, `@NotNull`, `@Size`  
**Global exception handling** using `@ControllerAdvice`  
**JWT**-based authentication for secure APIs 
**Swagger UI** for live API documentation & testing  
Fully tested with Postman

---

## Features

- Create, Read, Update, Delete Projects & Tasks
- Link Tasks under Projects with `project_id`
- DTOs to keep API outputs clean (avoid exposing JPA entities)
- Validation to enforce data rules on input
- Global exception handling to return neat JSON errors
- JWT-based authentication with token generation and filtering  
- Auto-generated Swagger documentation at `/swagger-ui`

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.2.4**
- **Spring Data JPA & Hibernate**
- **H2 in-memory database** (easy to demo)
- **Maven**, **Git**
- **Postman** for manual API testing
- **Swagger UI** for interactive docs
- **Spring Security** + **JWT** for secure endpoints 

---

## Live Demo
Hosted on Render:
```
https://task-tracker-api-gr89.onrender.com/swagger-ui/index.html
```
Full documentation and authentication flow are described directly in Swagger UI — simply follow the steps shown there to test secured endpoints.


## Running Locally

```
git clone https://github.com/nick-0516/spring-boot-task-tracker-api.git
cd task-tracker-api
./mvnw spring-boot:run
```

Then open to explore & test API.

```
http://localhost:8080/swagger-ui/index.html
```

## Authentication (JWT)
This API uses JWT-based stateless authentication.

### ➡ Get a Token
```
POST /api/authenticate
Body:
{
  "username": "ravi",
  "password": "pass"
}
```
Copy the token from the response.

## Swagger Authorization
Open http://localhost:8080/swagger-ui/index.html

Click Authorize (button at top-right)

Paste only the raw token (no Bearer prefix needed)

Now you can test secured endpoints like /projects and /tasks

## Welcome Endpoint
To guide new users and simplify API access.

Try:

```
GET /
```
It returns:

```
{
  "message": "Welcome to the Task Tracker API",
  "docs": "/swagger-ui/index.html",
  "authInstructions": "POST /api/authenticate with username='ravi', password='pass' to get a token"
}
```

## Example API calls

### ➡ Create Project
```
POST /projects
{
  "name": "Learn Spring Boot"
}
```

### ➡ Create Task linked to Project
```
POST /tasks
{
  "title": "Build REST controllers",
  "completed": false,
  "project": { "id": 1 }
}
```

### ➡ Get all tasks
```
GET /tasks
```

### ➡ Get tasks by project
```
GET /projects/1/tasks
```


## Validation & Exception Handling

- Uses `@Valid`, `@NotNull`, `@Size` to enforce rules on input.
- Global `@ControllerAdvice` handles exceptions, e.g. if project not found:

```
{
  "timestamp": "2025-06-30T15:20:00",
  "status": 404,
  "error": "Project not found with ID: 99"
}
```

## Swagger & Schemas

- Live documentation & testing at:

```
http://localhost:8080/swagger-ui/index.html
```

- Auto-generates schemas from DTOs, showing constraints like `@Size(min=3)`, `@NotNull`.

---

## About Me

Hi, I'm Ravi — an aspiring backend developer transitioned from a Support Engineering role.  
Strong in Java, Spring Boot, REST APIs, with a passion for building clean, maintainable systems.

---

