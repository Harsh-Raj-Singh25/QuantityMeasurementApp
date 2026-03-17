package com.quantityMeasurement.DTOs;

import com.quantityMeasurement.IMeasurable;

//QuantityModel.java - Internal Generic Model
public class QuantityModel<U extends IMeasurable> {
	public final double value;
	public final U unit;

	public QuantityModel(double value, U unit) {
		this.value = value;
		this.unit = unit;
	}
}
