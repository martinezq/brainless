package org.mysoft.neural.sample;

import org.mysoft.brainless.genetics.core.Generation;
import org.mysoft.brainless.genetics.core.GeneticParameters;

public class EvolvableTextGeneration extends Generation<EvolvableText> {

	@Override
	protected EvolvableText instantiate(GeneticParameters parameters) {
		EvolvableText i =  new EvolvableText("Ala ma kota w worku na plecach");
		i.setParameters(parameters);
		return i;
	}

}
