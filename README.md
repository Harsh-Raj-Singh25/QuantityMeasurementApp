# QuantityMeasurementApp
## UC14 - Temperature Measurement & Selective Arithmetic Support

### Overview
> UC14 introduces **Temperature** (Celsius and Fahrenheit) as a fourth measurement category. This use case represents a significant architectural evolution, moving from simple linear conversion factors to formula-based transformations. Additionally, it implements **Selective Arithmetic Support**, allowing the system to logically prevent nonsensical operations like adding or dividing absolute temperatures.

---
### Date : 23 Feb 2026
* Refactored `IMeasurable` interface to support capability-based checks.
* Implemented the `TemperatureUnit` enum with non-linear conversion logic.
---

### Performed operations in the following steps:
* **Step 1 – Refactoring IMeasurable Interface**
  > 1. Added a `SupportsArithmetic` functional interface and a default `supportsArithmetic()` method to the base interface.
  > 2. Implemented `validateOperationSupport()` to provide a centralized hook for units to reject specific arithmetic calls.
* **Step 2 – Implementing Temperature Logic**
  > 1. Created `TemperatureUnit` using `Function<Double, Double>` to encapsulate Celsius-to-Fahrenheit and Fahrenheit-to-Celsius formulas.
  > 2. Overrode capability methods to return `false` for arithmetic support, ensuring fail-fast behavior.
* **Step 3 – Updating the Quantity Engine**
  > 1. Enhanced `validateArithmeticOperands()` to query the unit’s support status before performing any math.
  > 2. Verified that `equals()` and `convertTo()` remain fully functional for Temperature, allowing for comparison and state change without arithmetic.
---

### Features
* **Non-Linear Conversion Engine:** Replaces simple multiplication factors with Java `Function` lambdas to handle complex formulas like $F = (C \times 9/5) + 32$.
* **Interface Segregation (ISP):** Refactored `IMeasurable` with default methods to allow units to "opt-out" of arithmetic operations they do not support.
* **Operational Guardrails:** Triggers `UnsupportedOperationException` when arithmetic is attempted on Temperature objects, while maintaining full support for Length, Weight, and Volume.
* **Category Isolation:** Ensures Temperature remains a distinct domain, preventing any comparison with other physical quantities.
* **Backward Compatibility:** Leverages default interface methods to ensure existing measurement units require no code changes.
---

### Concepts Learned in UC14
* **Interface Segregation Principle:** Designing interfaces that don't force implementations to support irrelevant behavior.
* **Capability-Based Design:** Using the "Ask, Don't Assume" pattern to check object capabilities at runtime.
* **Non-Linear Domain Modeling:** Transitioning from factor-based systems to formula-based systems using functional programming.
* **Absolute vs. Relative Scales:** Understanding the physical constraints that make certain arithmetic operations (like temperature division) logically invalid.
---

### Example Output
* **Equality**: `0.0 CELSIUS` == `32.0 FAHRENHEIT` -> **true**
* **Intersection Point**: `-40.0 CELSIUS` == `-40.0 FAHRENHEIT` -> **true**
* **Conversion**: `100.0 CELSIUS` to `FAHRENHEIT` -> **212.0 FAHRENHEIT**
* **Unsupported Operation**: `100.0 CELSIUS.add(50.0 CELSIUS)` -> **Throws UnsupportedOperationException**
* **Category Safety**: `32.0 FAHRENHEIT` equals `32.0 FEET` -> **false**
---

* **The system now supports complex, non-linear categories while enforcing strict logical boundaries on arithmetic operations.**
* **Pushed the Temperature Support implementation to the repository.**
* **Code :** [UC 14 Temperature Support](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurement)