package org.mysoft.brainless.neural.core;

import java.util.LinkedList;

public class InputLayer extends LinkedList<NeuronInput>{

	private static final long serialVersionUID = 1L;

	public static InputLayer create() {
		return new InputLayer();
	}

}
