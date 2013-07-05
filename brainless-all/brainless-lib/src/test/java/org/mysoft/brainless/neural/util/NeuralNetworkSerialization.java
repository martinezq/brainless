package org.mysoft.brainless.neural.util;

import org.junit.Assert;
import org.junit.Test;
import org.mysoft.brainless.neural.core.NeuralNetwork;

public class NeuralNetworkSerialization {

	private long ts = System.currentTimeMillis();
	private String filePath =  "../../../../../out_" + ts + ".nn";
	
	@Test
	public void test_serialization() {
		NeuralNetwork n = NeuralNetwork.createDefault();
		n.randomizeWeights();
		
		NeuralNetworkSerializer.serializeToFile(n, filePath);
	}
	
	@Test
	public void test_serialize_deserialize() {
		NeuralNetwork n = NeuralNetwork.create(2,2);

		n.randomizeWeights();
		
		NeuralNetworkSerializer.serializeToFile(n, filePath);
		
		NeuralNetwork d = NeuralNetworkDeserializer.deserializeFromFile(filePath);
		
		Assert.assertTrue("Not equal", d.hasEqualWeights(n));
	}
	
}
