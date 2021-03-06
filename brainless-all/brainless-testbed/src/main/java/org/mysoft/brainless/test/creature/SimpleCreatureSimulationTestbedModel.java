package org.mysoft.brainless.test.creature;

import org.mysoft.brainless.creature.SimpleCreatureNeuralNetworkGeneration;
import org.mysoft.brainless.creature.SimpleCreatureSimulation;
import org.mysoft.brainless.creature.SimpleCreatureWalkingFitCalculator;
import org.mysoft.brainless.genetics.chromosome.NeuralNetworkChromosome;
import org.mysoft.brainless.genetics.core.GeneticEngine;
import org.mysoft.brainless.genetics.core.GeneticParameters;
import org.mysoft.brainless.genetics.neural.DefaultNeuralNetworkMutationOperator;
import org.mysoft.brainless.genetics.neural.WeightLevelNeuralNetworkCrossoverOperator;
import org.mysoft.brainless.neural.core.NeuralNetwork;
import org.mysoft.brainless.neural.util.NeuralNetworkSerializer;
import org.mysoft.brainless.test.CharacterSimulationTest;
import org.mysoft.brainless.test.CharacterSimulationTestbedModel;

public class SimpleCreatureSimulationTestbedModel extends CharacterSimulationTestbedModel<CharacterSimulationTest<?>, SimpleCreatureSimulation> {

	public SimpleCreatureSimulationTestbedModel() {
		super();
		init();
	}

	@Override
	protected SimpleCreatureSimulation createSimulation() {
		return SimpleCreatureSimulation.create(learn());
	}
	
	@Override
	protected CharacterSimulationTest<?> createTest() {
		return new CharacterSimulationTest<SimpleCreatureSimulation>(simulation);
	}

	private NeuralNetwork learn() {

		GeneticParameters<NeuralNetworkChromosome> params = new GeneticParameters<NeuralNetworkChromosome>();
		params.setGenerationSize(32);
		params.setMaxGenerations(32);
		params.setMutationProbability(0.05);
		params.setCrossoverDisabled(false);
		params.setBestImmortal(false);
		params.setCrossoverOperator(new WeightLevelNeuralNetworkCrossoverOperator());
		params.setMutationOperator(new DefaultNeuralNetworkMutationOperator());
		params.setFitCalculator(new SimpleCreatureWalkingFitCalculator());
		params.setFitCalculationRepeats(1);
		params.setMaxGenerationsWithNoProgress(16);

		GeneticEngine<NeuralNetworkChromosome> engine = new GeneticEngine<>(params);

		engine.start(SimpleCreatureNeuralNetworkGeneration.createArrayOf(1));

		//NeuralNetworkChromosome best = (new SimpleCreatureNeuralNetworkGeneration()).instantiate(params); 
		NeuralNetworkChromosome best = engine.getBest();

		// System.out.println("Best fit = " + best.getFit());

		long ts = System.currentTimeMillis();
		String filePath = "../../../../../best_" + ts + ".nn";

		NeuralNetworkSerializer.serializeToFile(best.getNeuralNetwork(), filePath);

		return best.getNeuralNetwork();
	}

}
