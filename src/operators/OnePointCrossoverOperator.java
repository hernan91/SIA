package operators;

import java.util.ArrayList;
import java.util.Random;

import individuals.BinaryRepresentationIndividual;
import individuals.Individual;
import operatorsModels.Operator;
import problemData.ProblemData;

public class OnePointCrossoverOperator extends Operator{

	public OnePointCrossoverOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public ArrayList<Individual> operate(ArrayList<Individual> individuals) {
		Random rand = new Random();
		ArrayList<Individual> offspringIndividuals = new ArrayList<>();
		BinaryRepresentationIndividual ind1 = (BinaryRepresentationIndividual) individuals.get(0);
		BinaryRepresentationIndividual ind2 = (BinaryRepresentationIndividual) individuals.get(1);
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
