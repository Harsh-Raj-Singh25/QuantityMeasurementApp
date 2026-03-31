package com.quantityMeasurement.controller;

import com.quantityMeasurement.model.QuantityInputDTO;
import com.quantityMeasurement.model.QuantityMeasurementDTO;
import com.quantityMeasurement.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Allows your frontend to talk to this backend 
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
	
	@PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> convertQuantity(@Valid @RequestBody QuantityInputDTO input) {
        QuantityMeasurementDTO response = service.convert(input);
        
        // If the service caught an error (like incompatible units), return a 400 Bad Request
        if (response.isError()) {
            return ResponseEntity.badRequest().body(response);
        }
        
        // Otherwise, return 200 OK
        return ResponseEntity.ok(response);
    }
}