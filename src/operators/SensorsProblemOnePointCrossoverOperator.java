package operators;

import java.util.ArrayList;
import java.util.Random;

import generics.BinaryRepresentationIndividual;
import generics.Location;
import sensorsProblem.SensorsProblemIndividual;

public class SensorsProblemOnePointCrossoverOperator extends Operator{

	@Override
	public ArrayList<BinaryRepresentationIndividual> operate(ArrayList<BinaryRepresentationIndividual> individuals) {
		Random rand = new Random();
		ArrayList<BinaryRepresentationIndividual> offspringIndividuals = new ArrayList<BinaryRepresentationIndividual>();
		SensorsProblemIndividual ind1 = (SensorsProblemIndividual) individuals.get(0);
		SensorsProblemIndividual ind2 = (SensorsProblemIndividual) individuals.get(1);
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
		offspringIndividuals.add(ind1);
		offspringIndividuals.add(ind2);
		return offspringIndividuals;
	}
	
}
