package com.quantityMeasurement.controller;

import com.quantityMeasurement.dto.QuantityInputDTO;
import com.quantityMeasurement.dto.QuantityMeasurementDTO;
import com.quantityMeasurement.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurements", description = "REST API for quantity operations")
public class QuantityMeasurementController {

	@Autowired
	private IQuantityMeasurementService service;

	@PostMapping("/compare")
	@Operation(summary = "Compare two quantities")
	public ResponseEntity<QuantityMeasurementDTO> compareQuantities(@Valid @RequestBody QuantityInputDTO input) {
		return ResponseEntity.ok(service.compare(input));
	}

	@PostMapping("/add")
	@Operation(summary = "Add two quantities")
	public ResponseEntity<QuantityMeasurementDTO> addQuantities(@Valid @RequestBody QuantityInputDTO input) {
		return ResponseEntity.ok(service.add(input));
	}

	@GetMapping("/history/operation/{operation}")
	@Operation(summary = "Get operation history by type")
	public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(@PathVariable String operation) {
		return ResponseEntity.ok(service.getHistoryByOperation(operation));
	}
}