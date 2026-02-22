# QuantityMeasurementApp
## UC4 - Extended Unit Support
>This branch contains the code demostrating the extended units of measurement.
>It basically shows how a we can add more units in the enum and hence with a generic class, we can use multiple units.

### Date : 17 Feb 2026
* Worked on feature 4 that is extending the unit class and adding more units and their conversion rate.
* Modified Enum in Length class to add two more units: Yards & Centimeters and their respective conversion rates.
* Performed whole operstions in following steps:
* Step 1 – Update LengthUnit Enum
  >1. Add YARDS constant to the LengthUnit enum with conversion factor 3.0 (since 1 yard = 3 feet).
  >2. Add CENTIMETERS constant to the LengthUnit enum with conversion factor (since 1 cm = 0.393701 in).
  >3. Verify that the conversion factor calculation is correct: 1 yard ÷ 1 foot = 3.0 or 1 cm  ÷ 1 in = 0.39307
  >4. Verify that the conversion factor calculation is correct: 1 yard ÷ 1 foot = 3.0.
* Step 2 – Verify QuantityLength Class
  >1. Confirm that the existing equals() method and convertToFeet() logic work correctly for the new unit.
  >2. No changes to the QuantityLength class should be needed due to the generic design from UC3.
* Step 3 – Test Coverage
  >1. Ensure comprehensive test cases cover yard-to-yard, yard-to-feet, and yard-to-inches comparisons.
  >2. Ensure comprehensive test cases cover yard-to-cm, cm-to-cm, and cm-to-inches, etc comparisons.
  >3. Verify cross-unit equality with various combinations.  
* Based on test cases program was modified.
* Pushed the code to repository.
* Code : [UC 4](https://github.com/Harsh-Raj-Singh25/QuantityMeasurementApp/edit/feature/UC4-YardEquality)
* 



