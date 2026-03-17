package com.quantityMeasurement.service;

import com.quantityMeasurement.DTOs.QuantityDTO;
import com.quantityMeasurement.entities.QuantityMeasurementEntity;
import com.quantityMeasurement.repository.IQuantityMeasurementRepository;

//QuantityMeasurementServiceImpl.java
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
	private final IQuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repo) {
		this.repository = repo;
	}

	@Override
	public boolean compare(QuantityDTO d1, QuantityDTO d2) {
		try {
			// Logic: Convert DTO to Models -> Normalize -> Compare
			// Simplified for brevity:
			boolean result = Double.compare(d1.value, d2.value) == 0;

			repository.save(new QuantityMeasurementEntity(d1.value + d1.unit, d2.value + d2.unit, "COMPARE",
					String.valueOf(result), false));
			return result;
		} catch (Exception e) {
			repository.save(new QuantityMeasurementEntity(d1.unit, d2.unit, "COMPARE", e.getMessage(), true));
			throw e;
		}
	}

	@Override
	public double add(QuantityDTO d1, QuantityDTO d2) {
		// Implementation of addition logic...
		return d1.value + d2.value;
	}
}