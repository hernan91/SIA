package other;

import generics.Individual;
import sensorsProblem.Location;
import sensorsProblem.SensorsProblemObjectiveFunction;
import sensorsProblem.SearchSpaceProblemData;

/**
 * n=60
 * gridSize = n*n
 * radio = 10
 * sensorLocation = contendra instancias de la clase Location
 * transNumber = 9+21
 * Los 9 primeros sensores del alelo de la solucion van a describir la solucion optima, estos serian los estaticos
 * Los otros 21 sensores se generaran de forma aleatoria...
 * 
 */
public class PruebaCuadrada {
	static int sensorRatio = 10;
	static int gridSizeX = 60;
	static int gridSizeY = 60;
	static int alfa = 2;
	static int[] optimalSolution = {1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	static Individual transmissorsPopulation = new Individual( optimalSolution );
	static Location[] transmissorsPositions = {
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
	/**
	 * static Location[] transmissorsPositions = {
		new Location(9,9), new Location(9,29), new Location(9,49),
		new Location(29,9), new Location(29,29), new Location(29,49),
		new Location(49,9), new Location(49,29), new Location(49,49),
	};
	*/
	

	
	public static void main(String[] args) {
		SearchSpaceProblemData conf = new SearchSpaceProblemData(sensorRatio, gridSizeX, gridSizeY);
		SensorsProblemObjectiveFunction fitnessFunc = new SensorsProblemObjectiveFunction(conf);
		
		double fitness = fitnessFunc.fitnessMaximizeRange(transmissorsPopulation, transmissorsPositions, alfa);
		System.out.printf("El fitness es de %f", fitness);
	}

}
