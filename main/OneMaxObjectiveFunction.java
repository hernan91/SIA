package main;

public class OneMaxObjectiveFunction extends ObjectiveFunction {

	@Override
	public double obtainFitness(Individual individual) {
		int[] allele = individual.getAllele();
		int sum = 0;
		for(int i=0; i<allele.length; i++) {
			sum += allele[i];
		}
		return (100/allele.length)*sum;
	}
	
}
