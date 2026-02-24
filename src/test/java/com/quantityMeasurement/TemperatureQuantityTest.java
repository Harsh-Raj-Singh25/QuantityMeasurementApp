package com.quantityMeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureQuantityTest {
    private static final double EPSILON = 1e-2;

    // Equality & Basic Conversion ---

    @Test
    public void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    public void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        assertTrue(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
                .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        // Freezing point comparison
        Quantity<TemperatureUnit> celsius = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(celsius.equals(fahrenheit));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        // Boiling point comparison
        Quantity<TemperatureUnit> celsius = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(celsius.equals(fahrenheit));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        // The point where both scales intersect
        Quantity<TemperatureUnit> celsius = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(celsius.equals(fahrenheit));
    }

    @Test
    public void testTemperatureConversion_RoundTrip_PreservesValue() {
        Quantity<TemperatureUnit> original = new Quantity<>(37.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> roundTrip = original.convertTo(TemperatureUnit.FAHRENHEIT)
                                                     .convertTo(TemperatureUnit.CELSIUS);
        assertEquals(original.getValue(), roundTrip.getValue(), EPSILON);
    }

    //  Selective Arithmetic Support (The ISP Check) ---

    @Test(expected = UnsupportedOperationException.class)
    public void testTemperatureUnsupportedOperation_Add() {
        Quantity<TemperatureUnit> t1 = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        t1.add(t2); // Should throw UnsupportedOperationException
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTemperatureUnsupportedOperation_Subtract() {
        new Quantity<>(100.0, TemperatureUnit.CELSIUS).subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTemperatureUnsupportedOperation_Divide() {
        new Quantity<>(100.0, TemperatureUnit.CELSIUS).divide(new Quantity<>(2.0, TemperatureUnit.CELSIUS));
    }

    @Test
    public void testOperationSupportMethods_LengthUnitAddition() {
        // Verifies backward compatibility: Length still supports addition
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    @Test
    public void testOperationSupportMethods_TemperatureUnitAddition() {
        // Verifies temperature specifically opts out
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

    //  Type Safety & Category Isolation ---

    @Test
    public void testTemperatureVsLengthIncompatibility() {
        Quantity<TemperatureUnit> temp = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<LengthUnit> length = new Quantity<>(100.0, LengthUnit.FEET);
        assertFalse(temp.equals(length));
    }

    @Test
    public void testTemperatureVsWeightIncompatibility() {
        Quantity<TemperatureUnit> temp = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<WeightUnit> weight = new Quantity<>(50.0, WeightUnit.KILOGRAM);
        assertFalse(temp.equals(weight));
    }

    //  Validation & Edge Cases ---

    @Test(expected = IllegalArgumentException.class)
    public void testTemperatureNullUnitValidation() {
        new Quantity<TemperatureUnit>(100.0, null);
    }

    @Test
    public void testTemperatureDifferentValuesInequality() {
        assertFalse(new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    public void testIMeasurableInterface_Evolution_BackwardCompatible() {
        // Existing units should work with new default methods without code changes
        assertNotNull(WeightUnit.KILOGRAM.convertToBaseUnit(10.0));
        assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
    }
}