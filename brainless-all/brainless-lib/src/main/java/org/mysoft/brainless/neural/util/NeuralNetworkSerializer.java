package org.mysoft.brainless.neural.util;

import java.io.FileWriter;
import java.io.IOException;

import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.Neuron;
import org.mysoft.brainless.neural.core.NeuronLayer;

public class NeuralNetworkSerializer {

	public static String serialize(NeuralNetwork neuralNetwork) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(neuralNetwork.getHiddenLayers().size() + ",");
		
		for(NeuronLayer layer: neuralNetwork.getHiddenLayers()) {
			sb.append(layer.size() + ",");
			
			for(Neuron neuron: layer) {
				sb.append(neuron.getWeightsCount() + ",");
				
				for(int i=0; i<neuron.getWeightsCount(); i++) {
					sb.append(neuron.getWeight(i).getValue() + ",");
				}
			}
		}

		int length = sb.length();
		
		return sb.substring(0, length - 1);
	}

	public static void serializeToFile(NeuralNetwork neuralNetwork, String filePath) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.append(serialize(neuralNetwork));
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
