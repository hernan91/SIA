package operators;

import java.util.ArrayList;

import generics.Individual;
import generics.ObjectiveFunction;
import generics.Population;

public class ReplacementOperator extends Operator {
	private ObjectiveFunction objFunc;
	private int populationSize;
	
	public ReplacementOperator(ObjectiveFunction objFunc, int populationSize) {
		super();
		this.objFunc = objFunc;
		this.populationSize = populationSize;
	}

	@Override
	public ArrayList<Individual> operate(ArrayList<Individual> pop) {
		Population population = new Population(pop);
		population.sortByFitness(objFunc);
		population.removeLastNIndividuals(populationSize);
		return population.getIndividuals();
	}

}
