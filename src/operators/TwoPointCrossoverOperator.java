package operators;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;

public class TwoPointCrossoverOperator extends Operator{
	
	@Override
	public ArrayList<Individual> operate(ArrayList<Individual> population) {
		Random rand = new Random();
		ArrayList<Individual> offspringIndividuals = new ArrayList<Individual>();
		Individual ind1 = population.get(0);
		Individual ind2 = population.get(1);
		int alleleLength = ind1.getAllele().length;
		int cutPoint1 = rand.nextInt(alleleLength-2)+1;
		int cutPoint2 = new Random().nextInt(alleleLength-cutPoint1-1)+cutPoint1;
		for(int i=cutPoint1; i<=cutPoint2; i++) {
			int k = ind1.getAllele()[i];
			ind1.getAllele()[i] = ind2.getAllele()[i];
			ind2.getAllele()[i] = k;
		}
		offspringIndividuals.add(ind1);
		offspringIndividuals.add(ind2);
		return offspringIndividuals;
	}
}
