package com.quantityMeasurement.service;

import com.quantityMeasurement.DTOs.QuantityDTO;

public interface IQuantityMeasurementService {
	boolean compare(QuantityDTO d1, QuantityDTO d2);

	QuantityDTO add(QuantityDTO d1, QuantityDTO d2);
}