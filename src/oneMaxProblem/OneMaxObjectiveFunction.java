package oneMaxProblem;

import generics.BinaryRepresentationIndividual;
import generics.ObjectiveFunction;

public class OneMaxObjectiveFunction extends ObjectiveFunction {

	@Override
	public double getFitness(BinaryRepresentationIndividual individual) {
		int[] allele = individual.getAllele();
		int sum = 0;
		for(int i=0; i<allele.length; i++) {
			sum += allele[i];
		}
		return (100f/allele.length)*sum;
	}
	
}
