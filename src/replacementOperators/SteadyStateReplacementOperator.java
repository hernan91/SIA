package replacementOperators;

import java.util.ArrayList;

import individuals.Individual;
import objectiveFunctions.ObjectiveFunction;
import operatorsModels.ReplacementOperator;
import others.Population;
import problemData.ProblemData;

public class SteadyStateReplacementOperator extends ReplacementOperator{
	public SteadyStateReplacementOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public Population operate(Population oldGeneration, Population newGeneration) {
		ArrayList<Individual> offSpring = new ArrayList<Individual>();
		ObjectiveFunction objFunc = getProblemData().getObjFunc();
		objFunc.sortPopulationByFitness(oldGeneration.getIndividuals());
		objFunc.sortPopulationByFitness(newGeneration.getIndividuals());
		for(int i=0; i<oldGeneration.getNumberOfIndividuals()-1; i++) {
			offSpring.add(oldGeneration.getIndividuals().get(i));
		}
		offSpring.add(newGeneration.getIndividuals().get(0));
		return new Population(offSpring, getProblemData());
	}
	
}
