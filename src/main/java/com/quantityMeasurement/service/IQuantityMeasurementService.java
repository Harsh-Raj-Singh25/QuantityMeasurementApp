package com.quantityMeasurement.service;

import com.quantityMeasurement.DTOs.QuantityDTO;

//IQuantityMeasurementService.java
public interface IQuantityMeasurementService {
	boolean compare(QuantityDTO d1, QuantityDTO d2);

	double add(QuantityDTO d1, QuantityDTO d2);
}

