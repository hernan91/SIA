package operators;

import java.util.Random;

import individuals.BinaryRepresentationIndividual;
import individuals.Individual;
import operatorsModels.CrossoverOperator;
import problemData.ProblemData;

public class TwoPointCrossoverOperator extends CrossoverOperator{
	
	public TwoPointCrossoverOperator(ProblemData problemData, float crossoverProbability) {
		super(problemData, crossoverProbability);
	}

	@Override
	public void cross(Individual individual1, Individual individual2) {
		Random rand = new Random();
		BinaryRepresentationIndividual ind1 = (BinaryRepresentationIndividual) individual1;
		BinaryRepresentationIndividual ind2 = (BinaryRepresentationIndividual) individual2;
		int alleleLength = ind1.getAllele().length;
		int cutPoint1 = rand.nextInt(alleleLength-2)+1;
		int cutPoint2 = new Random().nextInt(alleleLength-cutPoint1-1)+cutPoint1;
		for(int i=cutPoint1; i<=cutPoint2; i++) {
			int k = ind1.getAllele()[i];
			ind1.getAllele()[i] = ind2.getAllele()[i];
			ind2.getAllele()[i] = k;
		}
	}
}
