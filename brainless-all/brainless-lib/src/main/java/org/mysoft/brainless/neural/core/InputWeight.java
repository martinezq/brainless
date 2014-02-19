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
	
}
