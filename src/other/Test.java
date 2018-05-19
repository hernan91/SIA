package other;

import java.util.ArrayList;

import generics.Individual;
import generics.Population;
import operators.OnePointCrossoverOperator;

public class Test {
	public static void main(String args[]) {
		ArrayList<Individual> individuals = new ArrayList<Individual>();
		int[] allele1 = {0,0,0,0,0,0,0,0,0,0};
		int[] allele2 = {1,1,1,1,1,1,1,1,1,1};
		individuals.add(new Individual(allele1));
		individuals.add(new Individual(allele2));
		OnePointCrossoverOperator operator = new OnePointCrossoverOperator();
		Population pop = new Population(operator.operate(individuals));
		pop.printIndividualsAllels();
	}
}
