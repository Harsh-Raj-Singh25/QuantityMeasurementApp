# QuantityMeasurementApp
## UC3 - Generic Quantity Class (DRY Implementation)

### Overview
> UC3 refactors the application to adhere to the **DRY (Don't Repeat Yourself)** principle. By replacing redundant, unit-specific classes with a single, generic `QuantityLength` class and a `LengthUnit` Enum, the system now supports cross-unit comparisons (e.g., 1 Foot == 12 Inches) through a centralized conversion engine.
---
### Date : 19 Feb 2026
* Refactored `Feet` and `Inches` classes into a unified `QuantityLength` class.
* Implemented the `LengthUnit` Enum to manage conversion factors.
---
### Performed operations in the following steps:
* **Step 1 – Designing the LengthUnit Enum**
  > 1. Defined **INCHES** as the base unit with a factor of 1.0.
  > 2. Defined **FEET** with a conversion factor of 12.0 relative to the base unit.
* **Step 2 – Implementing the QuantityLength Engine**
  > 1. Created a unified constructor that accepts both a numeric value and a `LengthUnit` constant.
  > 2. Refactored the `equals()` method to perform "normalization": multiplying the value by the unit's conversion factor before comparison.
* **Step 3 – Validation and Backward Compatibility**
  > 1. Verified that existing tests for same-unit equality still pass (Foot-to-Foot, Inch-to-Inch).
  > 2. Validated that the system correctly identifies 1 Foot and 12 Inches as equivalent values.
---
### Features
* **Unit Abstraction:** Consolidates multiple measurement categories into a single, scalable class structure.
* **Cross-Unit Equality:** Accurately compares different units (Feet vs. Inches) by normalizing values to a common base unit.
* **Type-Safe Constants:** Utilizes Java Enums to define units and conversion factors, eliminating "magic strings" and spelling-related bugs.
* **Scalable Design:** New length units can be added by simply updating the Enum without modifying the core logic.
* **Symmetric Comparison:** Ensures that if $A = B$, then $B = A$ across all unit types.
---
### Concepts Learned in UC3
* **DRY Principle:** Reducing maintenance burden by eliminating code duplication.
* **Polymorphism:** Using a single class to handle diverse unit types at runtime.
* **Base-Unit Normalization:** The mathematical process of bringing different scales to a common denominator for accurate comparison.
* **Refactoring Best Practices:** Consolidating redundant code while maintaining identical public behavior.
---
### Example Output
* **Input**: `Quantity(1.0, FEET)` and `Quantity(12.0, INCHES)` -> **Equal (true)**
* **Input**: `Quantity(1.0, INCHES)` and `Quantity(1.0, INCHES)` -> **Equal (true)**
* **Symmetry Check**: `12.0 INCHES` equals `1.0 FOOT` -> **true**
---
* **The system now provides a robust, generic framework for length measurements, successfully resolving the code duplication issues of UC2.**
* **Pushed the Generic Quantity refactor to the repository.**
* **Code :** [UC 3 DRY Refactor Implementation](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC3-DRYRefactor)
