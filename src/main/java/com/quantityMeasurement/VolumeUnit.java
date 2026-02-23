package com.quantityMeasurement;

public enum VolumeUnit implements IMeasurable {
	LITRE(1.0),MILLILITRE(0.001),GALLON(3.78541);
	
	private final double conversionFactor;
	
	VolumeUnit(double conversionFactor){
		this.conversionFactor=conversionFactor;
	}
	@Override
	public double getConversionFactor() {
		return conversionFactor;
	}

	@Override
	public double convertToBaseUnit(double value) {
		// TODO Auto-generated method stub
		return value * getConversionFactor();
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		// TODO Auto-generated method stub
		return baseValue / getConversionFactor();
	}
	 public double convert(double value, VolumeUnit targetUnit) {
	        double baseValue = this.convertToBaseUnit(value);
	        return baseValue / targetUnit.getConversionFactor();
	 }

	@Override
	public String getUnitName() {
		// TODO Auto-generated method stub
		return this.getUnitName();
	}	
}