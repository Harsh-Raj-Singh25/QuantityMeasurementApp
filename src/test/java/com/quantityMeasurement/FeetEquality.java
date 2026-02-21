package com.quantityMeasurement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FeetEquality {
	
	@Test
	public void testEquality_SameValue() {
		Feet feet1=new Feet(1.0);
		Feet feet2=new Feet(1.0);
		
		assertTrue(feet1.equals(feet2));
	}
	
	@Test
	public void testEquality_DifferentValue() {
		Feet feet1=new Feet(4.0);
		Feet feet2=new Feet(5.2);
		
		assertFalse(feet1.equals(feet2));
	}
	
	@Test
	public void testEquality_NullComparison() {
		Feet feet1=new Feet(4.0);
		
		assertFalse(feet1.equals(null));
	}
	
	@Test
	public void testEquality_NonNumericalInput() {
		Feet feet1=new Feet(4.0);
		String str="Harsh";
		assertFalse(feet1.equals(str));
		
	}
	
	@Test
	public void testEquality_SameReference() {
		Feet feet1=new Feet(4.0);
		assertTrue(feet1.equals(feet1));
	}
	
}
