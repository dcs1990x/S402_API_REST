# Task S4.02 – REST API with Spring Boot

## 📋 Project Overview
This goal of this project is to develop a REST API using Spring Boot that performs full CRUD operations (Create, Read, Update, Delete) on an entity named Fruit. The application uses an in-memory H2 database, ideal for development and testing environments due to its simplicity and speed.
It follows best practices such as abstraction programming, DTO usage, input validation and centralized exception handling.

## 💻 Technologies Used

- Java 21
- Spring Boot 3.5.7
- Spring Web
- Spring Data JPA
- H2 Database
- Spring Boot DevTools
- Jakarta Validation API
- Lombok
- JUnit 5 & Mockito
- Docker (multi-stage build for production-ready images)

## 🍎 Fruit Stock Manager API

This backend application allows you to manage a fruit store’s stock by registering each fruit with its name and weight (in kilos).

## 📦 Project Structure

The application follows the MVC (Model–View–Controller) architecture and is organized into the following packages:

cat.itacademy.s04.t02.n01.controllers   
cat.itacademy.s04.t02.n01.model   
cat.itacademy.s04.t02.n01.services   
cat.itacademy.s04.t02.n01.repository   
cat.itacademy.s04.t02.n01.exception   

## 🧩 Main Components
### Fruit (Model)

Entity class that represents a fruit in the H2 database. Includes validation annotations to ensure data integrity:

```
@Entity
@Data
@NoArgsConstructor
@Table(name="fruits")
public class Fruit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NonNull
    @NotBlank
    @Pattern(regexp = "^[^0-9]*$", message = "The name cannot contain any numbers.")
    private String name;

    @Column(name = "weight")
    @NonNull
    @PositiveOrZero(message="Weight cannot be neither 0 nor negative.")
    private Float weight;

    public Fruit(String name, Float weight){
        this.name = name;
        this.weight = weight;
    }
}
```

### FruitRepository

Extends JpaRepository<Fruit, Integer> and provides basic CRUD operations automatically handled by Spring Data JPA.

### Service Layer

Contains the business logic for fruit management. A different service class is created for each endpoint. Responsible for validating data, interacting with the repository, and throwing custom exceptions when necessary.

### FruitController

Defines the REST endpoints for interacting with the API.

Method	Endpoint	Description
POST	/fruits	Create a new fruit
GET	/fruits	Retrieve all fruits
GET	/fruits/{id}	Retrieve a fruit by ID
PUT	/fruits/{id}	Update an existing fruit
DELETE	/fruits/{id}	Delete a fruit by ID

### 🧠 User Stories & Acceptance Criteria

Register a new fruit:   
✅ Returns 201 Created with the created fruit.   
⚠️ Returns 400 Bad Request if data is invalid (empty name or invalid weight).   

Get all fruits:   
✅ Returns 200 OK with a JSON array of all fruits.   
Returns an empty array with 200 OK if no records exist.   

Get a specific fruit by ID:   
✅ Returns 200 OK with fruit details if ID exists.   
⚠️ Returns 404 Not Found if the ID does not exist.   

Update an existing fruit:   
✅ Returns 200 OK with the updated fruit.   
⚠️ Returns 404 Not Found if the ID does not exist.   
⚠️ Returns 400 Bad Request if validation fails.   

Delete a fruit:   
✅ Returns 204 No Content on successful deletion.   
⚠️ Returns 404 Not Found if the fruit ID does not exist.   

## ⚙️ Project Configuration

Main application.properties setup:   
```
spring.application.name=fruit-api-h2

# ========== SERVER ==========
server.port=9000

# ========== DATASOURCE ==========
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# ========== H2 CONSOLE ==========
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=true

# ========== JPA ==========
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# ========== SQL INIT ==========
spring.sql.init.mode=always
```
Initial data file (data.sql):   
```
INSERT INTO fruits (name, weight_in_kilos) VALUES
('Apple', 2),
('Pear', 3),
('Orange', 4),
('Banana', 1);
```
## 🚀 How to Run

- Clone the repository:   
```
git clone https://github.com/dcs1990x/S402_API_REST
```

- Build the project:   
```
mvn clean package -DskipTests
```

- Run the application:   
```
java -jar target/fruit-api-h2-0.0.1-SNAPSHOT.jar
```

- Access the H2 Console:   

URL: http://localhost:9000/h2-console   
JDBC URL: jdbc:h2:mem:testdb   
User: sa   
Password: (leave blank)   

- Available Endpoints:

GET http://localhost:9000/fruits   
GET http://localhost:9000/fruits/{id}   
POST http://localhost:9000/fruits   
PUT http://localhost:9000/fruits/{id}   
DELETE http://localhost:9000/fruits/{id}   

## 🧪 Testing   

Tests are written before implementing each functionality, using:   

- @SpringBootTest and MockMvc for integration testing.  
- Mockito for isolated unit tests on the service layer.   

## 🐳 Dockerfile (Multi-Stage Build)   
```
# Stage 1: build .jar
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests clean package

# Stage 2: run .jar
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]
```

This multi-stage Docker build reduces image size and separates the build and runtime phases for optimal production deployment.   

## 👨‍💻 Author   

Developed by Daniel Caldito Serrano as part of the Java Back-end Development Bootcamp organized by IT Academy.   
