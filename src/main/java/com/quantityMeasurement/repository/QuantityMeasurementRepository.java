package com.quantityMeasurement.repository;

import com.quantityMeasurement.model.OperationType;
import com.quantityMeasurement.model.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
	List<QuantityMeasurementEntity> findByOperation(OperationType operation);

	List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);

	List<QuantityMeasurementEntity> findByIsErrorTrue();

	long countByOperationAndIsErrorFalse(OperationType operation);
}