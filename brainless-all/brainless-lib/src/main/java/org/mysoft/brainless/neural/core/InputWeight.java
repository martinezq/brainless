package org.mysoft.brainless.neural.core;

public class InputWeight {

	private static final double MIN = -1.0;
	private static final double MAX = 1.0;
	
	private double value;
	
	public static InputWeight create(double value) {
		InputWeight w = new InputWeight();
		w.value = value;
		return w;
	}
	
	public double getValue() {
		return value;
	}

	public InputWeight duplicate() {
		return InputWeight.create(this.getValue());
	}

	public static InputWeight random() {
		double value =  (MAX - MIN) * Math.random() + MIN;
		return create(value);
	}
	
	public static InputWeight max() {
		return create(MAX);
	}
	
	public static InputWeight min() {
		return create(MIN);
	}
	
	public static InputWeight zero() {
		return create(0.0);
	}

	public void increase() {
		value = value * 1.0001;
	}

	public void  decrease() {
		value = value * 0.9999;
	}

	public void normalize() {
		if(value < MIN) {
			value = MIN;
		}
		
		if(value > MAX) {
			value = MAX;
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputWeight other = (InputWeight) obj;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	
}
