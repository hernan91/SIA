package other;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;
import generics.Location;
import generics.Population;
import generics.SensorsProblemPopulation;
import sensorsProblem.SensorFieldData;
import sensorsProblem.SensorsProblemIndividual;
import sensorsProblem.SquareRatioObjectiveFunction;

public class Test {
	public static void main(String args[]) {
		int range = 5;
		Population pop = new Population(60, 100);
		SensorFieldData areaData = new SensorFieldData(range, 60, 60);
		
		SquareRatioObjectiveFunction objFunc = new SquareRatioObjectiveFunction(areaData, transmissorPositions, 2);
		
		
		System.out.println(objFunc.obtainFitness(individual));
		
		String dir = "/home/darkside/git/SIA/pruebita";
		CsvWriter.writeLocations(dir, transmissorPositions);
		CsvWriter.writeSolution(dir, "solution", individual);
	}
}