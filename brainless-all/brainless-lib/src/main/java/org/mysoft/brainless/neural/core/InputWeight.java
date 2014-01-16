package org.mysoft.brainless.neural.core;

public class InputWeight {

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
		double value = 0;
		if(Math.random() < 0.05) {
			value = 20.0 * Math.random() - 10.0;
		} else {
			value = 2.0 * Math.random() - 1.0;
		}
		return create(value);
	}
	
}
