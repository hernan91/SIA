package operators;

import java.util.ArrayList;
import generics.Individual;
import generics.ObjectiveFunction;
import generics.Population;
import generics.ProblemData;

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
