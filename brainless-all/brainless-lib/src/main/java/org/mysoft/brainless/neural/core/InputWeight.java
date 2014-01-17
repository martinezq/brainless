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
		if(Math.random() < 0.1) {
			value = 200.0 * Math.random() - 100.0;
		} else {
			value = 20.0 * Math.random() - 10.0;
		}
		return create(value);
	}

	public void increase() {
		value = value * 1.0001;
	}

	public void  decrease() {
		value = value * 0.9999;
	}
	
}
