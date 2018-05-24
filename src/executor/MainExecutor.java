package executor;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;
import generics.Location;
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
import sensorsProblem.SensorFieldData;
import sensorsProblem.SensorsProblemData;
import sensorsProblem.CircularRatioObjectiveFunction;
import sensorsProblem.SquareRatioObjectiveFunction;

public class MainExecutor{
	private static int popSolutionNumber = 100; //numero de soluciones de la poblacion
	private static int maxGen = 1000; //2000 numero màximo de generaciones
	private static int alfa = 2; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private static double maxFit; //maximo fitness a encontrar hasta parar
	private static float crossoverProbability = 0.9f;
	private static float mutationProbability = 1/popSolutionNumber;
	private static boolean tracing = true;
	private static int alleleLength; //longitud del alelo

	
	public static void main(String[] args) {
		//oneMax();
		sensorsCoverage();
	}
	
	public static void sensorsCoverage() {
		int sensorRatio = 10;
		int gridSizeX = 60;
		int gridSizeY = 60;
		int randomlyDistributedTransmissors = 0;
		
		SensorFieldData squareGridProblemData = new SensorFieldData(sensorRatio, gridSizeX, gridSizeY);
		int[] arrayCoord = {10,10 , 10,30 , 10,50 , 30,10 , 30,30 , 30,50 , 50,10 , 50,30 , 50,50 , 23,38 , 1,11 , 5,26 , 38,56 , 34,50,
				18,36, 48,14, 8,1, 57,27, 18,56, 49,44, 2,28, 49,51, 47,44, 21,4, 9,25, 6,42, 3,0, 50,31, 31,41, 11,47, 20,15, 30,22,
				42,25, 48,36, 36,47, 18,45, 3,58, 29,59, 58,59, 42,4, 56,37, 57,52, 8,17, 19,23, 24,31, 38,12, 34,36, 41,37, 58,3,
				53,19, 2,54, 12,60, 37,11, 54,14, 44,19, 31,3, 48,57, 8,36, 46,59, 25,37};
		ArrayList<Location> transmissorsPositions = new ArrayList<Location>();
		for(int i=0; i<arrayCoord.length; i=i+2) transmissorsPositions.add(new Location(arrayCoord[i], arrayCoord[i+1]));
		addRandomDistributedSensors(randomlyDistributedTransmissors, gridSizeX, gridSizeY, transmissorsPositions);
		
		alleleLength = transmissorsPositions.size()+randomlyDistributedTransmissors;
		maxFit = 99999999999999999999999999999999999f;//Math.pow(100, alfa)/transmissorsPositions.size();
		
		//SensorsProblemCircularRatioObjectiveFunction sensorsProblemObjectiveFunction = new SensorsProblemCircularRatioObjectiveFunction(squareGridProblemData, transmissorsPositions, alfa);
		SquareRatioObjectiveFunction sensorsProblemObjectiveFunction = new SquareRatioObjectiveFunction(squareGridProblemData, transmissorsPositions, alfa);
		
		SensorsProblemData sensorsCoverageOptimizationProblemData = new SensorsProblemData(
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
		//CsvWriter.writeLocations("locations.csv", transmissorsPositions);
		//CsvWriter.writeSolution("solution.csv", bestIndividual);
	}
	
	public static void oneMax() {
		alleleLength = 9;
		OneMaxProblemData oneMaxProblemData = new OneMaxProblemData(new OneMaxObjectiveFunction(), maxFit, alfa);
		Operator selectionOperator = new BinaryTournamentSelectionOperator(oneMaxProblemData.getObjFunc());
		Operator crossoverOperator = new TwoPointCrossoverOperator();
		Operator mutationOperator = new BitFlipMutationOperator();
		Operator replacementOperator = new ReplacementOperator(oneMaxProblemData.getObjFunc(), popSolutionNumber);
		CanonicalGA ga = new CanonicalGA(alleleLength, popSolutionNumber, maxGen, crossoverProbability, mutationProbability, 
				selectionOperator, crossoverOperator, mutationOperator, replacementOperator, oneMaxProblemData);
		ga.execute(tracing);
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
