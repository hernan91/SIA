package other;

import generics.Individual;
import sensorsProblem.Location;
import sensorsProblem.SensorsProblemObjectiveFunction;
import sensorsProblem.SquareGridProblemData;

public class asd {

	public static void main(String[] args) {
		int[] allele = {1,1,1,1,1,1,1,1,1};
		Individual ind = new Individual(allele);
		int sensorRatio = 10;
		int gridSizeX = 60;
		int gridSizeY = 60;
		int alfa = 1;
		SquareGridProblemData squareGridProblemData = new SquareGridProblemData(sensorRatio, gridSizeX, gridSizeY);
		Location[] transmissorsPositions = {
				new Location(10,10), new Location(10,30), new Location(10,50),
				new Location(30,10), new Location(30,30), new Location(30,50),
				new Location(50,10), new Location(50,30), new Location(50,50),	
		};
		SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction = new SensorsProblemObjectiveFunction(
				squareGridProblemData, transmissorsPositions, alfa);
		System.out.println(sensorsProblemObjectiveFunction.obtainFitness(ind));
	}
}