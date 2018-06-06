package operators;

import java.util.Random;

import individuals.BinaryRepresentationIndividual;
import individuals.Individual;
import operatorsModels.CrossoverOperator;
import problemData.ProblemData;

public class OnePointCrossoverOperator extends CrossoverOperator{

	public OnePointCrossoverOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public void operate(Individual individual1, Individual individual2) {
		Random rand = new Random();
		BinaryRepresentationIndividual ind1 = (BinaryRepresentationIndividual) individual1;
		BinaryRepresentationIndividual ind2 = (BinaryRepresentationIndividual) individual2;
		int alleleLength = ind1.getAllele().length;
		int cutPoint = rand.nextInt(alleleLength-1)+1;
		for(int i=0; i<cutPoint; i++) {
			int k = ind1.getAllele()[i];
			ind1.getAllele()[i] = ind2.getAllele()[i];
			ind2.getAllele()[i] = k;
		}
	}

}
