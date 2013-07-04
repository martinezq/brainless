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
		double value = 0.0002 * Math.random() - 0.0001;
		return create(value);
	}
	
}
