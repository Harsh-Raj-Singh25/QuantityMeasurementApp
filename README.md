# QuantityMeasurementApp
## UC5 - Unit-to-Unit Conversion
Overview:
>UC5 extends the application’s capability by introducing explicit conversion operations between length units. While previous iterations focused on equality (e.g., is 1 foot equal to 12 inches?), UC5 allows the API to return a new numeric value or a Length instance converted from a source unit to a target unit.
> This branch contains the code demonstrating explicit conversion between various Length Units.
> It extends the previous comparison logic by allowing a source unit to be transformed into a specific target unit.
---
### Date : 20 Feb 2026
* Worked on feature 5 which focuses on the active conversion of length units.
* Implemented the conversion logic within the `Length` class using a base-unit normalization strategy.
---
* Performed operations in the following steps:
* Step 1 – Refined Conversion Logic
  > 1. Ensured all units in `LengthUnit` enum (FEET, INCHES, YARDS, CENTIMETERS) are relative to a single base unit (Inches).
  > 2. Implemented the universal conversion formula: $\text{Value} \times (\frac{\text{Source Factor}}{\text{Target Factor}})$.
  > 3. Removed premature rounding in internal methods to maintain high precision for units like Centimeters ($1\text{ cm} = 0.393701\text{ in}$).
* Step 2 – Enhanced API Design
  > 1. Added an instance method `convertTo(targetUnit)` that returns a new immutable `Length` object.
  > 2. Implemented method overloading in `QuantityMeasurementApp` to handle both raw numeric values and `Length` objects.
  > 3. Overrode `toString()` to provide human-readable output formatted to relevant decimal places.
* Step 3 – Expanded Test Coverage
  > 1. Added comprehensive test cases for unidirectional conversions (e.g., Inches to Feet, Yards to Inches).
  > 2. Implemented "Round-Trip" test cases to verify that converting $A \rightarrow B \rightarrow A$ preserves the original value.
  > 3. Utilized an **Epsilon ($\epsilon$)** of $1e-6$ in JUnit assertions to account for floating-point precision differences.
  > 4. Verified exception handling for invalid inputs like `null` units or non-finite numbers (NaN/Infinity).
---
* Key Features : 
>1. Explicit Conversion API: Provides a static method convert(value, source, target) for quick calculations.Value
>2. Object Immutability: The convertTo instance method returns a new Length object rather than modifying the current one, ensuring thread safety and data integrity.
>3. High Precision Math: Supports conversion for Centimeters using a specific factor ($1 \text{ cm} = 0.393701 \text{ inches}$) with floating-point precision handling.
>4. Method Overloading: The API supports both raw numeric conversion and object-based conversion.
---
* Based on test cases, the program was optimized for mathematical accuracy.
* Pushed the code to the repository.
* Code : [UC 5](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/tree/feature/UC5-UnitConversion)
---



