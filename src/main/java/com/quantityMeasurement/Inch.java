package com.quantityMeasurement;

public class Inch {
	private final double value;
	
	public Inch(double value) {
		this.value=value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		
		if(obj== null) {
			return false;
		}
		
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Inch other=(Inch) obj;
		
		return Double.compare(this.value, other.value) == 0;
		
	}
}
