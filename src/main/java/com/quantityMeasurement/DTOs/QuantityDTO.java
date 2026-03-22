package com.quantityMeasurement.DTOs;

public class QuantityDTO {
	public double value;
	public String unit;
	public String type;

	public QuantityDTO(double value, String unit, String type) {
		this.value = value;
		this.unit = unit.toUpperCase();
		this.type = type.toUpperCase();
	}
}
