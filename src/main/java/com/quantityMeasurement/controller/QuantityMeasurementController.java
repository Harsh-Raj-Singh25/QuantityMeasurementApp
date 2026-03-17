package com.quantityMeasurement.controller;

import org.springframework.stereotype.Controller;

import com.quantityMeasurement.DTOs.QuantityDTO;
import com.quantityMeasurement.service.IQuantityMeasurementService;

//QuantityMeasurementController.java
@Controller
public class QuantityMeasurementController {
	private final IQuantityMeasurementService service;

	public QuantityMeasurementController(IQuantityMeasurementService service) {
		this.service = service;
	}

	/**
	 * Performs addition by delegating to the service layer. Designed to be easily
	 * adaptable for future REST POST /add endpoints.
	 */
	public void performAddition(QuantityDTO d1, QuantityDTO d2) {
		try {
			System.out.println("Controller: Requesting Addition for " + d1.type);
			// The service returns a result DTO or throws an exception
			QuantityDTO result = service.add(d1, d2);

			System.out.println("Result: " + d1.value + " " + d1.unit + " + " + d2.value + " " + d2.unit + " = "
					+ result.value + " " + result.unit);
		} catch (Exception e) {
			// Service-level errors (like Temperature Addition) are caught here
			System.err.println("Operation Failed: " + e.getMessage());
		}
	}

	public void performComparison(QuantityDTO d1, QuantityDTO d2) {
		System.out.println("Controller: Handling Comparison Request...");
		boolean result = service.compare(d1, d2);
		System.out.println("Result: " + result);
	}
}