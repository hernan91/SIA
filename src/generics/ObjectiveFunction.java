package generics;

import java.util.ArrayList;

public abstract class ObjectiveFunction{
	private ProblemData problemData;
	
	public ObjectiveFunction(ProblemData problemData) {
		this.problemData = problemData;
	}
	
	public abstract double getFitness(Individual individual);
	
	public abstract double getPopulationFitnessMean(Population population);
	
	public abstract double getPopulationFitnessStandardDeviation(Population population);

	public abstract Individual getPopulationBestFitIndividual(Population population);
	
	public abstract Individual getPopulationWorstFitIndividual(Population population);

	public abstract void printPopulationStatisticInfo(Population population, double optimalScore);

	public abstract void sortPopulationByFitness(ArrayList<Individual> population);

	public ProblemData getProblemData() {
		return problemData;
	}

	public void setProblemData(ProblemData problemData) {
		this.problemData = problemData;
	}
}
