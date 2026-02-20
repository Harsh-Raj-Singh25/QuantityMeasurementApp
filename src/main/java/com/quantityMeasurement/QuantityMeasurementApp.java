package com.quantityMeasurement;

public class QuantityMeasurementApp {
	public static void main(String[] args) {
		Feet feet1=new Feet(5.0);
		Feet feet2=new Feet(5.0);
		
		System.out.println("Feet Equality : "+ feet1.equals(feet2));
		
		Inch inch1 = new Inch(1.0);
		Inch inch2 = new Inch(1.0);
		
		System.out.println("Inch Equality : "+ inch1.equals(inch2));
		
	}
}
