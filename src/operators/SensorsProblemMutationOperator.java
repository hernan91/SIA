package operators;

import java.util.ArrayList;
import java.util.Random;

import individuals.Individual;
import individuals.SensorsProblemIndividual;
import operatorsModels.Operator;
import others.Location;
import problemData.ProblemData;

public class SensorsProblemMutationOperator extends Operator{
	
	public SensorsProblemMutationOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public ArrayList<Individual> operate(ArrayList<Individual> individuals) {
		SensorsProblemIndividual individual = (SensorsProblemIndividual) individuals.get(0);
		int[] allele = individual.getAllele();
		Location[] transmissorsPositions = individual.getTransmissorsPositions();
		Random rand = new Random();
		int limitPosition = allele.length;
		int choosenPos = rand.nextInt(limitPosition);
		if(allele[choosenPos]==0) {
			allele[choosenPos] = 1;
			individual.moveSensorToRandomLocation(choosenPos);
		}
		else {
			float moveOrRemoveProbability = rand.nextFloat();
			if(moveOrRemoveProbability>0.5) 
				individual.moveSensorToRandomLocation(choosenPos);
			else {
				allele[choosenPos] = 0;
				transmissorsPositions[choosenPos] = null;
			}
		}
		return individuals;
	}
}
