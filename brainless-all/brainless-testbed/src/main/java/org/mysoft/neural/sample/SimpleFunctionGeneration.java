package org.mysoft.neural.sample;

import org.mysoft.brainless.genetics.core.Generation;
import org.mysoft.brainless.genetics.core.GeneticParameters;

public class SimpleFunctionGeneration extends Generation<SimpleFunction>{

	@Override
	protected SimpleFunction instantiate(GeneticParameters parameters) {
		SimpleFunction sf = new SimpleFunction();
		sf.setParameters(parameters);
		return sf;
	}

}
