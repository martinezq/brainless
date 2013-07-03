package org.mysoft.brainless.genetics.core;


public class GeneticOperator {

	private static final double MUTATION_PROB = 0.01;
	
	public static byte[] mutate(byte[] chromosome, boolean keep) {
		int len = chromosome.length;
		
		byte out[];
		
		if(keep)
			out = chromosome;
		else
			out = new byte[len];
		
		double prob = MUTATION_PROB;
		
		for(int i = 0; i < len; i++) {
			try {
				if(Math.random() < prob) {
					int sign = (int)(Math.round(Math.random()) * 2 - 1);
					out[i] = (byte)(chromosome[i] + sign);
				} else {
					out[i] = chromosome[i];
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				out[i] = 0;
			}
		}
		
		return out;
	}
	
	public static byte[] crossover(byte[] chromosome1, byte[] chromosome2) {
		int len = chromosome1.length;
		byte[] out = new byte[len];
		
		for(int i = 0; i < len; i+=1) {
			try {
				if(Math.random() < 0.5) {
					out[i] = chromosome1[i];
				} else {
					out[i] = chromosome2[i];
				}
			} catch (Exception e) {
				
			}
		}
		
		return out;		
	}
	
}
