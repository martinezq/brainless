package org.mysoft.brainless.neural.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.mysoft.brainless.neural.util.ActivationFunction;

public class Neuron implements NeuronInput {

	protected final static double BETA = 1;
	
	protected final List<NeuronInput> inputs = new LinkedList<>();
	protected final Map<NeuronInput, Double> weights = new HashMap<>();
	
	protected double calculatedValue = 0;
	protected boolean isCalculated = false;
	
	public double activator(double value) {
		return ActivationFunction.logSigmoid(value, BETA);
	}

	@Override
	public double calculatedOutput() {
		if(!isCalculated) {
			calculateValue();
		}
		
		return calculatedValue;
	}

	public void addInput(NeuronInput input) {
		inputs.add(input);
		weights.put(input,  Math.random());
	}
	
	public void removeInput(NeuronInput input) {
		weights.remove(input);
		inputs.remove(input);
	}

	private void calculateValue() {
		calculatedValue = activator(calculateInputs());
		isCalculated = true;
	}
	
	public void reset() {
		isCalculated = false;
		calculatedValue = 0;
	}
	
	private double calculateInputs() {
		double result = 0;
		
		for(NeuronInput input: inputs) {
			result = input.calculatedOutput() * 1;
		}
		
		return result;
	}
	
	
}
