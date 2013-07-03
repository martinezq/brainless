package org.mysoft.brainless.human;

import org.mysoft.brainless.body.BoneJoint;
import org.mysoft.brainless.body.ComplexBody;
import org.mysoft.brainless.brain.Brain;
import org.mysoft.brainless.neural.core.InputLayer;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.NeuronLayer;
import org.mysoft.brainless.neural.core.OutputLayer;
import org.mysoft.brainless.sensor.BodySensor;

public class HumanBrain extends Brain {

	NeuralNetwork network;
	
	public static HumanBrain create() {
		return create(NeuralNetwork.createEmpty());
	}
	
	public static HumanBrain create(NeuralNetwork neuralNetwork) {
		HumanBrain brain = new HumanBrain();
		brain.network = neuralNetwork;
		return brain;
	}
	
	@Override
	public void attachTo(ComplexBody complexBody) {
		super.attachTo(complexBody);
		connectBrainToBody();
	}

	private void connectBrainToBody() {
		if(body == null) {
			throw new IllegalStateException("There is no body attached");
		}

		InputLayer inputLayer = InputLayer.create();
		OutputLayer outputLayer = OutputLayer.create();
		
		for(BodySensor sensor: body.getBodySensors()) {
			inputLayer.add(sensor);
		}

		BoneJoint[] joints =  body.getBodyJoints();
		
		for(BoneJoint joint: joints) {
			outputLayer.add(joint);
		}
		
		network.attachInputLayer(inputLayer);
		network.attachOutputLayer(outputLayer);
		
	}

	@Override
	public void activate() {
		freeze();
	}

	@Override
	public void update() {
		network.step();
	}

	public void freeze() {
		for (BoneJoint joint : body.getBodyJoints()) {
			joint.hold(50000f);
		}

	}

}
