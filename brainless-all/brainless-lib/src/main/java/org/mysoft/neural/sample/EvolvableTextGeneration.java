package org.mysoft.neural.sample;

import org.mysoft.genetic.core.Generation;
import org.mysoft.genetic.core.GeneticParameters;

public class EvolvableTextGeneration extends Generation<EvolvableText> {

	@Override
	protected EvolvableText instantiate(GeneticParameters parameters) {
		EvolvableText i =  new EvolvableText("Ala ma kota");
		i.setParameters(parameters);
		return i;
	}

}
