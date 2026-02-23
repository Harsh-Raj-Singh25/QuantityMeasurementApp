# QuantityMeasurementApp
## UC10 - Generic Quantity Class with Multi-Category Support
### Overview
> UC10 refactors the application into a single, type-safe `Quantity<U>` class that works with any measurement category through a common `IMeasurable` interface. This architectural shift eliminates code duplication across category-specific classes (like `QuantityLength` and `QuantityWeight`) and restores the **Single Responsibility Principle** by consolidating unit-related patterns.
---
### Date : 21 Feb 2026
* Defined the `IMeasurable` interface to standardize unit behavior.
* Refactored Length and Weight categories into a unified `Quantity<U>` generic structure.
---
### Performed operations in the following steps:
* **Step 1 – Defining IMeasurable Interface**
  > 1. Created a contract for all units requiring `getConversionFactor()`, `convertToBaseUnit()`, and `convertFromBaseUnit()`.
  > 2. Updated `LengthUnit` and `WeightUnit` enums to implement this interface consistently.
* **Step 2 – Implementing the Generic Quantity Class**
  > 1. Created a single `Quantity<U>` class to replace `QuantityLength` and `QuantityWeight`.
  > 2. Implemented generic `equals()`, `add()`, and `convertTo()` methods that work with any `IMeasurable` unit.
  > 3. Added runtime category validation in `equals()` to prevent cross-category equality even when base values match.
* **Step 3 – Simplifying Demonstration & Test Infrastructure**
  > 1. Refactored `QuantityMeasurementApp` to use generic methods, significantly reducing method count and complexity.
  > 2. Verified that the generic implementation maintains 100% backward compatibility with UC1–UC9 test cases.
  > 3. Established a template for "zero-code" expansion; adding new units now requires only a new enum, with no changes to the Quantity class.
---
### Features
* **Generic Bounded Types:** Uses `Quantity<U extends IMeasurable>` to enforce type safety at compile-time while allowing universal logic reuse.
* **Interface-Based Design:** The `IMeasurable` interface standardizes conversion behavior, enabling polymorphic handling of all unit types.
* **Category Type Safety:** Implements runtime checks using `unit.getClass()` to prevent logically invalid comparisons (e.g., 1 Foot ≠ 1 Kilogram).
* **Consolidated Orchestration:** Simplifies the `QuantityMeasurementApp` class by replacing category-specific methods with unified generic demonstration methods.
* **Open-Closed Principle (OCP):** The system is now open for extension (adding new categories like Volume or Temperature) but closed for modification of core logic.
---
### Concepts Learned in UC10
* **Generic Programming:** Leveraging Java Generics to write reusable, type-safe logic that scales linearly rather than exponentially.
* **Type Erasure & Wildcards:** Handling the limitations of runtime type erasure by using wildcards (`Quantity<?>`) and object class checks.
* **DRY (Don't Repeat Yourself):** Eliminating boilerplate code by centralizing algorithm implementations across diverse domain categories.
* **Polymorphism & Delegation:** Using interface-based delegation to allow the Quantity class to perform math without knowing unit-specific details.
---
### Example Output
* **Generic Equality**: `new Quantity<>(1.0, FEET).equals(new Quantity<>(12.0, INCHES))` -> **true**
* **Unified Conversion**: `new Quantity<>(1.0, KILOGRAM).convertTo(GRAM)` -> **Quantity(1000.0, GRAM)**
* **Safety Check**: `1.0 Foot` equals `1.0 Kilogram` -> **false (Category Mismatch)**
---

* **Based on the defined test cases, the architecture now supports infinite measurement categories with a single, maintainable code path.**
* **Pushed the generic refactor to the repository.**
* **Code :** [UC 10 Generic Refactor](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/edit/feature/UC10-Generic-Quantity-Class)
