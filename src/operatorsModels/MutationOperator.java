package operatorsModels;

import java.util.ArrayList;
import java.util.Random;

import individuals.Individual;
import others.Population;
import problemData.ProblemData;

public abstract class MutationOperator extends Operator{
	float mutationProbability;
	
	public MutationOperator(ProblemData problemData, float mutationProbability) {
		super(problemData);
		this.mutationProbability = mutationProbability;
	}

	public Population operate(Population population) {
		ArrayList<Individual> offSprings = new ArrayList<>();
		Random rand = new Random();
		while(population.getNumberOfIndividuals()>0) {
			Individual individual = population.getIndividuals().remove(0);
			float p = rand.nextFloat();
			if(p<=mutationProbability) {
				mutate(individual);
			}
			offSprings.add(individual);
		}
		return new Population(offSprings, getProblemData());
	}
	
	public abstract void mutate(Individual individual);
	
	public float getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(float mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
}
