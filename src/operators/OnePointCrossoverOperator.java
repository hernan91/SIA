package operators;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;

public class OnePointCrossoverOperator extends Operator{

	@Override
	public ArrayList<Individual> operate(ArrayList<Individual> population) {
		Random rand = new Random();
		ArrayList<Individual> offspringIndividuals = new ArrayList<Individual>();
		Individual ind1 = population.get(0);
		Individual ind2 = population.get(1);
		int alleleLength = ind1.getAllele().length;
		int cutPoint = rand.nextInt(alleleLength-1)+1;
		for(int i=0; i<cutPoint; i++) {
			int k = ind1.getAllele()[i];
			ind1.getAllele()[i] = ind2.getAllele()[i];
			ind2.getAllele()[i] = k;
		}
		offspringIndividuals.add(ind1);
		offspringIndividuals.add(ind2);
		return offspringIndividuals;
	}

}
