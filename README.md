# Quantity Measurement Application
## A Generic, Type-Safe Measurement Engine in Java

### Project Overview
The **Quantity Measurement Application** is a robust Java-based engine designed to handle diverse physical quantities including **Length, Weight, Volume, and Temperature**. The project demonstrates an architectural evolution from basic object comparison to a highly scalable, generic system that adheres to **SOLID principles**, **Interface Segregation**, and the **DRY (Don't Repeat Yourself)** principle.
This project is build on Incremental model & Test Driven Development Model.
The engine supports cross-unit arithmetic, non-linear temperature conversions, and strict category isolation, ensuring that logically incompatible measurements (e.g., adding weight to length) are prevented at both compile-time and runtime.

---

### Architectural Journey (UC Summary)

| UC | Feature | Focus | Link |
|:---|:---|:---|:---|
| **01** | Feet Equality | Object Equality & Encapsulation | [View UC01](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC1-FeetEquality) |
| **02** | Inches Support | Handling Multiple Units | [View UC02](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC2-InchEquality) |
| **03** | DRY Refactor[Generic Length] | Unit Enums & Normalization | [View UC03](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC3-GenericLength) |
| **04** | Yard & Centimeter Support | Scaling Linear Comparisons | [View UC04](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC4-YardEquality) |
| **05** | Unit Conversion | Conversion Logic and Validation | [View UC05](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC5-UnitConversion) |
| **06** | Addition Logic |  Logic Introduction | [View UC06](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC6-UnitAddition) |
| **07** | Target Unit Addition |  Addition with Target Unit Specification | [View UC07](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition) |
| **08** | Standalone Unit | Separation of enum Logic in a separate enum class | [View UC08](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC8-StandaloneUnit) |
| **09** | Weight Measurement | Cross-Unit Weight Arithmetic & conversions | [View UC09](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC9-Weight-Measurement) |
| **10** | Generic Quantity | Bounded Type Parameters (Generics) with multi-category support | [View UC10](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC10-Generic-Quantity-Class) |
| **11** | Volume Support | Scalability Test of Generics | [View UC11](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC11-Volume-Measurement) |
| **12** | Sub/Div Ops | Non-Commutative Arithmetic | [View UC12](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC12-SubtractionDivisionOperations) |
| **13** | DRY Refactor | Centralized Arithmetic Logic with Functional Lambdas & Enum Dispatch | [View UC13](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC13-CentralizedArithmeticLogic) |
| **14** | Temperature | Non-Linearity & Capability Check | [View UC14](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurement) |

---

### Core Use Case Details

#### UC01 - UC02: Basic Equality
Established the foundation of value-based equality. We moved away from primitive comparison to encapsulated objects, ensuring that `1.0 ft` equals another `1.0 ft` object based on value rather than memory reference.

#### UC03 - UC05: Normalization & Scaling
Introduced the **LengthUnit Enum**. By establishing a base unit (Length), the system gained the ability to compare different units.
* **Logic:** `Value * Factor = BaseValue`.
* **Result:** `1 Yard == 3 Feet == 36 Inches`.



#### UC06 - UC09: Arithmetic & Weight Integration
Extended the engine to support mathematical operations. 
* **Addition:** `2 Inches + 5 cm = 3.97 Inches`.
* **Weight Domain:** Integrated Grams, Kilograms, and Tonnes, applying the same normalization logic used for Length.

#### UC10 - UC11: The Power of Generics
Refactored the core into `Quantity<U extends IMeasurable>`. This move eliminated category-specific classes. 
* **Impact:** Adding **Volume** (Litres, Millilitres, Gallons) required zero changes to the core `Quantity` class, proving the system's scalability.



#### UC12 - UC13: Advanced Arithmetic & DRY 2.0
Introduced Subtraction and Division. 
* **Refactor:** Centralized all math into a private `ArithmeticOperation` enum using **Java Lambdas**. 
* **DRY Logic:** Validation (null checks, category checks) is performed once in a helper method, reducing the bug surface by over 60%.

#### UC14: Non-Linearity & Capability Guardrails
The ultimate test of the architecture. Temperature (Celsius/Fahrenheit) introduced non-linear formulas ($y = mx + c$).
* **ISP Implementation:** Refactored the `IMeasurable` interface to allow Temperature to "opt-out" of arithmetic operations while keeping Length/Weight/Volume "opted-in."



---

### Key Technical Concepts Applied
* **TDD (Test Driven Development):** Over 100+ JUnit test cases ensuring 100% coverage of edge cases.
* **Interface Segregation (ISP):** Using default methods to handle optional unit capabilities.
* **Functional Programming:** Using `DoubleBinaryOperator` and `Function<Double, Double>` for math dispatching.
* **Bounded Generics:** Ensuring type safety across measurement domains.
* **Normalization:** Reducing all inputs to a "Base Unit" for consistent comparison.

---

### Getting Started
1. **Prerequisites:** Java 11+ and JUnit 4/5.
2. **Build:** Clone the repository and run `mvn clean install`.
3. **Execution:** Use `QuantityMeasurementApp.java` to see the demonstration of all 14 Use Cases in action.

---

*Developed with a focus on clean code, maintainability, and mathematical precision.*
