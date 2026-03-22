package com.quantityMeasurement.applicationLayer;

import com.quantityMeasurement.controller.QuantityMeasurementController;
import com.quantityMeasurement.DTOs.QuantityDTO;
import com.quantityMeasurement.repository.IQuantityMeasurementRepository;
import com.quantityMeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.quantityMeasurement.service.QuantityMeasurementServiceImpl;
import com.quantityMeasurement.utils.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementApp {
	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementApp.class);

	public static void main(String[] args) {
		logger.info("=== Starting Quantity Measurement Enterprise Application ===");

		IQuantityMeasurementRepository repository;
		if ("database".equalsIgnoreCase(ApplicationConfig.getProperty("repository.type"))) {
			repository = new QuantityMeasurementDatabaseRepository();
		} else {
			throw new UnsupportedOperationException("Only database repository mapped in this UC.");
		}

		repository.deleteAll(); // Reset for demonstration

		QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repository);
		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		logger.info("--- Executing Business Logic ---");

		// Success Case
		QuantityDTO feet = new QuantityDTO(1.0, "FEET", "LENGTH");
		QuantityDTO inches = new QuantityDTO(12.0, "INCHES", "LENGTH");
		controller.performComparison(feet, inches);

		// Error Case (Temperature Addition Validation)
		QuantityDTO c1 = new QuantityDTO(100.0, "CELSIUS", "TEMPERATURE");
		QuantityDTO c2 = new QuantityDTO(50.0, "CELSIUS", "TEMPERATURE");
		controller.performAddition(c1, c2);

		logger.info("--- Repository Report ---");
		logger.info("Total measurements stored: {}", repository.getTotalCount());
		logger.info("Connection Pool Stats: {}", repository.getPoolStatistics());

		repository.closeResources();
		logger.info("=== Shutdown Sequence Complete ===");
	}
}