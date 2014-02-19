package org.mysoft.brainless.test.human;

import org.jbox2d.testbed.framework.TestbedTest;
import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.GeneticEngine;
import org.mysoft.brainless.genetics.core.GeneticParameters;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkCrossoverOperator;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkMutationOperator;
import org.mysoft.brainless.genetics.neural.WeightLevelNeuralNetworkCrossoverOperator;
import org.mysoft.brainless.human.HumanNeuralNetworkGeneration;
import org.mysoft.brainless.human.HumanSimulation;
import org.mysoft.brainless.human.HumanStandingFitCalculator;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.util.NeuralNetworkSerializer;
import org.mysoft.brainless.test.CharacterSimulationTest;
import org.mysoft.brainless.test.CharacterSimulationTestbedModel;

public class HumanSimulationTestbedModel extends CharacterSimulationTestbedModel<CharacterSimulationTest<?>, HumanSimulation> {

	public HumanSimulationTestbedModel() {
		super();
		init();
	}

	@Override
	protected HumanSimulation createSimulation() {
		return HumanSimulation.create(learn());
	}
	
	@Override
	protected CharacterSimulationTest<?> createTest() {
		return new CharacterSimulationTest<HumanSimulation>(simulation);
	}

	private NeuralNetwork learn() {

		GeneticParameters<NeuralNetworkChromosome> params = new GeneticParameters<NeuralNetworkChromosome>();
		params.setGenerationSize(16);
		params.setMaxGenerations(32);
		params.setMutationProbability(0.02);
		params.setBestImmortal(false);
		params.setCrossoverOperator(new WeightLevelNeuralNetworkCrossoverOperator());
		params.setMutationOperator(new DefaultNeuralNetworkMutationOperator());
		params.setFitCalculator(new HumanStandingFitCalculator());

		GeneticEngine<NeuralNetworkChromosome> engine = new GeneticEngine<>(params);

		engine.start(HumanNeuralNetworkGeneration.createArrayOf(1));

		NeuralNetworkChromosome best = engine.getBest();

		// System.out.println("Best fit = " + best.getFit());

		long ts = System.currentTimeMillis();
		String filePath = "../../../../../best_" + ts + ".nn";

		NeuralNetworkSerializer.serializeToFile(best.getNeuralNetwork(), filePath);

		return best.getNeuralNetwork();
	}

}
