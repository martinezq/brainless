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

	public void addInputs(LinkedList<NeuronInput> inputs) {
		for(NeuronInput input: inputs) {
			addInput(input);
		}
	}

	public void addInputs(NeuronLayer layer) {
		for(Neuron neuron: layer) {
			addInput(neuron);
		}
	}

	
	public void addInput(NeuronInput input) {
		addInput(input, 2 * Math.random() - 1.0);
	}
	
	public void addInput(NeuronInput input, double weight) {
		inputs.add(input);
		weights.put(input, weight);
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
			result = input.calculatedOutput() * weights.get(input);
		}
		
		return result;
	}

	public Neuron duplicate() {
		return null;
	}
	
	
}
