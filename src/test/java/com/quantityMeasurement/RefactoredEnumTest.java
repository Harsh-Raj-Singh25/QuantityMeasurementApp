package com.quantityMeasurement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RefactoredEnumTest {
	private static final double EPSILON = 1e-3;

	// Standalone Enum Constant Verification ---

	@Test
	public void testLengthUnitEnum_FeetConstant() {
		// Verifies LengthUnit.FEET is accessible and has correct conversion factor 1.0.
		assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_InchesConstant() {
		// Verifies LengthUnit.INCHES has the correct conversion factor (1/12).
		assertEquals(0.0833, LengthUnit.INCHES.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_YardsConstant() {
		// Verifies LengthUnit.YARDS has correct conversion factor 3.0.
		assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_CentimetersConstant() {
		// Verifies LengthUnit.CENTIMETERS has correct conversion factor (1/30.48).
		assertEquals(0.0328, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
	}

	// --- Convert To Base Unit (Feet) Verification ---

	@Test
	public void testConvertToBaseUnit_FeetToFeet() {
		// Verifies 5.0 FEET returns 5.0 FEET.
		assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_InchesToFeet() {
		// Verifies 12.0 INCHES returns 1.0 FEET.
		assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_YardsToFeet() {
		// Verifies 1.0 YARD returns 3.0 FEET.
		assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_CentimetersToFeet() {
		// Verifies 30.48 CENTIMETERS returns ~1.0 FEET.
		assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON);
	}

	// --- Convert From Base Unit Verification ---

	@Test
	public void testConvertFromBaseUnit_FeetToFeet() {
		// Verifies 2.0 FEET (base) to FEET unit returns 2.0.
		assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToInches() {
		// Verifies 1.0 FEET (base) to INCHES returns 12.0.
		assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToYards() {
		// Verifies 3.0 FEET (base) to YARDS returns 1.0.
		assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToCentimeters() {
		// Verifies 1.0 FEET (base) to CENTIMETERS returns ~30.48.
		assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPSILON);
	}

	// --- Refactored QuantityLength (Length) Functionality ---

	@Test
	public void testQuantityLengthRefactored_Equality() {
		// Verifies cross-unit equality through refactored delegation.
		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = new Length(12.0, LengthUnit.INCHES);
		assertTrue(feet.equals(inches));
	}

	@Test
	public void testQuantityLengthRefactored_ConvertTo() {
		// Verifies convertTo() delegates correctly to unit methods.
		Length feet = new Length(1.0, LengthUnit.FEET);
		Length result = feet.convertTo(LengthUnit.INCHES);
		assertEquals(12.0, result.getValue(), EPSILON);
	}

	@Test
	public void testQuantityLengthRefactored_Add() {
		// Verifies add() correctly uses unit conversion methods.
		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = new Length(12.0, LengthUnit.INCHES);
		Length result = feet.add(inches, LengthUnit.FEET);
		assertEquals(2.0, result.getValue(), EPSILON);
	}

	@Test
	public void testQuantityLengthRefactored_AddWithTargetUnit() {
		// Verifies add() with explicit target (YARDS).
		Length feet = new Length(1.0, LengthUnit.FEET);
		Length inches = new Length(12.0, LengthUnit.INCHES);
		Length result = feet.add(inches, LengthUnit.YARDS);
		assertEquals(0.667, result.getValue(), EPSILON);
	}

	// --- Validation Verification ---

	@Test(expected = IllegalArgumentException.class)
	public void testQuantityLengthRefactored_NullUnit() {
		// Verifies null unit throws appropriate exception.
		new Length(1.0, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testQuantityLengthRefactored_InvalidValue() {
		// Verifies NaN is rejected.
		new Length(Double.NaN, LengthUnit.FEET);
	}

	// --- Precision and Scalability ---

	@Test
	public void testRoundTripConversion_RefactoredDesign() {
		// Verifies convert(convert(value, A, B), B, A) ≈ value.
		Length original = new Length(1.0, LengthUnit.YARDS);
		Length converted = original.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.YARDS);
		assertEquals(original.getValue(), converted.getValue(), EPSILON);
	}

	@Test
	public void testUnitImmutability() {
		// Enums are inherently immutable and thread-safe.
		LengthUnit unit = LengthUnit.FEET;
		assertEquals("FEET", unit.name());
	}
}
