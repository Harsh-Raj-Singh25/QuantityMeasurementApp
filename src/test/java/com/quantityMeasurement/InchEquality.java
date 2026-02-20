package com.quantityMeasurement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InchEquality {
	// Inch equality Test
		@Test
		public void testEquality_SameValue()
		{
			Inch inch1=new Inch(1.0);
			Inch inch2=new Inch(1.0);
			assertTrue(inch1.equals(inch2));
		}
		
		@Test
		public void testEquality_DifferentValue() {
			Inch inch1=new Inch(1.0);
			Inch inch2=new Inch(5.0);
			assertFalse(inch1.equals(inch2));
		}
		
		@Test
		public void testEquality_NullComparison() {
			Inch inch1=new Inch(1.0);
			assertFalse(inch1.equals(null));
		}
		
		@Test
		public void testEquality_NonNumericInput() {
			String str="Harsh";
			assertFalse(new Inch(4.0).equals(str));
		}
		
		@Test
		public void testEquality_SameReference() {
			Inch inch1=new Inch(1.0);
			assertTrue(inch1.equals(inch1));
		}
		
}
