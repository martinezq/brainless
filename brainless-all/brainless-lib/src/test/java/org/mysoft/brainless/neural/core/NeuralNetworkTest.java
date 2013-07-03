package org.mysoft.brainless.neural.core;

import org.junit.Assert;
import org.junit.Test;

public class NeuralNetworkTest {

	@Test
	public void create_default() {
		NeuralNetwork.createDefault();
	}
	
	@Test
	public void create_two_equal_and_compare() {
		NeuralNetwork n1 = NeuralNetwork.createDefault();
		NeuralNetwork n2 = NeuralNetwork.createDefault();
		
		Assert.assertTrue("Not equal topology", n1.hasEqualTopology(n2));
		Assert.assertTrue("Not equal topology", n2.hasEqualTopology(n1));
		
		Assert.assertTrue("Not equal weights", n1.hasEqualWeights(n2));
		Assert.assertTrue("Not equal weights", n2.hasEqualWeights(n1));
	}
	
	@Test
	public void create_two_randomize_and_compare() {
		NeuralNetwork n1 = NeuralNetwork.createDefault();
		NeuralNetwork n2 = NeuralNetwork.createDefault();
		
		n1.randomizeWeights();
		n2.randomizeWeights();
		
		Assert.assertTrue("Not equal topology", n1.hasEqualTopology(n2));
		Assert.assertTrue("Not equal topology", n2.hasEqualTopology(n1));
		
		Assert.assertFalse("Equal weights", n1.hasEqualWeights(n2));
		Assert.assertFalse("Equal weights", n2.hasEqualWeights(n1));
	}
	
	@Test
	public void create_and_duplicate() {
		NeuralNetwork n = NeuralNetwork.create(1,2,3,4);
		NeuralNetwork d = n.duplicate();
		
		Assert.assertTrue("Not equal topology", d.hasEqualTopology(n));
		Assert.assertTrue("Not equal weights", d.hasEqualWeights(n));
		
		Assert.assertTrue("Not equal topology", n.hasEqualTopology(d));
		Assert.assertTrue("Not equal weights", n.hasEqualWeights(d));
	}
	
	@Test
	public void attach_detach_input_layer() {
		NeuralNetwork n1 = NeuralNetwork.createDefault();
		n1.randomizeWeights();
		NeuralNetwork n2 = n1.duplicate();
		
		InputLayer inputLayer = InputLayer.create();
		
		inputLayer.add(Neuron.create());
		
		n2.attachInputLayer(inputLayer);
				
		Assert.assertFalse("Equal topology", n1.hasEqualTopology(n2));
		
		n2.detachInputLayer();
		
		Assert.assertTrue("Not equal topology", n1.hasEqualTopology(n2));
		Assert.assertTrue("Not equal weights", n1.hasEqualWeights(n2));		
		
	}
	
	@Test
	public void attach_detach_output_layer() {
		NeuralNetwork n1 = NeuralNetwork.createDefault();
		n1.randomizeWeights();
		NeuralNetwork n2 = n1.duplicate();
		
		OutputLayer outputLayer = OutputLayer.create();
		
		for(int i=0; i<n1.hiddenLayers.getLast().size(); i++) {
			outputLayer.add(new NetworkOutput() {
				@Override public void perform(double value) {}
			});
		}
		
		n1.attachOutputLayer(outputLayer);
				
		Assert.assertTrue("Not equal topology", n1.hasEqualTopology(n2));
		Assert.assertTrue("Not equal weights", n1.hasEqualWeights(n2));
		
		n2.detachOutputLayer();
		
		Assert.assertTrue("Not equal topology", n1.hasEqualTopology(n2));
		Assert.assertTrue("Not equal weights", n1.hasEqualWeights(n2));		
		
	}	
	
}
