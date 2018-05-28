package generics;

public abstract class ObjectiveFunction{
	
	public abstract double getFitness(BinaryRepresentationIndividual individual);
	
	public abstract double getPopulationFitnessMean(Population<BinaryRepresentationIndividual> population);
	
	public abstract double getPopulationFitnessStandardDeviation(Population<BinaryRepresentationIndividual> population);

	public abstract BinaryRepresentationIndividual getPopulationBestFitIndividual(Population<BinaryRepresentationIndividual> population);
	
	public abstract BinaryRepresentationIndividual getPopulationWorstFitIndividual(Population<BinaryRepresentationIndividual> population);

	public abstract void printPopulationStatisticInfo(Population<BinaryRepresentationIndividual> population, double optimalScore);

	public abstract void sortPopulationByFitness(Population<BinaryRepresentationIndividual> population);
}
