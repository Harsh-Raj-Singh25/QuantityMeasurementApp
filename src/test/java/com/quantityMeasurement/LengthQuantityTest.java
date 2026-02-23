package com.quantityMeasurement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class LengthQuantityTest {
	
	@Test
	public void testEquality_FeetToFeet_SameValue() {
		Length feet1=new Length(1.0,LengthUnit.FEET);
		Length feet2=new Length(1.0,LengthUnit.FEET);
		assertTrue(feet1.equals(feet2));
	}
	
	@Test
	public void testEquality_InchToInch_SameValue() {
		Length inch1=new Length(1.0,LengthUnit.INCHES);
		Length inch2=new Length(1.0, LengthUnit.INCHES);
		assertTrue(inch1.equals(inch2));
	}
	
	@Test
	public void testEquality_FeetToInch_Equivalency() {
		Length feet1=new Length(1.0,LengthUnit.FEET);
		Length inch1=new Length(12.0,LengthUnit.INCHES);
		assertTrue(feet1.equals(inch1));
	}
	
	@Test
	public void testEquality_InchToFeet_EquivalentValue() {
		Length feet1=new Length(1.0,LengthUnit.FEET);
		Length inch1=new Length(12.0,LengthUnit.INCHES);
		assertTrue(inch1.equals(feet1));
	}
	
	@Test
	public void testEquality_FeetToFeet_DifferentValue() {
		Length feet1=new Length(1.0,LengthUnit.FEET);
		Length feet2=new Length(15.0,LengthUnit.FEET);
		assertFalse(feet1.equals(feet2));
	}
	@Test
	public void testEquality_InchToInch_DifferentValue() {
		Length inch1=new Length(1.0,LengthUnit.INCHES);
		Length inch2=new Length(14.0, LengthUnit.INCHES);
		assertFalse(inch1.equals(inch2));
	}
	@Test
	public void testEquality_InvalidUnit() {
	    Length feet = new Length(1.0,  LengthUnit.FEET);
	    Object notALength = new Object();
	    
	    // Should return false, not crash
	    assertFalse(feet.equals(notALength));
	}
	@Test(expected = IllegalArgumentException.class)
	public void testEquality_NullUnit() {
	    // This will pass because we EXPECT the exception to be thrown here
	    new Length(12.0, null); 
	}
	//Verifies that a Quantity object equals itself (reflexive property).
	@Test
	public void testEquality_SameReference() {
		Length feet1=new Length(12.0,LengthUnit.FEET);
		assertTrue(feet1.equals(feet1));
	}
	@Test
	public void testEquality_NullComparison1() {
		Length feet1=new Length(12.0,LengthUnit.FEET);
		assertFalse(feet1.equals(null));
	}
}
