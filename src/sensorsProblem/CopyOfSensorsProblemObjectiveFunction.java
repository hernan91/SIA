package sensorsProblem;

import java.util.ArrayList;

import generics.Individual;
import generics.Location;
import generics.ObjectiveFunction;

public abstract class CopyOfSensorsProblemObjectiveFunction extends ObjectiveFunction{
	private SensorFieldData conf;
	private ArrayList<Location> transmissorsPositions;
	private float alfa;
	
	
	protected CopyOfSensorsProblemObjectiveFunction(SensorFieldData conf, ArrayList<Location> transmissorsPositions, float alfa) {
		super();
		this.setConf(conf);
		this.setTransmissorsPositions(transmissorsPositions);
		this.alfa = alfa;
	}
	
	public abstract int[][] getCoverageGrid(Individual possibleSolution);
	
	protected int[][] initializeGrid(){
		int grid[][] = new int[getConf().getGridSizeX()][getConf().getGridSizeY()];
		for(int i=0; i<getConf().getGridSizeX(); i++) {
			for(int j=0; j<getConf().getGridSizeY(); j++) {
				grid[i][j] = 0;
			}
		}
		return grid;
	}
	
	private double calcFitness(int coverageGrid[][], int usedTransmissors, float alfa) {
		float coverRange = (100*getCoveredPoints(coverageGrid)) /getConf().getGridSize();
		if(coverRange==0) return 0;
		return Math.pow(coverRange, alfa) / usedTransmissors;
		/**
		 * El problema con alfa=1 cuando se usa un solo transmisor, entonces queda (11**1)/1 = 11.11
		 * El fitness es el mismo que cuando se usan 9, queda (100**1)/9=11.11
		 * Alfa siempre > 1
		 */
	}
	
	public int getCoveredPoints(int coverageGrid[][]) {
		int coveredPoints = 0;
		for(int i=0; i<getConf().getGridSizeX(); i++) {
			for(int j=0; j<getConf().getGridSizeY(); j++) {
				coveredPoints += coverageGrid[i][j];
			}
		}
		return coveredPoints;
	}
	
	public ArrayList<Location> getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setTransmissorsPositions(ArrayList<Location> transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}

	public SensorFieldData getConf() {
		return conf;
	}

	public void setConf(SensorFieldData conf) {
		this.conf = conf;
	}
	
	@Override
	public double getFitness(Individual possibleSolution) {
		int[][] coverageGrid =  getCoverageGrid(possibleSolution);
		int usedTransmissors = 0;
		for(int bit : possibleSolution.getAllele()) usedTransmissors += bit; 
		return calcFitness(coverageGrid, usedTransmissors, alfa);
	}
	
	public static double obtFitness(Individual solution) {
		
	}
}

	
