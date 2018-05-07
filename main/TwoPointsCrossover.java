package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TwoPointsCrossover extends CrossoverOperator{
	private float crossoverProbability;
	
	public TwoPointsCrossover(float crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}
	@Override
	public Iterator<Individual> crossover(Individual ind1, Individual ind2) {
		ind1 = ind1.clone();
		ind2 = ind2.clone();
		Random rand = new Random();
		float cp = rand.nextFloat();
		ArrayList<Individual> offspringIndividuals = new ArrayList<Individual>();
		if(cp<=crossoverProbability) {
			int alleleLength = ind1.getAllele().length;
			int cutPoint1 = rand.nextInt(alleleLength-2)+1;
			int cutPoint2 = new Random().nextInt(alleleLength-cutPoint1-1)+cutPoint1;
			for(int i=cutPoint1; i<=cutPoint2; i++) {
				ind1.getAllele()[i] = ind2.getAllele()[i];
				ind2.getAllele()[i] = ind1.getAllele()[i];
			}
		}
		offspringIndividuals.add(ind1);
		offspringIndividuals.add(ind2);
		return offspringIndividuals.iterator();
	}

}
