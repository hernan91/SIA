package operators;

import java.util.ArrayList;

import individuals.Individual;
import operatorsModels.Operator;
import operatorsModels.OperatorInt;
import others.Population;
import problemData.ProblemData;
import problemData.SensorsProblemData;

public class SensorsProblemPercentageReplacementStrategy extends Operator implements OperatorInt{

	public SensorsProblemPercentageReplacementStrategy(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public ArrayList<Individual> operate(Population oldPop, Population newPop) {
		float takenFromOffspringPercentage = 0.9f;
		SensorsProblemData spData = (SensorsProblemData) getProblemData();
		int popSize = populations.size()/2;
		int takeFromOldPop = Math.round( (1-takenFromOffspringPercentage) * popSize);// [0,limInf]
		int takeFromNewPop = Math.round( takenFromOffspringPercentage * popSize);// [popSize,limSup]
		
		
		for (int i = 0; i < takeFormOldPop; i++) {
			array_type array_element = array[i];	
		}
		for (int i = 0; i < takeFormOldPop; i++) {
			array_type array_element = array[i];
			
		}
		return null;
	}


	@Override
	public ArrayList<Individual> operate() {
		return null;
	}

	
	

}
