package replacementOperators;

import operatorsModels.ReplacementOperator;
import others.Population;
import problemData.ProblemData;

public class ElitistReplacementOperator extends ReplacementOperator{

	public ElitistReplacementOperator(ProblemData problemData) {
		super(problemData);
	}


	public Population operate(Population oldGeneration, Population newGeneration) {
		Population replacement = new Population(oldGeneration.getIndividuals(), getProblemData());
		replacement.getIndividuals().addAll(newGeneration.getIndividuals());
		getProblemData().getObjFunc().sortPopulationByFitness(replacement.getIndividuals());
		int removeCount = replacement.getNumberOfIndividuals()/2;
		replacement.removeLastNIndividualsFromPopulation(removeCount);
		return replacement;
	}
}
