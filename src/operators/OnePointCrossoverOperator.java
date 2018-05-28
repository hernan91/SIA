package operators;

import java.util.ArrayList;
import java.util.Random;

import generics.BinaryRepresentationIndividual;

public class OnePointCrossoverOperator extends Operator{

	@Override
	public ArrayList<BinaryRepresentationIndividual> operate(ArrayList<BinaryRepresentationIndividual> individuals) {
		Random rand = new Random();
		ArrayList<BinaryRepresentationIndividual> offspringIndividuals = new ArrayList<BinaryRepresentationIndividual>();
		BinaryRepresentationIndividual ind1 = individuals.get(0);
		BinaryRepresentationIndividual ind2 = individuals.get(1);
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
