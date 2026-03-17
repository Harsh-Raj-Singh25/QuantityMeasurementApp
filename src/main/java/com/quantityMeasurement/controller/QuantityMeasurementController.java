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

	public void performComparison(QuantityDTO d1, QuantityDTO d2) {
		System.out.println("Controller: Handling Comparison Request...");
		boolean result = service.compare(d1, d2);
		System.out.println("Result: " + result);
	}
}