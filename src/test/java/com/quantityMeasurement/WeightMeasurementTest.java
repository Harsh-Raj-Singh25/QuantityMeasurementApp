package com.quantityMeasurement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WeightMeasurementTest {
	private static final double EPSILON = 1e-3;

    // --- Equality Comparisons ---

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        // Verifies identical kilogram measurements are equal
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
        // Verifies different kilogram values are not equal
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        // Verifies 1 kg equals 1000 g
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM).equals(new Weight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
        // Tests symmetry of metric conversion
        assertTrue(new Weight(1000.0, WeightUnit.GRAM).equals(new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible() {
        // Verifies category type safety: Weight cannot equal Length
    	Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);
        assertFalse(weight.equals(length));
    }

    @Test
    public void testEquality_NullComparison() {
        // Verifies comparison with null returns false
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        // Reflexive property test
    	Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(weight.equals(weight));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquality_NullUnit() {
        // Exception for null unit in constructor
        new Weight(1.0, null);
    }

    @Test
    public void testEquality_TransitiveProperty() {
        // If A=B and B=C, then A=C
    	Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);
        Weight c = new Weight(2.20462, WeightUnit.POUND);
        if(a.equals(b) && b.equals(c)) {
            assertTrue(a.equals(c));
        }
    }

    @Test
    public void testEquality_ZeroValue() {
        // Zero values equal across units
        assertTrue(new Weight(0.0, WeightUnit.KILOGRAM).equals(new Weight(0.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_NegativeWeight() {
        // Negative weight handling
        assertTrue(new Weight(-1.0, WeightUnit.KILOGRAM).equals(new Weight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_LargeWeightValue() {
        // Precision check for large values
        assertTrue(new Weight(1000000.0, WeightUnit.GRAM).equals(new Weight(1000.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_SmallWeightValue() {
        // Precision check for small values
        assertTrue(new Weight(0.001, WeightUnit.KILOGRAM).equals(new Weight(1.0, WeightUnit.GRAM)));
    }

    // --- Unit Conversions ---

    @Test
    public void testConversion_PoundToKilogram() {
        // 2.20462 lb to 1 kg
    	Weight result = new Weight(2.20462, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_KilogramToPound() {
        // 1 kg to 2.20462 lb
    	Weight result = new Weight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND);
        assertEquals(2.20462, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_SameUnit() {
        // Converting to same unit returns unchanged
    	Weight result = new Weight(5.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        // Zero conversion
        assertEquals(0.0, new Weight(0.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM).getValue(), EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        // Negative sign preservation
        assertEquals(-1000.0, new Weight(-1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM).getValue(), EPSILON);
    }

    @Test
    public void testConversion_RoundTrip() {
        // Round-trip preserves value
    	Weight original = new Weight(1.5, WeightUnit.KILOGRAM);
        Weight roundTrip = original.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM);
        assertEquals(original.getValue(), roundTrip.getValue(), EPSILON);
    }

    // --- Addition Operations ---

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        // Same-unit addition
    	Weight result = new Weight(1.0, WeightUnit.KILOGRAM).add(new Weight(2.0, WeightUnit.KILOGRAM));
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        // Cross-unit addition (Implicit target)
    	Weight result = new Weight(1.0, WeightUnit.KILOGRAM).add(new Weight(1000.0, WeightUnit.GRAM));
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_GramPlusKilogram() {
        // Cross-unit addition (Default to first operand)
    	Weight result = new Weight(500.0, WeightUnit.GRAM).add(new Weight(0.5, WeightUnit.KILOGRAM));
        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
        // Mixed metric and imperial addition
    	Weight result = new Weight(2.20462, WeightUnit.POUND).add(new Weight(1.0, WeightUnit.KILOGRAM));
        assertEquals(4.40924, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gram() {
        // Explicit target unit specification
    	Weight result = new Weight(1.0, WeightUnit.KILOGRAM).add(new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity() {
        // Addition order does not affect sum
    	Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
        assertEquals(w1.add(w2, WeightUnit.KILOGRAM).getValue(), w2.add(w1, WeightUnit.KILOGRAM).getValue(), EPSILON);
    }

    @Test
    public void testAddition_WithZero() {
        // Identity element addition
    	Weight result = new Weight(5.0, WeightUnit.KILOGRAM).add(new Weight(0.0, WeightUnit.GRAM));
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NegativeValues() {
        // Addition with negative measurements
    	Weight result = new Weight(5.0, WeightUnit.KILOGRAM).add(new Weight(-2000.0, WeightUnit.GRAM));
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_LargeValues() {
        // Addition with large magnitudes
    	Weight result = new Weight(1e6, WeightUnit.KILOGRAM).add(new Weight(1e6, WeightUnit.KILOGRAM));
        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_InvalidValue() {
        // Verifies NaN is rejected
        new Weight(Double.NaN, WeightUnit.KILOGRAM);
    }
}

