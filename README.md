# QuantityMeasurementApp
## UC15 - N-Tier Architecture Refactoring

### Overview
> UC15 transforms the monolithic Quantity Measurement Application into a professional **N-Tier Architecture**. By separating concerns into distinct layers—**Controller, Service, Repository, and Entity**—the system achieves high maintainability, loose coupling, and readiness for future scalability such as REST API integration.

---

### Architectural Layers
* **Application Layer:** Serves as the bootstrap entry point to initialize the system and coordinate the startup sequence.
* **Controller Layer:** Acts as a Facade, orchestrating user interactions and delegating complex business logic to the service layer.
* **Service Layer:** The core "Brain" of the application, encapsulating all conversion rules, arithmetic logic, and category validation.
* **Repository Layer:** Provides an abstraction for data access, currently implemented as a **Singleton Cache** with disk-based serialization for operation history.
* **Entity/Model Layer:** Defines standardized POJOs and DTOs (`QuantityDTO`, `QuantityModel`) to ensure consistent data flow between layers.

---

### Key Technical Implementations
* **Dependency Injection:** The service layer receives its repository dependency via constructor injection, facilitating easier unit testing and mocking.
* **Singleton Pattern:** Ensures a centralized point of access for quantity measurement data through the `QuantityMeasurementCacheRepository`.
* **Data Transfer Objects (DTOs):** Utilizes `QuantityDTO` to decouple internal service models from external representation, standardizing the API contract.
* **Interface Segregation (ISP):** Defined specific interfaces (`IQuantityMeasurementService`, `IQuantityMeasurementRepository`) to allow for flexible implementation swaps without affecting the overall structure.

---

### Date : 9 March 2026
* Refactored monolithic code into a 4-tier architectural structure.
* Implemented persistent caching for measurement operations.

---

### Performed operations in the following steps:

* **Step 1 – Designing Data Contracts**
  > 1. Developed `QuantityDTO` for layer communication and `QuantityMeasurementEntity` for persistent storage of operation results.
  > 2. Created the generic `QuantityModel<U>` to maintain internal type safety within the service layer.

* **Step 2 – Implementing the Service & Repository**
  > 1. Developed `QuantityMeasurementServiceImpl` to orchestrate business logic using normalized units.
  > 2. Built a Singleton Cache Repository that uses Java Serialization to save operation history to disk.



* **Step 3 – Orchestrating via Controller**
  > 1. Created `QuantityMeasurementController` to handle requests and format results for the end-user.
  > 2. Integrated centralized error handling to convert exceptions (like Temperature Addition) into readable error entities.

---

### Concepts Learned in UC15
* **N-Tier Architecture:** Understanding the benefits of separating UI, Logic, and Data Persistence.
* **Design Patterns:** Practical application of **Singleton**, **Factory**, **Facade**, and **Dependency Injection**.
* **POJO & DTO Usage:** Learning to use lightweight data carriers to decouple system components.
* **Persistent Storage:** Implementing object serialization to maintain application state across restarts.

---

### Example Data Flow (Addition)
1. **Controller**: Accepts `QuantityDTO` and calls `service.add()`.
2. **Service**: Maps DTO to `QuantityModel`, validates categories, and performs math.
3. **Repository**: Saves a `QuantityMeasurementEntity` containing operands and the result.
4. **Controller**: Receives result DTO and presents it to the user.

---

* **The application now stands as a production-ready architectural template, ready for integration with frameworks like Spring Boot.**
* **Pushed the N-Tier Refactor implementation to the repository.**
* **Code :** [UC 15 N-Tier Architecture](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC15-NTierArchitecture)
