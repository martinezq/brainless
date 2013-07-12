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
		double rand = 2.0 * Math.random() - 1.0;
		double value = 0;
		
		if(rand > -0.9 && rand < 0.9) {
			value = 0.01 * Math.random() - 0.005;
		} else if(rand > -0.95 && rand < 0.95) {
			value = 2.0 * Math.random() - 1.0;
		} else {
			value = 200.0 * Math.random() - 100.0;
		}
		
		return create(value);
	}
	
}
