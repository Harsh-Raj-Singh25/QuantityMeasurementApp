package com.quantityMeasurement.DTOs;

//QuantityDTO.java - For external communication
public class QuantityDTO {
 public double value;
 public String unit;
 public String type;

 public QuantityDTO(double value, String unit, String type) {
     this.value = value;
     this.unit = unit;
     this.type = type;
 }
}




