package selectionOperators;

import java.util.ArrayList;
import java.util.Random;

import individuals.Individual;
import operatorsModels.SelectionOperator;
import others.Population;
import problemData.ProblemData;

public class RouletteWheelSelectionOperator extends SelectionOperator {

	public RouletteWheelSelectionOperator(ProblemData problemData) {
		super(problemData);
	}

	private ArrayList<Float> generateRoulette(ArrayList<Individual> oldGen) {
		float fitSum = 0;
		ArrayList<Float> orderedByFitArray = new ArrayList<>();
		for(Individual ind : oldGen) {
			fitSum += ind.getFitness();
			orderedByFitArray.add(fitSum);
		}
		return orderedByFitArray;
	}
	
	private int throwTheBall(ArrayList<Float> orderedByFitArray) {
		Random rand = new Random();
		float fitSum = orderedByFitArray.get(orderedByFitArray.size()-1);
		float positionOfRoulette = rand.nextFloat()*fitSum;
		float currentFit = 0;
		for(int i=0; i<orderedByFitArray.size(); i++) {
			currentFit += orderedByFitArray.get(i);
			if(currentFit >= positionOfRoulette) return i; 
		}
		return -1;
	}
	
	@Override
	public Population operate(Population population) {
		ArrayList<Individual> selectedIndividuals = new ArrayList<>();
		ArrayList<Float> roulette = generateRoulette(population.getIndividuals());
		for(int i=0; i<population.getNumberOfIndividuals(); i++) {
			int positionOfRoulette = throwTheBall(roulette);
			selectedIndividuals.add(population.getIndividuals().get(positionOfRoulette));
		}
		return new Population(selectedIndividuals, this.getProblemData());
	}

	@Override
	public Individual select(ArrayList<Individual> individuals) {
		return null;
	}
}
