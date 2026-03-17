package com.quantityMeasurement;

import org.junit.Test;

import com.quantityMeasurement.model.Quantity;

import static org.junit.Assert.*;

public class CentralizedArithmeticLogicTest {
	private static final double EPSILON = 1e-3;

	// Enum Logic Verification ---

	@Test
	public void testArithmeticOperation_Add_EnumComputation() {
		// Direct test of the ADD logic (10 + 5 = 15)
		Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
		// Note: Testing internal enum logic via public add to ensure delegation
		Quantity<LengthUnit> result = q.add(new Quantity<>(5.0, LengthUnit.FEET));
		assertEquals(15.0, result.getValue(), EPSILON);
	}

	@Test
	public void testArithmeticOperation_Subtract_EnumComputation() {
		Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> result = q.subtract(new Quantity<>(5.0, LengthUnit.FEET));
		assertEquals(5.0, result.getValue(), EPSILON);
	}

	@Test(expected = ArithmeticException.class)
	public void testArithmeticOperation_DivideByZero_EnumThrows() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);
		q1.divide(q2);
	}

	// Validation Consistency (DRY Verification) ---

	@Test
	public void testValidation_NullOperand_ConsistentAcrossOperations() {
		Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

		try {
			q.add(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Operand cannot be null", e.getMessage());
		}

		try {
			q.subtract(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Operand cannot be null", e.getMessage());
		}

		try {
			q.divide(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Operand cannot be null", e.getMessage());
		}
	}

	@Test
	public void testValidation_CrossCategory_ConsistentAcrossOperations() {
		Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
		String expectedMsg = "Cross-category arithmetic is not allowed";

		// Cast to raw Quantity to bypass compile-time safety and test runtime logic
		@SuppressWarnings("rawtypes")
		Quantity rawWeight = (Quantity) weight;

		try {
			length.add(rawWeight);
			fail("Should have thrown IllegalArgumentException for ADD");
		} catch (IllegalArgumentException e) {
			assertEquals(expectedMsg, e.getMessage());
		}

		try {
			length.subtract(rawWeight);
			fail("Should have thrown IllegalArgumentException for SUBTRACT");
		} catch (IllegalArgumentException e) {
			assertEquals(expectedMsg, e.getMessage());
		}

		try {
			length.divide(rawWeight);
			fail("Should have thrown IllegalArgumentException for DIVIDE");
		} catch (IllegalArgumentException e) {
			assertEquals(expectedMsg, e.getMessage());
		}
	}

	// Rounding & Precision Consistency ---

	@Test
	public void testRounding_AddSubtract_TwoDecimalPlaces() {
		// 1.111 + 2.222 = 3.333 -> Rounded to 3.33
		Quantity<LengthUnit> q1 = new Quantity<>(1.111, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(2.222, LengthUnit.FEET);
		assertEquals(3.33, q1.add(q2).getValue(), 0.0);
		assertEquals(-1.11, q1.subtract(q2).getValue(), 0.0);
	}

	@Test
	public void testRounding_Divide_NoRounding() {
		// Division returns raw double, usually not subject to the 2-decimal rounding
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
		// 10 / 3 = 3.3333... (should not be exactly 3.33)
		assertNotEquals(3.33, q1.divide(q2), 0.0);
		assertEquals(3.3333, q1.divide(q2), 0.0001);
	}

	// Backward Compatibility & Chaining ---

	@Test
	public void testArithmetic_Chain_Operations() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
		Quantity<LengthUnit> q3 = new Quantity<>(2.0, LengthUnit.FEET);

		// (10 + 5) - 2 = 13
		Quantity<LengthUnit> result = q1.add(q2).subtract(q3);
		assertEquals(13.0, result.getValue(), EPSILON);
	}

	@Test
	public void testImmutability_Maintained() {
		Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
		q1.add(new Quantity<>(5.0, LengthUnit.FEET));
		// Verify original object didn't change despite internal helper usage
		assertEquals(10.0, q1.getValue(), EPSILON);
	}
}