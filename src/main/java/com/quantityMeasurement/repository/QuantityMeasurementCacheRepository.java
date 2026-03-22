package com.quantityMeasurement.repository;

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

	public void save(QuantityMeasurementEntity entity) {
		cache.add(entity);
		System.out.println("Repository: Entity Saved to Cache.");
	}

	@Override
	public long getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPoolStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeResources() {
		// TODO Auto-generated method stub
		
	}
}
