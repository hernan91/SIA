package generics;

public abstract class ObjectiveFunction{
	private ProblemData problemData;
	
	public ObjectiveFunction(ProblemData problemData) {
		this.problemData = problemData; 
	}
	
	public abstract double getFitness(Individual individual);
	
	public abstract double getPopulationFitnessMean(Population<Individual> population);
	
	public abstract double getPopulationFitnessStandardDeviation(Population<Individual> population);

	public abstract Individual getPopulationBestFitIndividual(Population<Individual> population);
	
	public abstract Individual getPopulationWorstFitIndividual(Population<Individual> population);

	public abstract void printPopulationStatisticInfo(Population<Individual> population, double optimalScore);

	public abstract void sortPopulationByFitness(Population<Individual> population);

	public ProblemData getProblemData() {
		return problemData;
	}

	public void setProblemData(ProblemData problemData) {
		this.problemData = problemData;
	}
}
