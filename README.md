# QuantityMeasurementApp
## UC11 - Volume Measurement Equality, Conversion, and Addition
### Overview
> UC11 extends the Quantity Measurement Application to support the **Volume** category (Litre, Millilitre, Gallon). This use case serves as a validation of the generic architecture established in UC10, proving that the `Quantity<U>` class and `IMeasurable` interface can scale to new physical dimensions without any modifications to core logic.
---
### Date : 23 Feb 2026
* Implemented the `VolumeUnit` standalone enum implementing `IMeasurable`.
* Verified 100% integration with generic demonstration and testing suites.
---
### Performed operations in the following steps:
* **Step 1 – Creating the VolumeUnit Enum**
  > 1. Defined units: **LITRE** (Base: 1.0), **MILLILITRE** (0.001), and **GALLON** (3.78541).
  > 2. Implemented the `IMeasurable` contract to provide centralized conversion factors relative to Litres.
* **Step 2 – Generic Logic Validation**
  > 1. Verified that `Quantity<VolumeUnit>` correctly identifies equivalent values across units (e.g., 1 L = 1000 mL).
  > 2. Validated the `convertTo()` and `add()` methods for cross-unit volume arithmetic.
* **Step 3 – Integration & Regression Testing**
  > 1. Confirmed that adding the Volume category does not affect existing Length and Weight functionality (Backward Compatibility).
  > 2. Verified category isolation: Attempting to compare `Quantity<VolumeUnit>` with `Quantity<LengthUnit>` returns `false`.
---
### Features
* **Zero-Modification Scaling:** Volume support was added purely by creating a new `VolumeUnit` enum; no changes were required for the `Quantity` class or demonstration methods.
* **Unified Volume API:** Supports equality, conversion, and addition for Litres (Base), Millilitres, and Gallons.
* **Multi-Category Isolation:** Maintains strict type safety, ensuring Volume measurements are incomparable to Length or Weight.
* **High Precision Conversions:** Handles imperial-to-metric transformations (Gallons to Litres) with a consistent epsilon-based precision.
* **Functional Immutability:** All volume operations return new `Quantity<VolumeUnit>` instances, preserving the state of original measurements.
---
### Concepts Learned in UC11
* **Architecture Linearity:** Confirmed that the cost of adding new categories remains constant rather than increasing as the system grows.
* **Domain Isolation:** Reinforcing the boundaries between different physical quantities through class-level runtime checks.
* **Imperial vs. Metric Precision:** Managing the mathematical nuances of non-metric units like Gallons within a normalized base-unit system.
---
### Example Output
* **Equality**: `new Quantity<>(1.0, LITRE).equals(1000.0, MILLILITRE)` -> **true**
* **Conversion**: `new Quantity<>(1.0, GALLON).convertTo(LITRE)` -> **Quantity(3.785, LITRE)**
* **Addition**: `new Quantity<>(1.0, GALLON).add(3.785, LITRE)` -> **Quantity(2.0, GALLON)**
* **Safety Check**: `1.0 Litre` equals `1.0 Foot` -> **false (Category Mismatch)**
---
* **The system successfully demonstrated full multi-category support with a single, maintainable code path.**
* **Pushed the Volume category implementation to the repository.**
* **Code :** [UC 11 Volume Support](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/edit/feature/UC11-Volume-Measurement)
