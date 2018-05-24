package operators;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;
import generics.Location;
import sensorsProblem.SensorsProblemIndividual;

public class SensorsProblemMutationOperator {
	public ArrayList<Individual> operate(ArrayList<Individual> individuals) {
		SensorsProblemIndividual individual = (SensorsProblemIndividual) individuals.get(0);
		int[] allele = individual.getAllele();
		Location[] transmissorsPositions = individual.getTransmissorsPositions();
		Random rand = new Random();
		int limitPosition = allele.length;
		int choosenPos = rand.nextInt(limitPosition);
		float moveOrRemoveProbability = rand.nextFloat();
		if(allele[choosenPos]==0) {
			allele[choosenPos] = 1;
			individual.moveSensorToRandomLocation(choosenPos);
		}
		else {
			if(moveOrRemoveProbability>0.5) 
				individual.moveSensorToRandomLocation(choosenPos);
			else {
				transmissorsPositions[choosenPos] = null;
			}
		}
		ArrayList<Individual> out = new ArrayList<Individual>();
		out.add(individual);
		return out;
	}
}
