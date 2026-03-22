package com.quantityMeasurement.entities;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public final String operand1;
	public final String operand2;
	public final String operation;
	public final String measurementType;
	public final String result;
	public final boolean hasError;

	public QuantityMeasurementEntity(String op1, String op2, String operation, String type, String res, boolean error) {
		this.operand1 = op1;
		this.operand2 = op2;
		this.operation = operation;
		this.measurementType = type;
		this.result = res;
		this.hasError = error;
	}
}