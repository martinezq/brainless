package org.mysoft.test;

import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;
import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.GeneticEngine;
import org.mysoft.brainless.genetics.core.GeneticParameters;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkCrossoverOperator;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkMutationOperator;
import org.mysoft.brainless.human.HumanCharacter;
import org.mysoft.brainless.human.HumanNeuralNetworkGeneration;
import org.mysoft.brainless.human.HumanStandingFitCalculator;
import org.mysoft.brainless.human.HumanSimulation;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.util.NeuralNetworkSerializer;

public class HumanCharacterTest extends TestbedTest {

	static NeuralNetwork best = null;

	HumanCharacter character;
	HumanSimulation simulation = HumanSimulation.create();

	int iterationsLeft = HumanSimulation.ITERATIONS;

	@Override
	public void initTest(boolean argDeserialized) {

		setTitle(getTestName());

		if (best == null) {
			best = learn();
		}

		simulation.initWorld(getWorld());
		character = simulation.initActor(getWorld(), best);

	}

	@Override
	public String getTestName() {
		return "Human Character Test";
	}

	private NeuralNetwork learn() {

		GeneticParameters<NeuralNetworkChromosome> params = new GeneticParameters<NeuralNetworkChromosome>();
		params.setGenerationSize(128);
		params.setMaxGenerations(64);
		params.setMutationProbability(0.01);
		params.setBestImmortal(false);
		params.setCrossoverOperator(new DefaultNeuralNetworkCrossoverOperator());
		params.setMutationOperator(new DefaultNeuralNetworkMutationOperator());
		params.setFitCalculator(new HumanStandingFitCalculator());

		GeneticEngine<NeuralNetworkChromosome> engine = new GeneticEngine<>(params);

		engine.start(HumanNeuralNetworkGeneration.createArrayOf(2));

		NeuralNetworkChromosome best = engine.getBest();

		// System.out.println("Best fit = " + best.getFit());

		long ts = System.currentTimeMillis();
		String filePath = "../../../../../best_" + ts + ".nn";

		NeuralNetworkSerializer.serializeToFile(best.getNeuralNetwork(), filePath);

		return best.getNeuralNetwork();
	}

	@Override
	public synchronized void step(TestbedSettings settings) {
		if (iterationsLeft-- == 0) {
			settings.pause = true;
		}

		super.step(settings);
		character.getBrain().step();

		double posx = character.getBody().getMasterBone().getPhysicalBody().getPosition().x;
		double posy = character.getBody().getMasterBone().getPhysicalBody().getPosition().y;

		// System.out.println(posx + ", " + posy);

	}

	@Override
	public void reset() {
		iterationsLeft = HumanSimulation.ITERATIONS;
		super.getModel().getSettings().pause = false;
		super.reset();
	}

}
