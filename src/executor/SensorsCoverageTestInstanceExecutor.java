package executor;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;
import geneticAlgorithms.CanonicalGA;
import oneMaxProblem.OneMaxObjectiveFunction;
import oneMaxProblem.OneMaxProblemData;
import operators.BinaryTournamentSelectionOperator;
import operators.BitFlipMutationOperator;
import operators.OnePointCrossoverOperator;
import operators.Operator;
import operators.ReplacementOperator;
import operators.TwoPointCrossoverOperator;
import other.CsvWriter;
import sensorsProblem.Location;
import sensorsProblem.SearchSpaceProblemData;
import sensorsProblem.SensorsCoverageOptimizationProblemData;
import sensorsProblem.SensorsProblemCircularRatioObjectiveFunction;
import sensorsProblem.SensorsProblemSquareRatioObjectiveFunction;

public class SensorsCoverageTestInstanceExecutor{
	private static int numExecutions = 30;
	private static String[] operators = {"1PC", "2PC"};
	private static int[] maxGens = {100, 500};
	private static float[] crossoverProbabilities = {0.8f, 0.9f, 1.0f};
	private static String[] objectiveFunctions = {"SquareRatio", "CircularRatio"};
	private static int popSolutionNumber = 100; //numero de soluciones de la poblacion
	private static float mutationProbability = 1/popSolutionNumber;
	private static int alfa = 2; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private static double maxFit = 9999999999999f; //maximo fitness a encontrar hasta parar
	private static boolean tracing = false;
	
	static int sensorRatio = 10;
	static int gridSizeX = 60;
	static int gridSizeY = 60;
	static int randomlyDistributedTransmissors = 0;
	static SearchSpaceProblemData squareGridProblemData = new SearchSpaceProblemData(sensorRatio, gridSizeX, gridSizeY);
	static int[] arrayCoord = {10,10 , 10,30 , 10,50 , 30,10 , 30,30 , 30,50 , 50,10 , 50,30 , 50,50 , 23,38 , 1,11 , 5,26 , 38,56 , 34,50,
			18,36, 48,14, 8,1, 57,27, 18,56, 49,44, 2,28, 49,51, 47,44, 21,4, 9,25, 6,42, 3,0, 50,31, 31,41, 11,47, 20,15, 30,22,
			42,25, 48,36, 36,47, 18,45, 3,58, 29,59, 58,59, 42,4, 56,37, 57,52, 8,17, 19,23, 24,31, 38,12, 34,36, 41,37, 58,3,
			53,19, 2,54, 12,60, 37,11, 54,14, 44,19, 31,3, 48,57, 8,36, 46,59, 25,37};

	
	public static void main(String[] args) {
		Individual indConf1 = sensorsCoverage(numExecutions, crossoverProbabilities[0], operators[0], maxGens[0], objectiveFunctions[0]);
		
	}
	
	public static Individual sensorsCoverage(float numExecutions, float crossoverProbability, String operator,  int maxGen, String objectiveFunction) {
		ArrayList<Location> transmissorsPositions = new ArrayList<Location>();
		for(int i=0; i<arrayCoord.length; i=i+2) transmissorsPositions.add(new Location(arrayCoord[i], arrayCoord[i+1]));
		addRandomDistributedSensors(randomlyDistributedTransmissors, gridSizeX, gridSizeY, transmissorsPositions);
		
		//int alleleLength = transmissorsPositions.size()+randomlyDistributedTransmissors;
		
		//SensorsProblemCircularRatioObjectiveFunction sensorsProblemObjectiveFunction = new SensorsProblemCircularRatioObjectiveFunction(squareGridProblemData, transmissorsPositions, alfa);
		SensorsProblemSquareRatioObjectiveFunction sensorsProblemObjectiveFunction = new SensorsProblemSquareRatioObjectiveFunction(squareGridProblemData, transmissorsPositions, alfa);
		
		SensorsCoverageOptimizationProblemData sensorsCoverageOptimizationProblemData = new SensorsCoverageOptimizationProblemData(
				maxFit, alfa, squareGridProblemData, transmissorsPositions, sensorsProblemObjectiveFunction);
		
		Operator selectionOperator = new BinaryTournamentSelectionOperator(sensorsCoverageOptimizationProblemData.getObjFunc());
		Operator crossoverOperator = new OnePointCrossoverOperator();
		//Operator crossoverOperator = new TwoPointCrossoverOperator();
		//Operator crossoverOperator = new ThreePointCrossoverOperator();
		Operator mutationOperator = new BitFlipMutationOperator();
		Operator replacementOperator = new ReplacementOperator(sensorsCoverageOptimizationProblemData.getObjFunc(), popSolutionNumber);
		
		CanonicalGA ga = new CanonicalGA(transmissorsPositions.size(), popSolutionNumber, maxGen, crossoverProbability, mutationProbability, 
				selectionOperator, crossoverOperator, mutationOperator, replacementOperator, sensorsCoverageOptimizationProblemData);
		Individual bestIndividual = ga.execute(tracing);
		CsvWriter.writeLocations("locations.csv", transmissorsPositions);
		CsvWriter.writeSolution("solution.csv", bestIndividual);
		return bestIndividual;
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
