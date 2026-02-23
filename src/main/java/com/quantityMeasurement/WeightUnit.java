package com.quantityMeasurement;

public enum WeightUnit {
	KILOGRAM(1.0), GRAM(0.001),  POUND(0.453592) ;
	private final double conversionFactor;

	// enum to represent different units and their conversion factors with
	// the base unit being Kilograms. This means all the conversion factors are defined
	// in terms of KGs.
	WeightUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public double getConversionFactor() {
		return conversionFactor;
	}
	
	/**
     * Responsibility 1: Convert value in this unit to the base unit (Feet).
     */
    public double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    } 
    /**
     * Responsibility 2: Convert a base unit value (Feet) back to this unit.
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }
    public double convert(double value, LengthUnit targetUnit) {
        double feetValue = this.convertToBaseUnit(value);
        return feetValue / targetUnit.getConversionFactor();
    }
}
