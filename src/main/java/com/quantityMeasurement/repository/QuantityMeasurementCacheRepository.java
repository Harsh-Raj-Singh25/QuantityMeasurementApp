package com.quantityMeasurement.repository;

//QuantityMeasurementCacheRepository.java
import java.util.ArrayList;
import java.util.List;

import com.quantityMeasurement.entities.QuantityMeasurementEntity;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
	private static QuantityMeasurementCacheRepository instance;
	private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

	private QuantityMeasurementCacheRepository() {
	}

	public static synchronized QuantityMeasurementCacheRepository getInstance() {
		if (instance == null)
			instance = new QuantityMeasurementCacheRepository();
		return instance;
	}

	@Override
	public void save(QuantityMeasurementEntity entity) {
		cache.add(entity);
		System.out.println("Repository: Entity Saved to Cache.");
	}
}  