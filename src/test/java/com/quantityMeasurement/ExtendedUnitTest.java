package com.quantityMeasurement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.quantityMeasurement.model.Quantity;


public class ExtendedUnitTest {
	@Test
	public void testEquality_YardToYard_SameValue() {
			Quantity<LengthUnit> l1=new Quantity<>(1.0,LengthUnit.YARDS);
			Quantity<LengthUnit> l2=new Quantity<>(1.0,LengthUnit.YARDS);
//			Length l1=new Length(1.0,LengthUnit.YARDS);
//			Length l2=new Length(1.0,LengthUnit.YARDS);
			
			assertTrue(l1.equals(l2));
	}

	@Test
	public void testEquality_YardToYard_DifferentValue() {
		Quantity<LengthUnit> l1=new Quantity<>(1.0,LengthUnit.YARDS);
		Quantity<LengthUnit> l2=new Quantity<>(2.0,LengthUnit.YARDS);
//		Length l1=new Length(1.0,LengthUnit.YARDS);
//		Length l2=new Length(2.0,LengthUnit.YARDS);
		
		assertFalse(l1.equals(l2));	
	}
	
	//Verifies that Quantity(3.0, FEET) equals Quantity(1.0, YARDS).
	@Test
	public void testEquality_YardToFeet_EquivalentValue() {
		Quantity<LengthUnit> l1=new Quantity<>(1.0,LengthUnit.YARDS);
		Quantity<LengthUnit> l2=new Quantity<>(3.0,LengthUnit.FEET);
//		Length l1=new Length(1.0,LengthUnit.YARDS);
//		Length l2=new Length(3.0,LengthUnit.FEET);
		
		assertTrue(l1.equals(l2));
	}
//	//Verifies that Quantity(1.0, YARDS) equals Quantity(36.0, INCHES).
//	@Test
//	public void testEquality_FeetToYard_EquivalentValue() {
//		Length l1=new Length(3.0,LengthUnit.FEET);
//		Length l2=new Length(1.0,LengthUnit.YARDS);
//		
//		assertTrue(l1.equals(l2));
//	}
//	
//	@Test
//	public void testEquality_YardToInches_EquivalentValue() {
//		Length l1=new Length(1.0,LengthUnit.YARDS);
//		Length l2=new Length(36.0,LengthUnit.INCHES);
//		
//		assertTrue(l1.equals(l2));
//	}
//
//	@Test
//	public void testEquality_InchesToYard_EquivalentValue() {
//		Length l1=new Length(36.0,LengthUnit.INCHES);
//		Length l2=new Length(1.0,LengthUnit.YARDS);
//		
//		assertTrue(l1.equals(l2));
//	}
//
//	@Test
//	public void testEquality_YardToFeet_NonEquivalentValue() {
//		Length l1=new Length(1.0,LengthUnit.YARDS);
//		Length l2=new Length(2.0,LengthUnit.FEET);
//		
//		assertFalse(l1.equals(l2));
//	}
//
//	@Test
//	public void testEquality_centimetersToInches_EquivalentValue(){
//		Length l1=new Length(1.0,LengthUnit.CENTIMETERS);
//		Length l2=new Length(0.393701,LengthUnit.INCHES);
//		
//		assertTrue(l1.equals(l2));
//	}
//
//	@Test
//	public void testEquality_centimetersToFeet_NonEquivalentValue() {
//		Length l1=new Length(1.0,LengthUnit.CENTIMETERS);
//		Length l2=new Length(1.0,LengthUnit.FEET);
//		
//		assertFalse(l1.equals(l2));
//	}
//
//	@Test
//	public void testEquality_MultiUnit_TransitiveProperty(){
//		Length l1=new Length(1.0,LengthUnit.YARDS);
//		Length l2=new Length(3.0,LengthUnit.FEET);
//		Length l3=new Length(36.0,LengthUnit.INCHES);
//		
//		assertTrue(l1.equals(l2));
//		assertTrue(l2.equals(l3));
//		assertTrue(l1.equals(l3));
//		
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void testEquality_YardWithNullUnit() {
//		Length l1=new Length(1.0,LengthUnit.YARDS);
//		Length l2=new Length(1.0,null);
//		
//		assertFalse(l1.equals(l2));
//	}
//
//	@Test
//	public void testEquality_YardSameReference() {
//		Length l1=new Length(1.0,LengthUnit.YARDS); 
//		
//		assertTrue(l1.equals(l1));
//	}
//
//	@Test
//	public void testEquality_YardNullComparison() {
//		Length l1=new Length(1.0,LengthUnit.YARDS); 
//		assertFalse(l1.equals(null));
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void testEquality_CentimetersWithNullUnit() {
//		Length l1=new Length(1.0,LengthUnit.CENTIMETERS);
//		Length l2=new Length(1.0,null);
//		
//		assertTrue(l1.equals(l2));
//	}
//
//	@Test
//	public void rtestEquality_CentimetersSameReference() {
//		Length l1=new Length(1.0,LengthUnit.CENTIMETERS); 
//		
//		assertTrue(l1.equals(l1));
//	}
//
//	@Test
//	public void testEquality_CentimetersNullComparison(){
//		Length l1=new Length(1.0,LengthUnit.CENTIMETERS);
//		
//		assertFalse(l1.equals(null));
//	}
//
//	@Test
//	public void testEquality_AllUnits_ComplexScenario() {
//		Length l1=new Length(2.0,LengthUnit.YARDS);
//		Length l2=new Length(6.0,LengthUnit.FEET);
//		Length l3=new Length(72.0,LengthUnit.INCHES);
//		
//		assertTrue(l1.equals(l2));
//		
//		assertTrue(l2.equals(l3));
//		assertTrue(l1.equals(l3));
//	}

}