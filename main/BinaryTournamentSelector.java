package main;

import java.util.ArrayList;
import java.util.Random;

//Selection strategy
public class BinaryTournamentSelector{
	private ObjectiveFunction objFunction;
	
	public BinaryTournamentSelector(ObjectiveFunction objFunction) {
		this.objFunction = objFunction;
	}
	
	private Individual doTournamentRound(Population population) {
		ArrayList<Individual> individuals =  population.getIndividuals();
		Random rand = new Random();
		int randNum1 = rand.nextInt(population.getNumberOfIndividuals()-1);
		int randNum2 = rand.nextInt(population.getNumberOfIndividuals()-1);
		Individual individual1 = individuals.get(randNum1).clone();
		Individual individual2 = individuals.get(randNum2).clone();
		if(individual1.compareTo(individual2, objFunction)==1) return individual1;
		return individual2;
	}
	
	public Population selectIndividuals(Population population) {
		ArrayList<Individual> selectedIndividuals =  new ArrayList<Individual>();
		for(int i=0; i<population.getNumberOfIndividuals(); i++) {
			selectedIndividuals.add(doTournamentRound(population));
		}
		return new Population(selectedIndividuals, objFunction);
	}
}
