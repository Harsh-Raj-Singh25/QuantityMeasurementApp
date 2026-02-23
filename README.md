# QuantityMeasurementApp
## UC2 - Feet and Inches Measurement Equality
### Overview
> UC2 extends the initial system to accommodate **Inches** alongside **Feet**. This use case establishes the ability to handle multiple units of measurement independently. While both units are supported, they are treated as distinct entities, maintaining strict type safety to prevent logical errors in comparison.
---
### Date : 18 Feb 2026
* Implemented a separate `Inches` class mirroring the `Feet` class structure.
* Added static methods `compareFeet()` and `compareInches()` for cleaner API usage.
---
### Performed operations in the following steps:
* **Step 1 – Implementing the Inches Class**
  > 1. Developed a standalone `Inches` inner class with a private final field for value storage.
  > 2. Overrode the `equals()` method to handle identity, null, and type-specific value checks.
* **Step 2 – Refactoring the Application API**
  > 1. Moved instantiation logic from the `main` method into dedicated static comparison methods.
  > 2. Ensured both classes utilize `Double.compare()` to handle floating-point precision consistently.
* **Step 3 – Validation and Testing**
  > 1. Verified same-unit equality (Inch vs. Inch and Foot vs. Foot).
  > 2. Confirmed that cross-unit equality (Foot vs. Inch) correctly returns `false` due to type checking.
---
### Features
* **Multi-Unit Support:** Independent implementation of `Feet` and `Inches` classes.
* **Decoupled Comparison Logic:** Introduction of static helper methods to perform equality checks without cluttering the `main` method.
* **Value-Based Equality:** Comparison logic is based on internal numeric values rather than memory references.
* **Strict Type Safety:** Prevents a `Feet` object from being considered equal to an `Inches` object, even if their numeric values match.
---
### Concepts Learned in UC2
* **Encapsulation Expansion:** Applying object-oriented principles across multiple similar domains.
* **Type-Based Guardrails:** Using `getClass()` to isolate distinct measurement categories
