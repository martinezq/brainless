package org.mysoft.brainless.neural.util;

public class ActivationFunction {

	public final static double logSigmoid(double value, double beta) {
		return 1 / (1 + Math.pow(Math.E, (-beta * value)));
	}
	
}
