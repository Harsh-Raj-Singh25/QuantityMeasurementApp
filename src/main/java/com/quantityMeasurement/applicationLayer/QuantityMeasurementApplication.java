package com.quantityMeasurement.applicationLayer;

import com.quantityMeasurement.controller.QuantityMeasurementController;
import com.quantityMeasurement.service.IQuantityMeasurementService;
import com.quantityMeasurement.service.QuantityMeasurementServiceImpl;
import com.quantityMeasurement.repository.IQuantityMeasurementRepository;
import com.quantityMeasurement.repository.QuantityMeasurementCacheRepository;
import com.quantityMeasurement.DTOs.QuantityDTO;

/**
 * UC15: Entry point for the N-Tier Quantity Measurement Application.
 * Coordinates initialization and delegates all logic to the Controller.
 */
public class QuantityMeasurementApplication {

	public static void main(String[] args) {
		System.out.println("=== Initializing Quantity Measurement N-Tier System ===");

		// 1. Initialize Repository (Singleton Pattern)
		// Provides centralized data storage for operation history.
		IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();

		// 2. Initialize Service (Dependency Injection)
		// Encapsulates all business logic and conversion rules.
		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repo);

		// 3. Initialize Controller (Facade Pattern)
		// Orchestrates interactions between user input and the service layer.
		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		// --- 4. Run Demonstration ---

		// Example 1: Length Equality (Cross-Unit)
		System.out.println("\n--- Length Equality Demonstration ---");
		QuantityDTO feet = new QuantityDTO(1.0, "FEET", "LENGTH");
		QuantityDTO inches = new QuantityDTO(12.0, "INCHES", "LENGTH");
		controller.performComparison(feet, inches); //

		// Example 2: Weight Addition
		System.out.println("\n--- Weight Addition Demonstration ---");
		QuantityDTO kg = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
		QuantityDTO grams = new QuantityDTO(500.0, "GRAM", "WEIGHT");
		controller.performAddition(kg, grams); //

		// Example 3: Temperature Operations (Handling Selective Support)
		System.out.println("\n--- Temperature Demonstration ---");
		QuantityDTO celsius = new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE");
		QuantityDTO fahrenheit = new QuantityDTO(212.0, "FAHRENHEIT", "TEMPERATURE");

		// Comparison is supported for Temperature
		controller.performComparison(celsius, fahrenheit);

		// Addition is NOT supported for Temperature (Triggers Error Entity)
		controller.performAddition(celsius, celsius);

		System.out.println("\n=== Demonstration Complete. Check Repository for History ===");
	}
}