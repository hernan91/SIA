package operators;

import java.util.Random;

import individuals.Individual;
import individuals.SensorsProblemIndividual;
import operatorsModels.CrossoverOperator;
import others.Location;
import problemData.ProblemData;

public class SensorsProblemOnePointCrossoverOperator extends CrossoverOperator{

	public SensorsProblemOnePointCrossoverOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public void operate(Individual individual1, Individual individual2) {
		Random rand = new Random();
		SensorsProblemIndividual ind1 = (SensorsProblemIndividual) individual1;
		SensorsProblemIndividual ind2 = (SensorsProblemIndividual) individual2;
		int alleleLength = ind1.getAllele().length;
		int cutPoint = rand.nextInt(alleleLength-1)+1;
		for(int i=0; i<cutPoint; i++) {
			int k = ind1.getAllele()[i];
			Location l = ind1.getTransmissorsPositions()[i]; 	
			ind1.getAllele()[i] = ind2.getAllele()[i];
			ind2.getAllele()[i] = k;
			ind1.getTransmissorsPositions()[i] = ind2.getTransmissorsPositions()[i];
			ind2.getTransmissorsPositions()[i] = l;
		}
	}
	
}
