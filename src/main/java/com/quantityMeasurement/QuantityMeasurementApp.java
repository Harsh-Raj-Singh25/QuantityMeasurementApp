package com.quantityMeasurement;

import com.quantityMeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {
	// Create a generic method to demonstrate Length equality check
	public static boolean demonstrateLengthEquality(Length l1,Length l2) {
		return l1.equals(l2);
	}
	// Define a static method to demonstrate Feet equality check
	public static void demonstrateFeetEquality() {
		Feet feet1=new Feet(5.0);
		Feet feet2=new Feet(5.0);
		
		System.out.println("Feet Equality : "+ feet1.equals(feet2));
	}
	// Define a static method to demonstrate Inches equality check
	public static void demonstrateInchesEquality() {
		Inch inch1 = new Inch(1.0);
		Inch inch2 = new Inch(1.0);
		
		System.out.println("Inch Equality : "+ inch1.equals(inch2));
	}
	// Create a static method to demonstrate Feet and Inches comparison
	public static void demonstrateFeetInchesComparison() {
		Length l1= new Length(1.0,LengthUnit.FEET);
		Length l2=new Length(12.0,LengthUnit.INCHES);
		System.out.println("Feet-Inches Equality -> " + l1.equals(l2));
	}
	
	public static void main(String[] args) {
		demonstrateFeetEquality();
		demonstrateInchesEquality();
		demonstrateFeetInchesComparison();
	}
}
