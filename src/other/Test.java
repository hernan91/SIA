package other;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;
import generics.Location;
import generics.Population;
import generics.SensorsProblemPopulation;
import operators.SensorsProblemMutationOperator;
import operators.SensorsProblemOnePointCrossoverOperator;
import sensorsProblem.SensorFieldData;
import sensorsProblem.SensorsProblemIndividual;
import sensorsProblem.SquareRatioObjectiveFunction;

public class Test {
	public static void main(String args[]) {
		int range = 5;
		int alleleLength = 60;
		int numberOfIndividuals = 100;
		Population pop = new Population(alleleLength, numberOfIndividuals);
		SensorFieldData areaData = new SensorFieldData(range, 60, 60);
		SquareRatioObjectiveFunction objFunc = new SquareRatioObjectiveFunction(areaData, 2);
		SensorsProblemOnePointCrossoverOperator crossOp = new SensorsProblemOnePointCrossoverOperator();
		SensorsProblemMutationOperator mutOp = new SensorsProblemMutationOperator();
		SensorsProblemIndividual spInd1 = new SensorsProblemIndividual(alleleLength, areaData);
		SensorsProblemIndividual spInd2 = new SensorsProblemIndividual(alleleLength, areaData);
		ArrayList<Individual> individuals = new ArrayList<Individual>();
		individuals.add(spInd1);
		individuals.add(spInd2);
		crossOp.operate(individuals);
		mutOp.operate(individuals);
		
		
		
		String dir = "/home/darkside/git/SIA/pruebita";
		//CsvWriter.writeLocations(dir, transmissorPositions);
		//CsvWriter.writeSolution(dir, "solution", individual);
		//ArrayList<SensorsProblemIndividual> individuals = new ArrayList<SensorsProblemIndividual>();
		//for(int i=0; i<numberOfIndividuals; i++) individuals.add(new SensorsProblemIndividual(alleleLength, areaData));
				
	}
}