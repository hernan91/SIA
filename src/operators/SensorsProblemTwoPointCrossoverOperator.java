package operators;

import java.util.Random;

import individuals.Individual;
import individuals.SensorsProblemIndividual;
import operatorsModels.CrossoverOperator;
import others.Location;
import problemData.ProblemData;

public class SensorsProblemTwoPointCrossoverOperator extends CrossoverOperator{
	public SensorsProblemTwoPointCrossoverOperator(ProblemData problemData) {
		super(problemData);
	}

	@Override
	public void operate(Individual individual1, Individual individual2) {
		Random rand = new Random();
		SensorsProblemIndividual ind1 = (SensorsProblemIndividual) individual1;
		SensorsProblemIndividual ind2 = (SensorsProblemIndividual) individual2;
		int alleleLength = ind1.getAllele().length;
		int cutPoint1 = rand.nextInt(alleleLength-2)+1;
		int cutPoint2 = new Random().nextInt(alleleLength-cutPoint1-1)+cutPoint1;
		for(int i=cutPoint1; i<=cutPoint2; i++) {
			int k = ind1.getAllele()[i];
			Location l = ind1.getTransmissorsPositions()[i]; 	
			ind1.getAllele()[i] = ind2.getAllele()[i];
			ind2.getAllele()[i] = k;
			ind1.getTransmissorsPositions()[i] = ind2.getTransmissorsPositions()[i];
			ind2.getTransmissorsPositions()[i] = l;
		}
	}
}
