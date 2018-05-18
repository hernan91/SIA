package executor;

import java.util.ArrayList;
import java.util.Random;

import generics.Individual;
import geneticAlgorithms.CanonicalGA;
import oneMaxProblem.OneMaxObjectiveFunction;
import oneMaxProblem.OneMaxProblemData;
import operators.BinaryTournamentSelectionOperator;
import operators.BitFlipMutationOperator;
import operators.Operator;
import operators.ReplacementOperator;
import operators.TwoPointCrossoverOperator;
import other.CsvWriter;
import sensorsProblem.Location;
import sensorsProblem.SensorsCoverageOptimizationProblemData;
import sensorsProblem.SensorsProblemObjectiveFunction;
import sensorsProblem.SquareGridProblemData;

public class MainExecutor{
	private static int popSolutionNumber = 100; //numero de soluciones de la poblacion
	private static int maxGen = 100; //2000 numero màximo de generaciones
	private static int alfa = 2; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private static double maxFit; //maximo fitness a encontrar hasta parar
	private static float crossoverProbability = 0.9f;
	private static float mutationProbability = 1/16f;
	private static boolean tracing = true;
	private static int alleleLength; //longitud del alelo

	
	public static void main(String[] args) {
		//oneMax();
		sensorsCoverage();
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
	
	public static void sensorsCoverage() {
		int sensorRatio = 10;
		int gridSizeX = 60;
		int gridSizeY = 60;
		int randomlyDistributedTransmissors = 0;
		
		SquareGridProblemData squareGridProblemData = new SquareGridProblemData(sensorRatio, gridSizeX, gridSizeY);
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
		transmissorsPositions.add(new Location(23,38));
		transmissorsPositions.add(new Location(1,11));
		transmissorsPositions.add(new Location(5,26));
		transmissorsPositions.add(new Location(38,56));
		transmissorsPositions.add(new Location(34,50));
		transmissorsPositions.add(new Location(18,36));
		transmissorsPositions.add(new Location(48,14));
		transmissorsPositions.add(new Location(8,1));
		transmissorsPositions.add(new Location(57,27));
		transmissorsPositions.add(new Location(18,56));
		transmissorsPositions.add(new Location(49,44));
		transmissorsPositions.add(new Location(2,28));
		transmissorsPositions.add(new Location(49,51));
		transmissorsPositions.add(new Location(47,44));
		transmissorsPositions.add(new Location(21,4));
		transmissorsPositions.add(new Location(9,25));
		transmissorsPositions.add(new Location(6,42));
		transmissorsPositions.add(new Location(3,0));
		transmissorsPositions.add(new Location(50,31));
		transmissorsPositions.add(new Location(31,41));
		transmissorsPositions.add(new Location(11,47));
		transmissorsPositions.add(new Location(20,15));
		transmissorsPositions.add(new Location(30,22));
		transmissorsPositions.add(new Location(42,25));
		transmissorsPositions.add(new Location(48,36));
		transmissorsPositions.add(new Location(36,47));
		transmissorsPositions.add(new Location(18,45));
		transmissorsPositions.add(new Location(3,58));
		transmissorsPositions.add(new Location(29,59));
		transmissorsPositions.add(new Location(58,59));
		transmissorsPositions.add(new Location(42,4));
		transmissorsPositions.add(new Location(56,37));
		transmissorsPositions.add(new Location(57,52));
		transmissorsPositions.add(new Location(8,17));
		transmissorsPositions.add(new Location(19,23));
		transmissorsPositions.add(new Location(24,31));
		transmissorsPositions.add(new Location(38,12));
		transmissorsPositions.add(new Location(34,36));
		transmissorsPositions.add(new Location(41,37));
		transmissorsPositions.add(new Location(58,3));
		transmissorsPositions.add(new Location(53,19));
		transmissorsPositions.add(new Location(2,54));
		transmissorsPositions.add(new Location(12,60));
		transmissorsPositions.add(new Location(37,11));
		transmissorsPositions.add(new Location(54,14));
		transmissorsPositions.add(new Location(44,19));
		transmissorsPositions.add(new Location(31,3));
		transmissorsPositions.add(new Location(48,57));
		transmissorsPositions.add(new Location(8,36));
		transmissorsPositions.add(new Location(46,59));
		transmissorsPositions.add(new Location(25,37));

		
		alleleLength = transmissorsPositions.size()+randomlyDistributedTransmissors;
		/**
		 * Ver lo de Math.pow(100, alfa)/alleleLength
		 * alleleLength? No voy a tener la solucion optima siempre, no? Entonces, queda infinito?
		 */
		maxFit = 99999999999999999999999999999999999f;//Math.pow(100, alfa)/transmissorsPositions.size();
		addRandomDistributedSensors(randomlyDistributedTransmissors, gridSizeX, gridSizeY, transmissorsPositions);
		
		
		SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction = new SensorsProblemObjectiveFunction(
				squareGridProblemData, transmissorsPositions, alfa);
		
		SensorsCoverageOptimizationProblemData sensorsCoverageOptimizationProblemData = new SensorsCoverageOptimizationProblemData(
				maxFit, alfa, squareGridProblemData, transmissorsPositions, sensorsProblemObjectiveFunction);
		
		Operator selectionOperator = new BinaryTournamentSelectionOperator(sensorsCoverageOptimizationProblemData.getObjFunc());
		Operator crossoverOperator = new TwoPointCrossoverOperator();
		//Operator crossoverOperator = new ThreePointCrossoverOperator();
		Operator mutationOperator = new BitFlipMutationOperator();
		Operator replacementOperator = new ReplacementOperator(sensorsCoverageOptimizationProblemData.getObjFunc(), popSolutionNumber);
		
		CanonicalGA ga = new CanonicalGA(transmissorsPositions.size(), popSolutionNumber, maxGen, crossoverProbability, mutationProbability, 
				selectionOperator, crossoverOperator, mutationOperator, replacementOperator, sensorsCoverageOptimizationProblemData);
		Individual bestIndividual = ga.execute(tracing);
		CsvWriter.writeLocations("locations.csv", transmissorsPositions);
		CsvWriter.writeSolution("solution.csv", bestIndividual);
	}
	
	//esto va temporalmente
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
