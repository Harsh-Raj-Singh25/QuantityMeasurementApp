# QuantityMeasurementApp
## UC12 - Subtraction and Division Operations
### Overview
> UC12 enhances the generic `Quantity<U>` class by implementing **Subtraction** and **Division** operations. While subtraction allows for finding the difference between two quantities (returning a new `Quantity`), division computes the ratio between two quantities of the same category, resulting in a **dimensionless scalar** (primitive `double`).
---

### Date : 23 Feb 2026
* Implemented generic `subtract()` and `divide()` methods in the `Quantity<U>` class.
* Added specialized demonstration logic to `QuantityMeasurementApp` to showcase ratio calculations.
---
### Performed operations in the following steps:
* **Step 1 – Implementing Subtraction**
  > 1. Created overloaded `subtract()` methods with support for implicit and explicit target units.
  > 2. Integrated normalization to the base unit before performing the arithmetic to ensure cross-unit accuracy (e.g., Feet minus Inches).
* **Step 2 – Implementing Division (Ratio Calculation)**
  > 1. Developed the `divide()` method to return a `double` representing the ratio between two measurements.
  > 2. Added validation to throw an `ArithmeticException` if the divisor is a zero-quantity.
* **Step 3 – Validation and Safety Integration**
  > 1. Added a runtime `unit.getClass()` check to ensure arithmetic is only performed within the same measurement category.
  > 2. Verified that all operations maintain **Immutability**, returning new results without altering the state of the original operands.
---
### Features
* **Full Arithmetic Suite:** Complements existing addition logic with robust subtraction and division capabilities.
* **Dimensionless Ratios:** Division logic correctly identifies that dividing like-units (e.g., Length/Length) results in a unitless scalar value.
* **Non-Commutative Logic:** Properly handles operations where order matters, ensuring $A - B$ and $B - A$ produce mathematically distinct results.
* **Strict Error Handling:** Implements fail-fast validation for **Division by Zero** and **Null Operands**.
* **Method Chaining Support:** Arithmetic methods return new `Quantity<U>` instances, allowing for fluid operations like `q1.add(q2).subtract(q3)`.
* **Cross-Category Protection:** Prevents logically invalid operations (e.g., Subtracting Weight from Volume) through runtime class verification.
---
### Concepts Learned in UC12
* **Dimensionless Quantities:** Understanding how units cancel out in division to produce scalar ratios.
* **Mathematical Property Verification:** Testing for non-commutativity and non-associativity in arithmetic logic.
* **Runtime vs. Compile-time Safety:** Using `.getClass()` checks to bypass generic type erasure limitations during arithmetic validation.
* **Arithmetic Inverses:** Verifying the mathematical relationship between operations (e.g., $A + B - B \approx A$).
---
### Example Output
* **Subtraction**: `10.0 FEET - 6.0 INCHES` -> **9.50 FEET**
* **Division**: `10.0 KILOGRAM / 5.0 KILOGRAM` -> **2.0** (Scalar)
* **Cross-Unit Ratio**: `24.0 INCHES / 2.0 FEET` -> **1.0**
* **Error Handling**: `10.0 FEET / 0.0 FEET` -> **Throws ArithmeticException**
---
* **The system now provides a comprehensive mathematical API for manipulating physical measurements across all supported categories.**
* **Pushed the Subtraction and Division implementation to the repository.**
* **Code :** [UC 12 Arithmetic Expansion](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/edit/feature/UC12-SubtractionDivisionOperations)
