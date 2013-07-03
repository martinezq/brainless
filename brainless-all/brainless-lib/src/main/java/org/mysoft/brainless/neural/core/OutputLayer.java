package org.mysoft.brainless.neural.core;

import java.util.LinkedList;

public class OutputLayer extends LinkedList<NetworkOutput> {

	private static final long serialVersionUID = 1L;

	public static OutputLayer create() {
		return new OutputLayer();
	}

}
