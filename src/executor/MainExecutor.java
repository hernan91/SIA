package executor;

import java.util.ArrayList;
import java.util.Random;

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
	private static int alleleLength = 30; //longitud del alelo
	private static int popSolutionNumber = 100; //numero de soluciones de la poblacion
	private static int maxGen = 5000; //2000 numero màximo de generaciones
	private static float maxFit = 11.11f; //maximo fitness a encontrar hasta parar
	private static int alfa = 1;
	private static float crossoverProbability = 0.5f;
	private static float mutationProbability = 0.01f;
	private static boolean tracing = false;

	
	public static void main(String[] args) {
		//oneMax();
		sensorsCoverage();
	}
	
	public static void oneMax() {
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
		addRandomDistributedSensors(21, gridSizeX, gridSizeY, transmissorsPositions);
		
		
		SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction = new SensorsProblemObjectiveFunction(
				squareGridProblemData, transmissorsPositions.toArray(new Location[30]), alfa);
		
		SensorsCoverageOptimizationProblemData sensorsCoverageOptimizationProblemData = new SensorsCoverageOptimizationProblemData(
				maxFit, alfa, squareGridProblemData, transmissorsPositions.toArray(new Location[30]), sensorsProblemObjectiveFunction);
		
		Operator selectionOperator = new BinaryTournamentSelectionOperator(sensorsCoverageOptimizationProblemData.getObjFunc());
		Operator crossoverOperator = new TwoPointCrossoverOperator();
		//Operator crossoverOperator = new ThreePointCrossoverOperator();
		Operator mutationOperator = new BitFlipMutationOperator();
		Operator replacementOperator = new ReplacementOperator(sensorsCoverageOptimizationProblemData.getObjFunc(), popSolutionNumber);
		
		CanonicalGA ga = new CanonicalGA(transmissorsPositions.size(), popSolutionNumber, maxGen, crossoverProbability, mutationProbability, 
				selectionOperator, crossoverOperator, mutationOperator, replacementOperator, sensorsCoverageOptimizationProblemData);
		ga.execute(tracing);
		CsvWriter writer = new CsvWriter();
		writer.writeCsvFile("locations.csv", transmissorsPositions);
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
