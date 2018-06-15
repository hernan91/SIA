package ReeplacementOperators;

import java.util.ArrayList;

import individuals.Individual;
import objectiveFunctions.ObjectiveFunction;
import operatorsModels.ReplacementOperator;
import others.Population;
import problemData.ProblemData;
import problemData.SensorsProblemData;

public class SensorsProblemProportionReplacementOperator extends ReplacementOperator{

	public SensorsProblemProportionReplacementOperator(ProblemData problemData) {
		super(problemData);
	}

	public Population operate(Population oldGeneration, Population newGeneration) {
		SensorsProblemData sp = (SensorsProblemData) getProblemData();
		float takenFromnewGenPercentage = sp.getTakenFromNewGenProportion();
		ArrayList<Individual> offspring = new ArrayList<Individual>();
		ObjectiveFunction objFunc = getProblemData().getObjFunc();
		int popSize = oldGeneration.getNumberOfIndividuals();
		int takeFromOldPop = Math.round( (1-takenFromnewGenPercentage) * popSize);// [0,limInf]
		int takeFromNewPop = Math.round( takenFromnewGenPercentage * popSize);// [popSize,limSup]
		objFunc.sortPopulationByFitness(oldGeneration.getIndividuals());
		objFunc.sortPopulationByFitness(newGeneration.getIndividuals());
		for (int i = 0; i < takeFromOldPop; i++) {
			offspring.add(oldGeneration.getIndividuals().get(i));	
			//offspring.add(oldGeneration.getIndividuals().remove(oldGeneration.getNumberOfIndividuals()-1));	
		}
		for (int i = 0; i < takeFromNewPop; i++) {
			offspring.add(newGeneration.getIndividuals().get(i));
		}
		return new Population(offspring, getProblemData());
	}
}
