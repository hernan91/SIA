package other;

import java.util.ArrayList;

import generics.Individual;
import sensorsProblem.Location;
import sensorsProblem.SensorsProblemObjectiveFunction;
import sensorsProblem.SquareGridProblemData;

public class asd {

	public static void main(String[] args) {
		int[] allele = {1,1,1,1,1,1,1,1,1};
		//int[] allele = {1,1,1,1,1,1,1,1,1};
		Individual ind = new Individual(allele);
		ArrayList<Location> transmissorsPositions = new ArrayList<Location>();
		transmissorsPositions.add(new Location(10,10));
		transmissorsPositions.add(new Location(10,30));
		transmissorsPositions.add(new Location(10,50));
		transmissorsPositions.add(new Location(30,10));
		transmissorsPositions.add(new Location(30,30));
		transmissorsPositions.add(new Location(30,50));
		transmissorsPositions.add(new Location(50,10));
		transmissorsPositions.add(new Location(50,30));
		transmissorsPositions.add(new Location(50,50));
		SensorsProblemObjectiveFunction obj = new SensorsProblemObjectiveFunction(new SquareGridProblemData(10, 60, 60),transmissorsPositions,2f);
		double fitness = obj.obtainFitness(ind);
		System.out.println(fitness);
		
	}
}