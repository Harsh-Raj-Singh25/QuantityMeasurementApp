# QuantityMeasurementApp
## UC16 - Database Integration with JDBC

### Overview
> UC16 elevates the application to an enterprise-grade system by introducing persistent relational storage via **JDBC (Java Database Connectivity)**. This architectural leap replaces the previous file-based serialization with a robust database repository, enabling concurrent access, complex SQL querying, and reliable audit trails.

---

### Features
* **Relational Persistence:** Implements `QuantityMeasurementDatabaseRepository` to store operation history in H2 (Development) or MySQL/PostgreSQL (Production).
* **Maven Standardization:** Adopts the professional Maven directory layout (`src/main/java`, `src/test/java`) for better dependency and lifecycle management.
* **Connection Pooling:** Integrates a custom `ConnectionPool` to manage database resources efficiently and prevent connection exhaustion.
* **SQL Injection Protection:** Utilizes parameterized `PreparedStatement` for all database interactions to ensure high security.
* **Structured Logging:** Replaces `System.out.println` with the **SLF4J** facade and **Logback** for professional application tracing.
* **Environment Configuration:** Uses `application.properties` to allow seamless switching between Cache and Database repositories without code changes.

---

### Date : 17 Mar 2026
* Migrated the project to a Maven-based structure.
* Implemented JDBC repository and database schema management.

---

### Project Architecture & Data Flow

* **Step 1 – Professional Project Structure**
  > 1. Organized code into layered packages: `.controller`, `.services`, `.repository`, `.entity`, and `.exception`.
  > 2. Configured `pom.xml` with dependencies for JDBC drivers, JUnit, and Maven plugins.

* **Step 2 – Database Utility Implementation**
  > 1. Created `ApplicationConfig` to load environment-specific properties from `application.properties`.
  > 2. Developed a thread-safe `ConnectionPool` to handle resource acquisition and release.



* **Step 3 – Persistence Layer Refactoring**
  > 1. Enhanced `IQuantityMeasurementRepository` with methods for advanced querying (`getMeasurementsByType`, `getTotalCount`).
  > 2. Implemented the Database Repository with transaction support and automatic resource cleanup.

---

### Concepts Learned in UC16
* **Maven Lifecycle:** Managing the build process through `clean`, `compile`, `test`, and `package` phases.
* **JDBC Best Practices:** Handling `ResultSet`, `Statement`, and `Connection` objects securely and efficiently.
* **Connection Management:** Understanding the performance benefits of pooling over manual connection handling.
* **Schema Management:** Using SQL scripts (`schema.sql`) to define and initialize database structures.

---

### Example SQL Audit
| ID | OPERAND_1 | OPERAND_2 | OPERATION | RESULT | TIMESTAMP |
|:---|:---|:---|:---|:---|:---|
| 101 | 1.0 FEET | 12.0 INCHES | COMPARE | true | 2026-03-17 11:45 |
| 102 | 100.0 C | 50.0 C | ADD | ERROR | 2026-03-17 11:46 |

---

* **The system is now fully persistent and scalable, meeting enterprise-level standards for data management.**
* **Pushed the JDBC Integration and Maven Refactor to the repository.**
* **Code :** [UC 16 JDBC Integration](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC16-JDBCIntegration)