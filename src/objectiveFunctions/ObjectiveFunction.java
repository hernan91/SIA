package objectiveFunctions;

import java.util.ArrayList;
import individuals.Individual;
import others.Population;

public abstract class ObjectiveFunction{
	
	public abstract double getFitness(Individual individual);
	
	public abstract double getPopulationFitnessMean(Population population);
	
	public abstract double getPopulationFitnessStandardDeviation(Population population);

	public abstract Individual getPopulationBestFitIndividual(Population population);
	
	public abstract Individual getPopulationWorstFitIndividual(Population population);

	public abstract void printPopulationStatisticInfo(Population population, double optimalScore);

	public abstract void sortPopulationByFitness(ArrayList<Individual> population);
}
