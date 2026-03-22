# QuantityMeasurementApp
## UC17 - Spring Boot REST API & Spring Data JPA Integration

### Overview
> UC17 represents a major architectural paradigm shift. The application has been fully migrated from a standalone, manual JDBC-driven Java program into a modern, enterprise-grade **Spring Boot RESTful Microservice**. 

This refactoring replaces raw SQL and manual connection pooling with **Spring Data JPA**, exposes the core business logic over HTTP via **REST Controllers**, and introduces centralized error handling and interactive API documentation.

---

### Key Technical Implementations
* **Spring Boot Auto-Configuration:** Replaced manual Tomcat server setup and data source initialization with Spring Boot's embedded server and auto-configuration properties.
* **REST Controllers (`@RestController`):** Exposed business logic through standard HTTP methods (`POST /compare`, `POST /add`, `GET /history`).
* **Spring Data JPA (`@Repository`):** Eliminated raw JDBC boilerplate. Queries are now generated dynamically via `JpaRepository` interfaces.
* **Global Exception Handling (`@ControllerAdvice`):** Centralized error catching to ensure clients always receive standardized, readable JSON error responses (e.g., 400 Bad Request) instead of server crash logs.
* **Data Transfer Objects (DTOs) with Validation:** Integrated `spring-boot-starter-validation` (`@NotNull`, `@Valid`) to ensure data integrity before it reaches the service layer.
* **Swagger/OpenAPI (`springdoc-openapi`):** Auto-generated interactive API documentation accessible directly from the browser.

---

### Date : 15 Mar 2026
* Migrated the Maven project to the Spring Boot ecosystem.
* Implemented JPA Entities and Repository patterns.
* Deployed global exception handlers and Swagger UI.

--

### API Endpoints
| HTTP Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/quantities/compare` | Evaluates if two input quantities are equivalent. |
| `POST` | `/api/v1/quantities/add` | Adds two compatible quantities and returns the normalized result. |
| `GET` | `/api/v1/quantities/history/operation/{op}` | Retrieves audit history filtered by operation type. |
| `GET` | `/swagger-ui.html` | Access the interactive API testing interface. |
---
### Testing Strategy
* **Controller Layer:** Utilized `@WebMvcTest` and `MockMvc` to test HTTP routing and JSON serialization in isolation.
* **Integration Testing:** Deployed `@SpringBootTest` with `TestRestTemplate` to verify end-to-end flows against the embedded H2 database.
---
### Concepts Mastered in UC17
* **Inversion of Control (IoC) & Dependency Injection:** Leveraging `@Autowired`, `@Service`, and `@RestController` for seamless component wiring.
* **Object-Relational Mapping (ORM):** Mapping Java classes to database tables using `@Entity`, `@Id`, and `@GeneratedValue`.
* **API Design:** Building intuitive, predictable REST endpoints.
* **Data Validation:** Using Jakarta Validation constraints to secure API inputs.

---

* **The system is now a scalable, production-ready backend service.**
* **Code :** [UC 17 Spring Boot Backend](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC17-SpringBoot)