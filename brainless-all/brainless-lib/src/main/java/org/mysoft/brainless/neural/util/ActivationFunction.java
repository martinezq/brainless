package org.mysoft.brainless.neural.util;

public class ActivationFunction {

	public final static double logSigmoid(double value, double beta) {
		return 2.0 / (1.0 + Math.pow(Math.E, (-beta * value))) - 1.0;
	}
	
	public final static double sin(double value) {
		return 0.5 * Math.sin(value) + 0.5;
		
	}
}
