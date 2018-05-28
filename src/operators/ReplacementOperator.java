package operators;

import java.util.ArrayList;

import generics.BinaryRepresentationIndividual;
import generics.ObjectiveFunction;
import generics.Population;

public class ReplacementOperator extends Operator {
	private ObjectiveFunction objFunc;
	
	public ReplacementOperator(ObjectiveFunction objFunc) {
		super();
		this.objFunc = objFunc;
	}

	@Override
	public ArrayList<BinaryRepresentationIndividual> operate(ArrayList<BinaryRepresentationIndividual> pop) {
		Population<BinaryRepresentationIndividual> population = new Population<>(pop);
		objFunc.sortPopulationByFitness(population);
		int removeCount = population.getNumberOfIndividuals()/2;
		population.removeLastNIndividualsFromPopulation(population, removeCount);
		return population.getIndividuals();
	}

}
