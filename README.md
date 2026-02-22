# QuantityMeasurementApp
## UC7 - Addition with Target Unit Specification
Overview:
> UC7 extends the application’s arithmetic capabilities by providing flexibility in specifying the unit for the addition result. Instead of defaulting to the first operand's unit, the caller can explicitly request any supported unit.
---
### Date : 20 Feb 2026
* Worked on feature 7 which introduces explicit target unit control for addition operations.
* Implemented a private utility method to handle core arithmetic while managing method overloading.
---
### Performed operations in the following steps:

* **Step 1 – Strict Validation & Gatekeeper Logic**
  > 1. Implemented checks to ensure operands and target units are non-null.
  > 2. Utilized `Double.isFinite()` to reject NaN and Infinite values.

* **Step 2 – Method Overloading & DRY Implementation**
  > 1. Used Method Overloading to allow the `add()` method to coexist with and without explicit target unit parameters.
  > 2. Created a **Private Utility Method** (`performAddition`) to centralize logic.

* **Step 3 – Precision and Scale Testing**
  > 1. Verified correctness when converting across different scales (e.g., result in yards instead of inches).
  > 2. Maintained **Epsilon ($\epsilon$)** based comparisons in JUnit to handle rounding issues.
---
### Features
* **Explicit Target Specification:** Caller has total control over the result representation.
* **Private Utility Addition:** Centralizes math logic to ensure consistent precision (DRY principle).
* **Method Overloading:** Supports both implicit (UC6) and explicit (UC7) target unit specification.
* **Immutability:** Every operation returns a new `Length` object, preserving original state.
* **Strict Validation:** Uses `Double.isFinite()` and null-checks for data integrity.
---
* Based on test cases, the program was optimized for accuracy.
* Pushed the code to the repository.
* **Code :** [UC 7](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition)
