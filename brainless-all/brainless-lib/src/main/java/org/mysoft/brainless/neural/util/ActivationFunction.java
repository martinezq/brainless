package org.mysoft.brainless.neural.util;

public class ActivationFunction {

	public final static double logSigmoid(double value, double beta) {
		return 1 / (1 + Math.pow(Math.E, (-beta * value)));
	}
	
	public final static double sin(double value) {
		return 0.5 * Math.sin(value) + 0.5;
		
	}
}
