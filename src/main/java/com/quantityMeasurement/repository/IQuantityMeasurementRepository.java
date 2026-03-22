package com.quantityMeasurement.repository;

import com.quantityMeasurement.entities.QuantityMeasurementEntity;

public interface IQuantityMeasurementRepository {
	void save(QuantityMeasurementEntity entity);

	long getTotalCount();

	void deleteAll();

	String getPoolStatistics();

	void closeResources();
}
