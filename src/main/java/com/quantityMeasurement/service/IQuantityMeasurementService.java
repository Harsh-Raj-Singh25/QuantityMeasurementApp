package com.quantityMeasurement.service;

import com.quantityMeasurement.model.QuantityInputDTO;
import com.quantityMeasurement.model.QuantityMeasurementDTO;
import java.util.List;

public interface IQuantityMeasurementService {
	QuantityMeasurementDTO compare(QuantityInputDTO input);

	QuantityMeasurementDTO add(QuantityInputDTO input);

	List<QuantityMeasurementDTO> getHistoryByOperation(String operation);
}