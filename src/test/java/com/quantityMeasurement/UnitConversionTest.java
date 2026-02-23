package com.quantityMeasurement;

import static org.junit.Assert.*;
import org.junit.Test; 

public class UnitConversionTest {
    private static final double EPSILON = 1e-6;

    @Test
    public void testConversion_FeetToInches() {
    	double result=QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    public void testConversion_InchesToFeet() {
    	double result =QuantityMeasurementApp.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {
    	double result=QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES);
        assertEquals(36.0, result, EPSILON);
    }

    @Test
    public void testConversion_InchesToYards() {
    	double result = QuantityMeasurementApp.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        // 2.54 cm * 0.393701 = 0.999999... which is ~1.0
        assertEquals(1.0, QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES), 0.01);
    }

    @Test
    public void testConversion_FeetToYard() {
        assertEquals(2.0, QuantityMeasurementApp.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS), EPSILON);
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {
        double originalValue = 10.0;
        double intermediate = QuantityMeasurementApp.convert(originalValue, LengthUnit.FEET, LengthUnit.YARDS);
        double result = QuantityMeasurementApp.convert(intermediate, LengthUnit.YARDS, LengthUnit.FEET);
        assertEquals(originalValue, result, EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        assertEquals(0.0, QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        assertEquals(-12.0, QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_InvalidUnit_Throws() {
        QuantityMeasurementApp.convert(1.0, null, LengthUnit.INCHES);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NaNOrInfinite_Throws() {
        QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES);
    }
}