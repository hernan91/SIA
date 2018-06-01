package operators;

import java.util.ArrayList;

import individuals.Individual;
import objectiveFunctions.ObjectiveFunction;
import operatorsModels.Operator;
import others.Population;
import problemData.ProblemData;

public class SimpleReplacementOperator extends Operator {

	public SimpleReplacementOperator(ProblemData problemData) {
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
