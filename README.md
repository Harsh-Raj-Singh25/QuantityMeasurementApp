# QuantityMeasurementApp
## UC8 - Refactoring Unit Enum to Standalone with Conversion Responsibility

### Overview
> UC8 refactors the architectural design from UC1–UC7 to overcome the disadvantage of embedding the `LengthUnit` enum within the `QuantityLength` class. This extraction eliminates circular dependencies and upholds the **Single Responsibility Principle (SRP)** by centralizing unit-related conversion logic within the standalone unit enum. The `QuantityLength` class (Length) is simplified to delegate all conversion operations to the unit, establishing a scalable pattern for future measurement categories.
---
### Date : 21 Feb 2026
* Refactored the design to move `LengthUnit` to a standalone top-level class.
* Implemented the **Delegation Pattern** to shift conversion responsibility from the domain class to the unit enum.
---
### Performed operations in the following steps:
* **Step 1 – Enum Refactoring & Responsibility Assignment**
  > 1. Moved `LengthUnit` from inside `Length` to a standalone top-level enum class.
  > 2. Implemented `convertToBaseUnit(double value)` to convert a value in the current unit to feet (base unit).
  > 3. Implemented `convertFromBaseUnit(double baseValue)` to convert a base unit value back to the specific unit.
* **Step 2 – Quantity Class Simplification**
  > 1. Removed all internal conversion logic and hardcoded factors from the `Length` class.
  > 2. Updated `convertTo()`, `add()`, and `equals()` to delegate all mathematical operations to the unit's conversion methods.
  > 3. Refocused the class solely on value comparison and arithmetic orchestration.
* **Step 3 – Backward Compatibility & Verification**
  > 1. Ensured the public API of `Length` remained unchanged to support existing client code without modification.
  > 2. Verified that all test cases from UC1–UC7 pass identically using the new architecture.
  > 3. Confirmed that the design pattern is ready for seamless integration of new categories like `WeightUnit` and `VolumeUnit`.
---
### Features
* **Single Responsibility Principle (SRP):** Each class has a single, well-defined responsibility; `LengthUnit` handles conversions, while `Length` handles comparisons and arithmetic.
* **Separation of Concerns:** Unit-specific logic is isolated in the enum, while domain logic is isolated in the quantity class, ensuring changes to one do not affect the other.
* **Dependency Inversion:** `Length` depends on the unit abstraction rather than concrete implementation, allowing units to be extended without modifying the quantity class.
* **Circular Dependency Elimination:** Extracting `LengthUnit` prevents circular references, allowing future categories like Weight and Volume to coexist cleanly.
* **Delegation Pattern:** `Length` delegates conversion responsibility to the unit via methods like `unit.convertToBaseUnit()`, improving code clarity and reducing complexity.
* **Scalability Pattern:** The refactored design serves as a template for new measurement categories like WeightUnit and VolumeUnit.
---
### Concepts Learned in UC8
* **Encapsulation of Conversion Logic:** Hiding internal formulas within unit methods so external classes only interact through a public API.
* **Refactoring Best Practices:** Improving internal structure while preserving existing functionality and maintaining backward compatibility.
* **Java Enum Capabilities:** Leveraging enums to encapsulate both data (factors) and behavior (conversion methods) in a thread-safe manner.
* **Architectural Scalability:** Established a foundation for enterprise-grade measurement systems that prevents code duplication across different Quantity types.
---
### Example Output
* **Input**: `Quantity(1.0, FEET).convertTo(INCHES)` -> **Output**: `Quantity(12.000, INCHES)`
* **Input**: `Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET)` -> **Output**: `Quantity(2.000, FEET)`
* **Input**: `LengthUnit.INCHES.convertToBaseUnit(12.0)` -> **Output**: `1.0` (Feet)
---
* **Based on the defined test cases, the refactored design established a foundation for enterprise-grade measurement systems.**
* **Pushed the refactored code to the repository.**
* **Code :** [UC 8](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/edit/feature/UC8-StandaloneUnit)
