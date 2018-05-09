package strategies;

import java.util.ArrayList;
import java.util.Iterator;

import generics.Individual;
import generics.Population;
import operators.MutationOperator;
import operators.TwoPointsCrossover;

public class ReproductionStrategy {
	private float crossoverProbability; 
	private float mutationProbability;
	
	public ReproductionStrategy(float crossoverProbability, float mutationProbability) {
		super();
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = mutationProbability;
	}

	public Population crossover2pAndBitFlipMutation(Population population){
		ArrayList<Individual> offSprings = new ArrayList<Individual>();
		TwoPointsCrossover twoPCross = new TwoPointsCrossover(crossoverProbability);
		MutationOperator mutOperator = new MutationOperator(mutationProbability);
		Iterator<Individual> it = population.clone().getIndividuals().iterator();
		while(it.hasNext()) {
			Individual ind1 = it.next();
			Individual ind2 = it.next();
			Iterator<Individual> offSpring = twoPCross.crossover(ind1, ind2);
			offSprings.add( mutOperator.operate(offSpring.next()) );
			offSprings.add( mutOperator.operate(offSpring.next()) );
		}
		return new Population(offSprings);
	}
}
