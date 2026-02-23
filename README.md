# QuantityMeasurementApp
## UC13 - Centralized Arithmetic Logic (DRY Refactor)
### Overview
> UC13 refactors the arithmetic engine implemented in UC12 to eliminate code duplication and enforce the **DRY (Don't Repeat Yourself)** principle. By introducing a centralized validation helper and an enum-based operation dispatcher using **Java Lambdas**, the application now processes all arithmetic operations through a single, maintainable code path.
---
### Date : 23 Feb 2026
* Refactored the `Quantity<U>` class to use centralized private helpers for arithmetic.
* Implemented Lambda-based operation dispatching via `ArithmeticOperation` enum.
---
### Performed operations in the following steps:
* **Step 1 – Designing the Arithmetic Dispatcher**
  > 1. Created a private `ArithmeticOperation` enum using the `@FunctionalInterface` `DoubleBinaryOperator`.
  > 2. Defined mathematical strategies as lambdas: `(a, b) -> a + b` for addition and `(a, b) -> a - b` for subtraction.
* **Step 2 – Creating Centralized Helpers**
  > 1. Extracted all guard clauses into `validateArithmeticOperands()` to ensure unified error handling.
  > 2. Implemented `performBaseArithmetic()` to handle the conversion of both operands to the base unit before executing the lambda.
* **Step 3 – Refactoring Public Methods**
  > 1. Updated `add()`, `subtract()`, and `divide()` to delegate all work to the new internal helpers.
  > 2. Verified through unit testing that the refactor preserved method chaining and immutability.
---
### Features
* **Centralized Validation:** Consolidates null checks, category verification, and finiteness validation into a single private helper method.
* **Functional Dispatcher:** Utilizes an internal `ArithmeticOperation` enum with `DoubleBinaryOperator` lambdas to encapsulate mathematical logic (ADD, SUBTRACT, DIVIDE).
* **Consistency Enforcement:** Ensures that error messages, rounding behavior, and base-unit normalization are identical across all operations.
* **Scalable Architecture:** Establishing a "Single Source of Truth" allows new operations (like Multiply or Modulo) to be added by updating a single enum constant.
* **Backward Compatibility:** Internal refactoring maintains the exact same public API and functional behavior established in UC1–UC12.
---
### Concepts Learned in UC13
* **DRY (Don't Repeat Yourself):** Reducing the "bug surface" by ensuring logic is implemented exactly once.
* **Lambda Expressions & Functional Interfaces:** Treating logic as data to simplify complex conditional structures.
* **Encapsulation:** Using private visibility for core engine details while maintaining a stable public interface.
* **Code Maintainability:** Reducing method length and complexity to improve readability and long-term project health.
---
### Internal Flow Example
1.  **Public Call**: `q1.subtract(q2, FEET)`
2.  **Validation**: `validateArithmeticOperands` checks nulls and category compatibility.
3.  **Computation**: `performBaseArithmetic` converts both to base units and triggers the `SUBTRACT` lambda.
4.  **Result**: The result is denormalized to the target unit and returned as a new `Quantity`.
---
* **The refactoring successfully consolidated the measurement engine, making the codebase cleaner and more robust.**
* **Pushed the DRY Refactor implementation to the repository.**
* **Code :** [UC 13 DRY Refactor](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/edit/feature/UC13-CentralizedArithmeticLogic)
