package org.mysoft.brainless.brain;

import org.mysoft.brainless.body.BoneJoint;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.core.NeuronLayer;
import org.mysoft.brainless.sensor.BodySensor;

public class HumanBrain extends Brain {

	NeuralNetwork network = new NeuralNetwork();
	
	@Override
	public void activate() {
		init();
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
	
	private void init() {
		for(BodySensor sensor: body.getBodySensors()) {
			network.addInput(sensor);
		}
		
		BoneJoint[] joints =  body.getBodyJoints();
		
		network.addHiddenLayer(new NeuronLayer(5));
		network.addHiddenLayer(new NeuronLayer(joints.length));
		
		for(BoneJoint joint: joints) {
			network.addOutput(joint);
		}
	}

}
