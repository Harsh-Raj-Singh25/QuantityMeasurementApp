package com.quantityMeasurement.applicationLayer;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quantityMeasurement.DTOs.QuantityDTO;
import com.quantityMeasurement.controller.QuantityMeasurementController;
import com.quantityMeasurement.repository.IQuantityMeasurementRepository;
import com.quantityMeasurement.repository.QuantityMeasurementCacheRepository;
import com.quantityMeasurement.service.QuantityMeasurementServiceImpl;

//QuantityMeasurementApp.java
@SpringBootApplication
public class QuantityMeasurementApplication {
	public static void main(String[] args) {
		// 1. Initialize Repository (Singleton)
		IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();

		// 2. Initialize Service (Dependency Injection)
		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repo);

		// 3. Initialize Controller
		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		// 4. Run Demonstration
		QuantityDTO feet = new QuantityDTO(1.0, "FEET", "LENGTH");
		QuantityDTO inches = new QuantityDTO(12.0, "INCHES", "LENGTH");

		controller.performComparison(feet, inches);
	}
}
