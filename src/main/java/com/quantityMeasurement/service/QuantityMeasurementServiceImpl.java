package com.quantityMeasurement.service;

import com.quantityMeasurement.model.*;
import com.quantityMeasurement.repository.QuantityMeasurementRepository;
import com.quantityMeasurement.unit.IMeasurable;
import com.quantityMeasurement.unit.LengthUnit;
import com.quantityMeasurement.unit.TemperatureUnit;
import com.quantityMeasurement.unit.VolumeUnit;
import com.quantityMeasurement.unit.WeightUnit;

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
        if ("WEIGHTUNIT".equalsIgnoreCase(type)) return WeightUnit.valueOf(unit.toUpperCase());
        if ("VOLUMEUNIT".equalsIgnoreCase(type)) return VolumeUnit.valueOf(unit.toUpperCase());
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
    
    // conversion logic
    @Override
    public QuantityMeasurementDTO convert(QuantityInputDTO input) {
        QuantityDTO q1 = input.getThisQuantityDTO();
        QuantityDTO q2 = input.getThatQuantityDTO();

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        
        // Use your helper method (assuming you added CONVERT to your OperationType enum)
        populateEntityBase(entity, q1, q2, OperationType.CONVERT);

        try {
            //  Prevent cross-type conversions (e.g., Length to Temperature)
            if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
                throw new IllegalArgumentException("Cannot convert between different measurement types.");
            }

            //  Get the actual Enum instances using your factory method
            IMeasurable sourceUnit = getUnitInstance(q1.getMeasurementType(), q1.getUnit());
            IMeasurable targetUnit = getUnitInstance(q2.getMeasurementType(), q2.getUnit());

            //  The Core Math: Convert to Base Unit -> Convert to Target Unit
            double baseValue = sourceUnit.convertToBaseUnit(q1.getValue());
            double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

            //   Rounding to 2 decimal places for clean UI (optional, but recommended)
            convertedValue = Math.round(convertedValue * 100.0) / 100.0;

            //  Populate the success results into the entity
            entity.setResultValue(convertedValue);
            entity.setResultUnit(q2.getUnit()); // We converted it TO q2's unit
            entity.setResultMeasurementType(q1.getMeasurementType());
            
            // Build a readable string like "1.0 YARDS = 36.0 INCHES"
            entity.setResultString(q1.getValue() + " " + q1.getUnit() + " = " + convertedValue + " " + q2.getUnit());
            
            repository.save(entity);
            
            //  Return the formatted DTO using your static mapper
            return QuantityMeasurementDTO.fromEntity(entity);

        } catch (Exception e) {
            // Handle errors gracefully and save the failure to the history table
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
            repository.save(entity);
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
}