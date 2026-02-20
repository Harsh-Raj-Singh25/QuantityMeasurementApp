package com.quantityMeasurement;

public class QuantityMeasurementApp {
	public static void main(String[] args) {
		Feet feet1=new Feet(5.0);
		Feet feet2=new Feet(5.0);
		
		System.out.println("Equality :"+ feet1.equals(feet2));
	}
}
