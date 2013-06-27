package org.mysoft.neural.sample;

import org.mysoft.brainless.genetic.core.Generation;
import org.mysoft.brainless.genetic.core.GeneticParameters;

public class EvolvableTextGeneration extends Generation<EvolvableText> {

	@Override
	protected EvolvableText instantiate(GeneticParameters parameters) {
		EvolvableText i =  new EvolvableText("Ala ma kota w worku na plecach");
		i.setParameters(parameters);
		return i;
	}

}
