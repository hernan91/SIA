package oneMaxProblem;

import GeneticAlgorithms.GAProblemData;
import generics.ObjectiveFunction;

public class OneMaxProblemData extends GAProblemData{

	public OneMaxProblemData(ObjectiveFunction objFunc, int alleleLength, int popSolutionNumber, int maxGen,
			float maxFit, int alfa, float crossoverProbability, float mutationProbability) {
		super(objFunc, alleleLength, popSolutionNumber, maxGen, maxFit, alfa, crossoverProbability, mutationProbability);
		// TODO Auto-generated constructor stub
	}

}
