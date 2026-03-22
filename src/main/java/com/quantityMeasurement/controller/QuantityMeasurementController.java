package com.quantityMeasurement.controller;

import com.quantityMeasurement.DTOs.QuantityDTO;
import com.quantityMeasurement.service.IQuantityMeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementController {
	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementController.class);
	private final IQuantityMeasurementService service;

	public QuantityMeasurementController(IQuantityMeasurementService service) {
		this.service = service;
	}

	public void performComparison(QuantityDTO d1, QuantityDTO d2) {
		try {
			boolean result = service.compare(d1, d2);
			logger.info("Result of Comparison: {}", result);
		} catch (Exception e) {
			logger.error("Comparison Failed: {}", e.getMessage());
		}
	}

	public void performAddition(QuantityDTO d1, QuantityDTO d2) {
		try {
			QuantityDTO result = service.add(d1, d2);
			logger.info("Result of Addition: {} {}", result.value, result.unit);
		} catch (Exception e) {
			logger.error("Addition Failed: {}", e.getMessage());
		}
	}
}