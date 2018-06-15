package operatorsModels;

import java.util.ArrayList;

import individuals.Individual;
import others.Population;
import problemData.ProblemData;

public abstract class SelectionOperator extends Operator{
	public SelectionOperator(ProblemData problemData) {
		super(problemData);
	}

	public Population operate(Population population) {
		ArrayList<Individual> individuals = new ArrayList<>();
		for(int i=0; i<population.getNumberOfIndividuals(); i++) {
			individuals.add(select(population.getIndividuals()));
		}
		return new Population(individuals, this.getProblemData());
	}
	
	public abstract Individual select(ArrayList<Individual> individuals);
}
