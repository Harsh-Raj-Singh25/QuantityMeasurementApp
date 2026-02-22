# QuantityMeasurementApp
## UC6 - Addition of Two Length Units
> UC6 extends the application by introducing addition operations between length measurements. 
> It enables the API to add two lengths of potentially different units and return the result in the unit of the first operand.


---
### Date : 20 Feb 2026
* Worked on feature 6 which focuses on arithmetic operations for length units.
* Implemented the addition logic within the `Length` class ensuring the result unit consistency.
---
### Performed operations in the following steps:
* **Step 1 – Arithmetic Implementation in Value Object**
  > 1. Added the `add()` instance method to the `Length` class to handle measurement summation.
  > 2. Implemented normalization logic: both operands are converted to the base unit (Inches) before addition.
  > 3. Ensured the final sum is converted from the base unit back to the unit of the first operand to maintain consistency.

* **Step 2 – Validation and Immutability**
  > 1. Confirmed that original `Length` objects remain unchanged after addition, adhering to the immutability principle.
  > 2. Added validation to ensure both operands are non-null and contain finite numeric values (using `Double.isFinite`).
  > 3. Optimized the code to perform the final unit conversion only once, resolving potential "double-conversion" inaccuracies.

* **Step 3 – Test Coverage and Mathematical Properties**
  > 1. Ensured comprehensive test cases cover same-unit addition (Feet + Feet) and cross-unit addition (Feet + Inches).
  > 2. Verified the Commutative Property: confirmed that `add(A, B)` yields the same result as `add(B, A)`.
  > 3. Implemented tests for Identity Element (adding zero) and negative value handling.
  > 4. Continued use of **Epsilon ($\epsilon$)** of $1e-6$ in JUnit assertions to account for floating-point precision differences.
---
### Features
* **Generic Length Design:** Scalable architecture that supports adding new units (Feet, Inches, Yards, Centimeters) with zero modification to core logic.
* **Immutability:** Every operation returns a new `Length` instance, ensuring the original measurement data remains thread-safe and unchanged.
* **High-Precision Conversions:** Mathematical accuracy is maintained for complex units like Centimeters using a specific factor ($1 \text{ cm} = 0.393701 \text{ in}$).
* **Base-Unit Normalization:** Internal arithmetic and comparisons are performed by first converting heterogeneous units to a common base unit (Inches).
* **Flexible API (Method Overloading):** Supports multiple ways to interact with the app, including raw value addition and object-based arithmetic.
* **Epsilon-Based Assertions:** Uses a small tolerance ($\epsilon = 1e-6$) in testing to handle binary floating-point rounding issues without losing data precision.
* **Value Object Semantics:** Features overridden `equals()` and `toString()` methods for better readability and safe object comparison.
---

* **Based on test cases, the program was optimized for mathematical accuracy.**
* **Pushed the code to the repository.**
* **Code :** [UC 6](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC6-UnitAddition)
