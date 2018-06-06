package operators;

import java.util.ArrayList;

import individuals.Individual;
import objectiveFunctions.ObjectiveFunction;
import operatorsModels.ReplacementOperator;
import others.Population;
import problemData.ProblemData;

public class SensorsProblemPercentageReplacementOperator extends ReplacementOperator{

	public SensorsProblemPercentageReplacementOperator(ProblemData problemData) {
		super(problemData);
	}

	public Population operate(Population oldGeneration, Population newGeneration) {
		float takenFromnewGenPercentage = 0.95f;
		ArrayList<Individual> offspring = new ArrayList<Individual>();
		ObjectiveFunction objFunc = getProblemData().getObjFunc();
		int popSize = oldGeneration.getNumberOfIndividuals();
		int takeFromOldPop = Math.round( (1-takenFromnewGenPercentage) * popSize);// [0,limInf]
		int takeFromNewPop = Math.round( takenFromnewGenPercentage * popSize);// [popSize,limSup]
		objFunc.sortPopulationByFitness(oldGeneration.getIndividuals());
		objFunc.sortPopulationByFitness(newGeneration.getIndividuals());
		for (int i = 0; i < takeFromOldPop; i++) {
			offspring.add(oldGeneration.getIndividuals().get(i));	
		}
		for (int i = 0; i < takeFromNewPop; i++) {
			offspring.add(newGeneration.getIndividuals().get(i));
		}
		return new Population(offspring, getProblemData());
	}
}
