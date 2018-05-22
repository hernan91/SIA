package other;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;
import generics.Location;
import sensorsProblem.DeploymentAreaData;
import sensorsProblem.SensorsProblemIndividual;
import sensorsProblem.SquareRatioObjectiveFunction;

public class Test {
	public static void main(String args[]) {
		int range = 5;
		ArrayList<Location> transmissorPositions = new ArrayList<Location>();
		addRandomDistributedSensors(60, 60, 60, transmissorPositions);
		int[] allele = new int[60];
		Random rand = new Random();
		for(int i=0; i<60; i++) {
			allele[i] = rand.nextInt(2);
		}
		Individual individual = new Individual(allele);
		DeploymentAreaData areaData = new DeploymentAreaData(range, 60, 60);
		SquareRatioObjectiveFunction objFunc = new SquareRatioObjectiveFunction(areaData, transmissorPositions, 2);
		System.out.println(objFunc.obtainFitness(individual));
		
		String dir = "/home/darkside/git/SIA/pruebita";
		CsvWriter.writeLocations(dir, transmissorPositions);
		CsvWriter.writeSolution(dir, "solution", individual);
	}
	
	public static ArrayList<Location> addRandomDistributedSensors(int numSensors, int xLim, int yLim, ArrayList<Location> locationArray) {
		int arraySize = locationArray.size();
		for(int i=arraySize; i<numSensors+arraySize; i++) {
			Random randX = new Random();
			Random randY = new Random();
			locationArray.add(new Location(randX.nextInt(xLim), randY.nextInt(yLim)));
		}
		return locationArray;
	}
}