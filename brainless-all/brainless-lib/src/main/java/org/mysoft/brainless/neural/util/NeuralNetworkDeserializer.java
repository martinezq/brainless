package org.mysoft.brainless.neural.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.Neuron;
import org.mysoft.brainless.neural.core.NeuronLayer;

public class NeuralNetworkDeserializer {

	private enum State {
		LAYERS_COUNT,
		NEURONS_COUNT,
		WEIGHTS_COUNT,
		WEIGHT
	}
	
	public static NeuralNetwork deserialize(String serializedNeuralNetwork) {
		State state = State.LAYERS_COUNT;
		
		String[] parts = serializedNeuralNetwork.split(",");
		
		NeuralNetwork n = NeuralNetwork.createEmpty();
		
		int layersCount = 0;
		NeuronLayer layer = null;
		Neuron neuron = null;
		int weightsLeft = 0;
		int neuronsLeft = 0;
		int layersLeft = 0;
		int neuronsCount = 0;
		
		for(int i=0; i<parts.length; i++) {
			String part = parts[i];
			
			//System.out.println(i + " (" + state.toString() + "): " + part);
			//System.out.println(layersLeft + ", " + neuronsLeft + ", " + weightsLeft);
			
			switch (state) {
				case LAYERS_COUNT:
					layersCount = Integer.parseInt(part);
					layersLeft = layersCount - 1;
					state = State.NEURONS_COUNT;
					break;

				case NEURONS_COUNT:
					neuronsCount = Integer.parseInt(part);
					layer = NeuronLayer.create(neuronsCount);
					neuronsLeft = neuronsCount;
					n.getHiddenLayers().add(layer);
					state=State.WEIGHTS_COUNT;
					break;
				
				case WEIGHTS_COUNT:
					weightsLeft = Integer.parseInt(part);
					neuron = layer.get(neuronsCount - neuronsLeft);
					if(weightsLeft > 0) {
						state=State.WEIGHT;
					} else {
						neuronsLeft--;
					}
					break;
					
				case WEIGHT:
					neuron.addWeight(Double.parseDouble(part));
					weightsLeft--;
					
					if(weightsLeft == 0) {
						neuronsLeft--;
						state=State.WEIGHTS_COUNT;
					
						if(neuronsLeft == 0) {
							layersLeft--;
							state = State.NEURONS_COUNT;
						}
							
					}
					break;
					
				default:
					break;
				}
		}
		
		n.createHiddenLayersConnections();
		
		return n;
	}
	
	public static NeuralNetwork deserializeFromFile(String filePath) {
		try {
			StringBuilder sb = new StringBuilder();
			
			FileReader reader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
			
			bufferedReader.close();
			reader.close();
			
			return deserialize(sb.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
