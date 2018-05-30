package operators;

import java.util.ArrayList;
import java.util.Random;

import individuals.BinaryRepresentationIndividual;
import individuals.Individual;
import problemData.ProblemData;

public class TwoPointCrossoverOperator extends Operator{
	
	public TwoPointCrossoverOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public ArrayList<Individual> operate(ArrayList<Individual> individuals) {
		Random rand = new Random();
		BinaryRepresentationIndividual ind1 = (BinaryRepresentationIndividual) individuals.get(0);
		BinaryRepresentationIndividual ind2 = (BinaryRepresentationIndividual) individuals.get(1);
		int alleleLength = ind1.getAllele().length;
		int cutPoint1 = rand.nextInt(alleleLength-2)+1;
		int cutPoint2 = new Random().nextInt(alleleLength-cutPoint1-1)+cutPoint1;
		for(int i=cutPoint1; i<=cutPoint2; i++) {
			int k = ind1.getAllele()[i];
			ind1.getAllele()[i] = ind2.getAllele()[i];
			ind2.getAllele()[i] = k;
		}
		return individuals;
	}
}
