package main;

public class test {

	public static void main(String[] args) {
		int alleleLength = 30; //longitud del alelo
		int popSolutionNumber = 200; //numero de soluciones de la poblacion
		int maxGen = 100; //2000 numero màximo de generaciones
		float maxFit = 1111.11f; //maximo fitness a encontrar hasta parar
		int alfa = 2;
		float crossoverProbability = 0.5f;
		float mutationProbability = 0.01f;
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
		
		GAProblemData gaProblemData = new GAProblemData(alleleLength, popSolutionNumber, maxGen, maxFit, alfa, crossoverProbability, mutationProbability);
		SensorsCoverageOptimizationProblemData sensorsCoverageOptimizationProblemData = new SensorsCoverageOptimizationProblemData(alleleLength, 
				popSolutionNumber, maxGen, maxFit, alfa, crossoverProbability, mutationProbability, 
				sensorRatio, gridSizeX, gridSizeY, transmissorsPositions);
		
		CanonicalGA ga = new CanonicalGA();
		//ga.executeWithOneMaxObjectiveF(gaProblemData);
		ga.executeWithSensorsProblemObjectiveF(sensorsCoverageOptimizationProblemData);
		//ga.executeWithSensorsProblemObjectiveF(sensorsCoverageOptimizationProblemData);
		//problema con referenceiias
	}

}
