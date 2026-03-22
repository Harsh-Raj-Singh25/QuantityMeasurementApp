package com.quantityMeasurement.service;

import com.quantityMeasurement.DTOs.QuantityDTO;
import com.quantityMeasurement.entities.QuantityMeasurementEntity;
import com.quantityMeasurement.repository.IQuantityMeasurementRepository;
import com.quantityMeasurement.unit.IMeasurable;
import com.quantityMeasurement.unit.LengthUnit;
import com.quantityMeasurement.unit.TemperatureUnit;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
	private final IQuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	private IMeasurable getUnitInstance(String type, String unit) {
		if ("LENGTH".equals(type))
			return LengthUnit.valueOf(unit);
		if ("TEMPERATURE".equals(type))
			return TemperatureUnit.valueOf(unit);
		throw new IllegalArgumentException("Unsupported Type: " + type);
	}

	@Override
	public boolean compare(QuantityDTO d1, QuantityDTO d2) {
		try {
			if (!d1.type.equals(d2.type))
				throw new IllegalArgumentException("Type mismatch");

			IMeasurable u1 = getUnitInstance(d1.type, d1.unit);
			IMeasurable u2 = getUnitInstance(d2.type, d2.unit);

			double base1 = u1.convertToBaseUnit(d1.value);
			double base2 = u2.convertToBaseUnit(d2.value);

			boolean result = Double.compare(base1, base2) == 0;

			repository.save(new QuantityMeasurementEntity(d1.value + " " + d1.unit, d2.value + " " + d2.unit, "COMPARE",
					d1.type, String.valueOf(result), false));
			return result;
		} catch (Exception e) {
			repository.save(new QuantityMeasurementEntity(d1.value + " " + d1.unit, d2.value + " " + d2.unit, "COMPARE",
					d1.type, e.getMessage(), true));
			throw e;
		}
	}

	@Override
	public QuantityDTO add(QuantityDTO d1, QuantityDTO d2) {
		try {
			if (!d1.type.equals(d2.type))
				throw new IllegalArgumentException("Type mismatch");

			IMeasurable u1 = getUnitInstance(d1.type, d1.unit);
			IMeasurable u2 = getUnitInstance(d2.type, d2.unit);

			if (!u1.supportsArithmetic() || !u2.supportsArithmetic()) {
				throw new UnsupportedOperationException("Addition not supported for this unit");
			}

			double base1 = u1.convertToBaseUnit(d1.value);
			double base2 = u2.convertToBaseUnit(d2.value);
			double finalValue = u1.convertFromBaseUnit(base1 + base2);

			repository.save(new QuantityMeasurementEntity(d1.value + " " + d1.unit, d2.value + " " + d2.unit, "ADD",
					d1.type, finalValue + " " + d1.unit, false));

			return new QuantityDTO(finalValue, d1.unit, d1.type);
		} catch (Exception e) {
			repository.save(new QuantityMeasurementEntity(d1.value + " " + d1.unit, d2.value + " " + d2.unit, "ADD",
					d1.type, e.getMessage(), true));
			throw e;
		}
	}
}