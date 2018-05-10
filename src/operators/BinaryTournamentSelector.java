package operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import generics.Individual;
import generics.ObjectiveFunction;
import generics.Population;

//Selection strategy
public class BinaryTournamentSelector extends Operator{
	private ObjectiveFunction objFunction;
	
	public BinaryTournamentSelector(ObjectiveFunction objFunction) {
		this.objFunction = objFunction;
	}
	
	private Individual doTournamentRound(ArrayList<Individual> population) {
		ArrayList<Individual> individuals =  population;
		Random rand = new Random();
		int randNum1 = rand.nextInt(population.size()-1);
		int randNum2 = rand.nextInt(population.size()-1);
		Individual individual1 = individuals.get(randNum1).clone();
		Individual individual2 = individuals.get(randNum2).clone();
		if(individual1.compareTo(individual2, objFunction)==1) return individual1;
		return individual2;
	}
	
	public ArrayList<Individual> selectIndividuals(ArrayList<Individual> population) {
		ArrayList<Individual> selectedIndividuals =  new ArrayList<Individual>();
		for(int i=0; i<population.size(); i++) {
			selectedIndividuals.add(doTournamentRound(population));
		}
		return new Population(selectedIndividuals);
	}

	@Override
	public Iterator<Individual> execute(Population population) {
		selectIndividuals(Population)
		return null;
	}
}
