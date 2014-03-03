package org.mysoft.brainless.neural.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mysoft.brainless.neural.util.ActivationFunction;

public class Neuron implements NeuronInput {

	protected final static double BETA = 1.0;
	
	protected final ArrayList<NeuronInput> inputs = new ArrayList<>();
	protected final ArrayList<InputWeight> inputWeights = new ArrayList<>();
	
	protected double calculatedValue = 0;
	protected boolean isCalculated = false;

	public static Neuron create() {
		return new Neuron();
	}
	
	public void applyWeights(List<InputWeight> weights) {
		inputWeights.clear();
		
		for(InputWeight weight: weights) {
			inputWeights.add(weight.duplicate());
		}
	}

	@Override
	public double calculatedOutput() {
		if(!isCalculated) {
			calculateValue();
		}
		
		return calculatedValue;
	}
	
	public void reset() {
		isCalculated = false;
		calculatedValue = 0;
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
		inputs.add(input);
		if(inputWeights.size() < inputs.size()) {
			inputWeights.add(InputWeight.create(0));
		}
	}
	
	
	public void removeInput(NeuronInput input) {
		inputs.remove(input);
	}

	private void calculateValue() {
		calculatedValue = activator(calculateInputs());
		isCalculated = true;
	}
	
	protected double activator(double value) {
		return ActivationFunction.logSigmoid(value, BETA);
		//return ActivationFunction.sin(value);
	}
	
	private double calculateInputs() {
		double result = 0;
		int inputSize = inputs.size();
		
		for(int i=0; i<inputSize; i++) {
			NeuronInput input = inputs.get(i);
			InputWeight weight = inputWeights.get(i);
			Double inputValue = input.calculatedOutput();
			
			result += inputValue * weight.getValue();
			/*
			double absInputValue = Math.abs(inputValue);
			
			if(absInputValue > 0.8) {
				weight.increase();
			} else if(absInputValue < 0.2) {
				weight.decrease();
			}
			*/
		}
		
		return result;
	}

	public void copyWeightsFrom(Neuron neuronFrom) {
		int count = neuronFrom.inputWeights.size();

		if(count == 0) {
			return;
		}
		
		this.inputWeights.clear();
		
		for(int ii=0; ii<count; ii++) {
			InputWeight weightFrom = neuronFrom.inputWeights.get(ii);
			this.inputWeights.add(weightFrom.duplicate());
		}
	}

	public boolean hasEqualTopology(Neuron otherNeuron) {
		return this.inputs.size() == otherNeuron.inputs.size();
	}

	public boolean hasEqualWeights(Neuron otherNeuron) {
		int count = inputWeights.size();
		
		for(int ii=0; ii<count; ii++) {
			InputWeight weight = this.inputWeights.get(ii);
			InputWeight otherWeight = otherNeuron.inputWeights.get(ii);

			boolean eq = weight.getValue() == otherWeight.getValue();
			
			if(!eq) {
				return false;
			}
		}
		
		return true;
	}

	public void randomizeWeights() {
		int count = inputs.size();
		randomizeWeightsUpTo(count);
	}
	
	public void randomizeWeightsUpTo(int count) {
		inputWeights.clear();
		
		for(int ii=0; ii<count; ii++) {
			inputWeights.add(InputWeight.random());
		}
		
	}

	public void cleanInputsKeepWeights() {
		inputs.clear();
	}

	public void setWeight(int position, InputWeight weight) {
		this.inputWeights.set(position, weight);
	}
	
	public InputWeight getWeight(int position) {
		return this.inputWeights.get(position);
	}
	
	public int getWeightsCount() {
		return this.inputWeights.size();
	}

	public void addWeight(double value) {
		this.inputWeights.add(InputWeight.create(value));
		
	}

	public void resetWeightsUpTo(int count) {
		inputWeights.clear();
		
		for(int ii=0; ii<count; ii++) {
			inputWeights.add(InputWeight.create(0.0));
		}
	}

	
	
}
