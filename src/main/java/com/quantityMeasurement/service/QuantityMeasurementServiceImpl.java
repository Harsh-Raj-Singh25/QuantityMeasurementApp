package com.quantityMeasurement.service;

import com.quantityMeasurement.model.*;
import com.quantityMeasurement.repository.QuantityMeasurementRepository;
import com.quantityMeasurement.unit.IMeasurable;
import com.quantityMeasurement.unit.LengthUnit;
import com.quantityMeasurement.unit.TemperatureUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    private IMeasurable getUnitInstance(String type, String unit) {
        if ("LENGTHUNIT".equalsIgnoreCase(type)) return LengthUnit.valueOf(unit.toUpperCase());
        if ("TEMPERATUREUNIT".equalsIgnoreCase(type)) return TemperatureUnit.valueOf(unit.toUpperCase());
        throw new IllegalArgumentException("Unsupported Measurement Type: " + type);
    }

    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {
        QuantityDTO q1 = input.getThisQuantityDTO();
        QuantityDTO q2 = input.getThatQuantityDTO();
        
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        populateEntityBase(entity, q1, q2, OperationType.COMPARE);

        try {
            if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
                throw new IllegalArgumentException("Type mismatch");
            }
            
            IMeasurable u1 = getUnitInstance(q1.getMeasurementType(), q1.getUnit());
            IMeasurable u2 = getUnitInstance(q2.getMeasurementType(), q2.getUnit());
            
            boolean result = Double.compare(u1.convertToBaseUnit(q1.getValue()), u2.convertToBaseUnit(q2.getValue())) == 0;
            
            entity.setResultString(String.valueOf(result));
            entity.setResultValue(0.0);
            repository.save(entity);
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
            repository.save(entity);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO add(QuantityInputDTO input) {
        QuantityDTO q1 = input.getThisQuantityDTO();
        QuantityDTO q2 = input.getThatQuantityDTO();
        
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        populateEntityBase(entity, q1, q2, OperationType.ADD);

        try {
            if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
                throw new IllegalArgumentException("Cannot perform arithmetic between different categories");
            }

            IMeasurable u1 = getUnitInstance(q1.getMeasurementType(), q1.getUnit());
            IMeasurable u2 = getUnitInstance(q2.getMeasurementType(), q2.getUnit());

            if (!u1.supportsArithmetic() || !u2.supportsArithmetic()) {
                throw new IllegalArgumentException("Addition not supported for absolute temperatures");
            }

            double base1 = u1.convertToBaseUnit(q1.getValue());
            double base2 = u2.convertToBaseUnit(q2.getValue());
            double finalValue = u1.convertFromBaseUnit(base1 + base2);

            entity.setResultValue(finalValue);
            entity.setResultUnit(q1.getUnit());
            entity.setResultMeasurementType(q1.getMeasurementType());
            repository.save(entity);
            
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
            repository.save(entity);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<QuantityMeasurementDTO> getHistoryByOperation(String operation) {
        List<QuantityMeasurementEntity> entities = repository.findByOperation(OperationType.valueOf(operation.toUpperCase()));
        return QuantityMeasurementDTO.fromEntityList(entities);
    }

    private void populateEntityBase(QuantityMeasurementEntity entity, QuantityDTO q1, QuantityDTO q2, OperationType type) {
        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnit());
        entity.setThisMeasurementType(q1.getMeasurementType());
        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnit());
        entity.setThatMeasurementType(q2.getMeasurementType());
        entity.setOperation(type);
    }
}