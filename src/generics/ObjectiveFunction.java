package generics;

public abstract class ObjectiveFunction <T extends BinaryRepresentationIndividual>{
	
	public abstract double getFitness(T individual);
	
	public abstract double getPopulationFitnessMean(Population<T> population);
	
	public abstract double getPopulationFitnessStandardDeviation(Population<T> population);

	public abstract T getPopulationBestFitIndividual(Population<T> population);
	
	public abstract T getPopulationWorstFitIndividual(Population<T> population);

	public abstract void printPopulationStatisticInfo(Population<T> population, double optimalScore);

	public abstract void sortPopulationByFitness(Population<T> population);
}
