package com.quantityMeasurement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.quantityMeasurement.Length.LengthUnit;

public class TargetUnitAdditionTest {
	private static final double EPSILON = 1e-3; // Tolerance for floating-point comparisons

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.FEET); // 1ft + 1ft = 2ft
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.INCHES); // 12in + 12in = 24in
        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.YARDS); // 2ft = 0.667 yards
        assertEquals(0.667, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        Length l1 = new Length(1.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.CENTIMETERS); // 2 inches = 5.08 cm
        assertEquals(5.08, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.YARDS); // 2yd + 1yd = 3yd
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length l1 = new Length(2.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.FEET); // 6ft + 3ft = 9ft
        assertEquals(9.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length res1 = l1.add(l2, LengthUnit.YARDS);
        Length res2 = l2.add(l1, LengthUnit.YARDS); // A+B = B+A
        assertEquals(res1.getValue(), res2.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(0.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.YARDS); // 5ft = 1.667 yards
        assertEquals(1.667, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length l1 = new Length(5.0, LengthUnit.FEET);
        Length l2 = new Length(-2.0, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.INCHES); // 3ft = 36in
        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        l1.add(new Length(12.0, LengthUnit.INCHES), null); // Null validation
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length l1 = new Length(1000.0, LengthUnit.FEET);
        Length l2 = new Length(500.0, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.INCHES); // 1500ft = 18000in
        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2, LengthUnit.YARDS); // 24in = 0.667yd
        assertEquals(0.667, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(0.1, LengthUnit.FEET);
        Length result = l1.add(l2, LengthUnit.INCHES); // Verified using epsilon
        assertEquals(13.2, result.getValue(), EPSILON);
    }
}
