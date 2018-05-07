package main;

import java.util.Iterator;

public abstract class CrossoverOperator {
	public abstract Iterator<Individual> crossover(Individual ind1, Individual ind2);
}
