package executor;

import geneticAlgorithms.CanonicalGA;
import oneMaxProblem.OneMaxObjectiveFunction;
import oneMaxProblem.OneMaxProblemData;
import operators.BinaryTournamentSelectionOperator;
import operators.BitFlipMutationOperator;
import operators.Operator;
import operators.ReplacementOperator;
import operators.TwoPointsCrossoverOperator;
import sensorsProblem.Location;
import sensorsProblem.SensorsCoverageOptimizationProblemData;
import sensorsProblem.SquareGridProblemData;

public class MainExecutor{
	private static int alleleLength = 30; //longitud del alelo
	private static int popSolutionNumber = 200; //numero de soluciones de la poblacion
	private static int maxGen = 1000; //2000 numero màximo de generaciones
	private static float maxFit = 1111.11f; //maximo fitness a encontrar hasta parar
	private static int alfa = 2;
	private static float crossoverProbability = 0.5f;
	private static float mutationProbability = 0.01f;
		
	
	
	
	public static void main(String[] args) {
		//oneMax();
		sensorsCoverage();
	}
	
	public static void oneMax() {
		OneMaxProblemData oneMaxProblemData = new OneMaxProblemData(new OneMaxObjectiveFunction(), alleleLength, popSolutionNumber, maxGen, maxFit, alfa, crossoverProbability, mutationProbability);
		//ga.execute(oneMaxProblemData); //ver porque no esta llegando al optimo, si no a 90
		
		//ga.executeWithSensorsProblemObjectiveF(sensorsCoverageOptimizationProblemData);
		//problema con referenceiias
	}
	
	public static void sensorsCoverage() {
		int sensorRatio = 10;
		int gridSizeX = 60;
		int gridSizeY = 60;
		SquareGridProblemData squareGridProblemData = new SquareGridProblemData(sensorRatio, gridSizeX, gridSizeY);
		Location[] transmissorsPositions = {
				new Location(10,10), new Location(10,30), new Location(10,50),
				new Location(30,10), new Location(30,30), new Location(30,50),
				new Location(50,10), new Location(50,30), new Location(50,50),
				new Location(10,10), new Location(10,30), new Location(10,50),
				new Location(30,10), new Location(30,30), new Location(30,50),
				new Location(30,10), new Location(30,30), new Location(30,50),
				new Location(10,10), new Location(10,30), new Location(10,50),
				new Location(30,10), new Location(30,30), new Location(30,50),
				new Location(50,10), new Location(50,30), new Location(50,50),
				new Location(10,10), new Location(10,30), new Location(10,50)		
		};
		
		SensorsCoverageOptimizationProblemData sensorsCoverageOptimizationProblemData = new SensorsCoverageOptimizationProblemData(
				alleleLength, popSolutionNumber, maxGen, maxFit, alfa, crossoverProbability, mutationProbability, 
				squareGridProblemData, transmissorsPositions);
		
		Operator selectionOperator = new BinaryTournamentSelectionOperator(sensorsCoverageOptimizationProblemData.getObjFunc());
		Operator crossoverOperator = new TwoPointsCrossoverOperator();
		Operator mutationOperator = new BitFlipMutationOperator();
		Operator replacementOperator = new ReplacementOperator(sensorsCoverageOptimizationProblemData.getObjFunc(), popSolutionNumber);
		
		CanonicalGA ga = new CanonicalGA(crossoverProbability, mutationProbability, 
				selectionOperator, crossoverOperator, mutationOperator, replacementOperator, sensorsCoverageOptimizationProblemData);
		ga.execute();
		
	}

}
