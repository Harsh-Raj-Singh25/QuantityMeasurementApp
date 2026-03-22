package com.quantityMeasurement.applicationLayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quantityMeasurement.DTOs.QuantityDTO;
import com.quantityMeasurement.controller.QuantityMeasurementController;
import com.quantityMeasurement.repository.IQuantityMeasurementRepository;
import com.quantityMeasurement.repository.QuantityMeasurementCacheRepository;
import com.quantityMeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.quantityMeasurement.service.QuantityMeasurementServiceImpl;
import com.quantityMeasurement.utils.ApplicationConfig;

public class QuantityMeasurementApplication {
	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementApplication.class);

	public static void main(String[] args) {
		logger.info("=== Starting Quantity Measurement Enterprise Application ===");

		// 1. Initialize Repository based on configuration Factory
		String repoType = ApplicationConfig.getProperty("repository.type");
		IQuantityMeasurementRepository repository;

		if ("database".equalsIgnoreCase(repoType)) {
			repository = new QuantityMeasurementDatabaseRepository();
			logger.info("Using JDBC Database Repository.");
		} else {
			repository = QuantityMeasurementCacheRepository.getInstance();
			logger.info("Using In-Memory Cache Repository.");
		}

		// Clean slate for testing
		repository.deleteAll();

		// 2. Dependency Injection
		QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repository);
		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		// 3. Execution Demonstrations
		logger.info("--- Executing Business Logic ---");
		QuantityDTO feet = new QuantityDTO(1.0, "FEET", "LENGTH");
		QuantityDTO inches = new QuantityDTO(12.0, "INCHES", "LENGTH");

		controller.performComparison(feet, inches);

		// 4. Reporting
		logger.info("--- Repository Report ---");
		logger.info("Total measurements stored: {}", repository.getTotalCount());
		logger.info("Connection Pool Stats: {}", repository.getPoolStatistics());

		// 5. Cleanup
		repository.closeResources();
		logger.info("=== Shutdown Sequence Complete ===");
	}
}