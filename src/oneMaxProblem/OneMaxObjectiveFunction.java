package oneMaxProblem;

import generics.Individual;
import generics.ObjectiveFunction;

public class OneMaxObjectiveFunction extends ObjectiveFunction {

	@Override
	public double obtainFitness(Individual individual) {
		int[] allele = individual.getAllele();
		int sum = 0;
		System.out.println("Alelo = ");
		for(int i=0; i<allele.length; i++) {
			sum += allele[i];
			System.out.print(allele[i]);
		}
		System.out.println();
		return (100/allele.length)*sum;
	}
	
}
