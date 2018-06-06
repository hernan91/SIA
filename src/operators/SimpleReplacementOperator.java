package operators;

import operatorsModels.ReplacementOperator;
import others.Population;
import problemData.ProblemData;

public class SimpleReplacementOperator extends ReplacementOperator{

	public SimpleReplacementOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public Population operate(Population oldGeneration, Population newGeneration) {
		Population replacement = new Population(oldGeneration.getIndividuals(), getProblemData());
		replacement.getIndividuals().addAll(newGeneration.getIndividuals());
		getProblemData().getObjFunc().sortPopulationByFitness(replacement.getIndividuals());
		int removeCount = replacement.getNumberOfIndividuals()/2;
		replacement.removeLastNIndividualsFromPopulation(removeCount);
		return replacement;
	}
}
