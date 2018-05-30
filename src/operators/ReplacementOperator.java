package operators;

import java.util.ArrayList;

import individuals.Individual;
import objectiveFunctions.ObjectiveFunction;
import others.Population;
import problemData.ProblemData;

public class ReplacementOperator extends Operator {

	public ReplacementOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public ArrayList<Individual> operate(ArrayList<Individual> pop) {
		ObjectiveFunction objFunc = getProblemData().getObjFunc();
		Population population = new Population(pop, getProblemData());
		objFunc.sortPopulationByFitness(population.getIndividuals());
		int removeCount = population.getNumberOfIndividuals()/2;
		population.removeLastNIndividualsFromPopulation(population, removeCount);
		return population.getIndividuals();
	}

}
