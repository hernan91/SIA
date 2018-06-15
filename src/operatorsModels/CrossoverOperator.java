package operatorsModels;

import java.util.ArrayList;
import java.util.Random;

import individuals.Individual;
import others.Population;
import problemData.ProblemData;;

public abstract class CrossoverOperator extends Operator{
	float crossoverProbability;
	
	public CrossoverOperator(ProblemData problemData, float crossoverProbability) {
		super(problemData);
		this.crossoverProbability = crossoverProbability;
	}

	public Population operate(Population population) {
		ArrayList<Individual> offSprings = new ArrayList<>();
		Random rand = new Random();
		while(population.getNumberOfIndividuals()>0) {
			Individual individual1 = population.getIndividuals().remove(0);
			Individual individual2 = population.getIndividuals().remove(0);
			float p = rand.nextFloat();
			if(p<=crossoverProbability) 
				cross(individual1, individual2);
			offSprings.add(individual1);
			offSprings.add(individual2);
		}
		return new Population(offSprings, getProblemData());
	}
	
	protected abstract void cross(Individual individual1, Individual individual2);
	
	public float getCrossoverProbability() {
		return crossoverProbability;
	}

	public void setCrossoverProbability(float crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}
}
