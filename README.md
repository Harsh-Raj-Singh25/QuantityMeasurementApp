# QuantityMeasurementApp
## UC9 - Weight Measurement Equality, Conversion, and Addition
### Overview
> UC9 extends the application to support **Weight measurements** (Kilogram, Gram, Pound) as a distinct category. This implementation demonstrates that the generic design patterns established in UC1–UC8 scale seamlessly to new measurement types. The architecture reinforces the **Single Responsibility Principle** by delegating conversion logic to a standalone `WeightUnit` enum while maintaining strict **Category Type Safety** to ensure weights and lengths are never compared or added.
---
### Date : 21 Feb 2026
* Implemented the `WeightUnit` standalone enum and `QuantityWeight` domain class.
* Established category-level isolation to prevent cross-type interoperability.
---
### Performed operations in the following steps:
* **Step 1 – Creating Standalone WeightUnit Enum**
  > 1. Defined units: **KILOGRAM** (Base: 1.0), **GRAM** (0.001), and **POUND** (0.453592).
  > 2. Implemented `convertToBaseUnit()` and `convertFromBaseUnit()` to centralize weight conversion factors.
* **Step 2 – Implementing QuantityWeight Class**
  > 1. Mirrored the refactored design of `Length` to maintain consistency across the application.
  > 2. Implemented `equals()` with epsilon-based comparison to handle floating-point precision for Pounds and Grams.
  > 3. Provided overloaded `add()` methods for both implicit and explicit target unit specification.
* **Step 3 – Category Isolation & Verification**
  > 1. Added explicit checks in the `equals()` method to reject any comparison between a `QuantityWeight` and a `Length` object.
  > 2. Verified mathematical accuracy for cross-unit equality (e.g., 1 kg = 1000 g) and round-trip conversions.
---
### Features
* **Independent Weight Category:** Supports Kilograms (Base), Grams, and Pounds with independent conversion logic.
* **Category Type Safety:** Implements strict runtime checks (`getClass()`) to ensure that Weight and Length measurements are treated as incomparable categories (e.g., 1 kg ≠ 1 foot).
* **Delegation Pattern:** The `QuantityWeight` class delegates all mathematical transformations to the `WeightUnit` enum, keeping the domain class focused on state.
* **Scalable Architecture:** Validates that the system can expand to multiple categories (Weight, Volume, Temperature) without refactoring existing Length logic.
* **Immutability:** Ensures thread-safety and data integrity by returning new instances for every conversion and addition operation.
---
### Concepts Learned in UC9
* **Multiple Measurement Categories:** Designing systems that accommodate different physical quantities while maintaining strict boundaries.
* **Category Type Safety:** Using class-level validation to prevent logical errors in multi-category domain models.
* **Precision and Epsilon:** Managing floating-point tolerances across different conversion scales (metric vs. imperial).
* **Architectural Scalability:** Confirming that existing patterns are reusable and extensible for future needs.
---
### Example Output
* **Equality**: `Quantity(1.0, KILOGRAM).equals(1000.0, GRAM)` -> **true**
* **Conversion**: `Quantity(1.0, KILOGRAM).convertTo(POUND)` -> **Quantity(2.205, POUND)**
* **Addition**: `Quantity(1.0, KILOGRAM).add(1000.0, GRAM)` -> **Quantity(2.0, KILOGRAM)**
* **Incompatibility**: `Weight(1.0kg).equals(Length(1.0ft))` -> **false**
---
* **Based on the defined test cases, the application now supports multiple, isolated measurement categories with high precision.**
* **Pushed the weight measurement implementation to the repository.**
* **Code :** [UC 9](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC9-WeightMeasurement)
