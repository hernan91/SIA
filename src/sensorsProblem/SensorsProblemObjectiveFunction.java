package sensorsProblem;

import java.util.ArrayList;

import generics.Individual;
import generics.Location;
import generics.ObjectiveFunction;

public abstract class SensorsProblemObjectiveFunction extends ObjectiveFunction{
	private SensorFieldData conf;
	private float alfa;
	
	
	public SensorsProblemObjectiveFunction(SensorFieldData conf, float alfa) {
		super();
		this.setConf(conf);
		this.alfa = alfa;
	}
	
	public abstract int[][] getCoverageGrid(SensorsProblemIndividual possibleSolution);
	
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
	
	public SensorFieldData getConf() {
		return conf;
	}

	public void setConf(SensorFieldData conf) {
		this.conf = conf;
	}
	
	@Override
	public double getFitness(Individual individual) {
		SensorsProblemIndividual spIndividual = (SensorsProblemIndividual) individual;
		int[][] coverageGrid =  getCoverageGrid(spIndividual);
		int usedTransmissors = 0;
		for(int bit : individual.getAllele()) usedTransmissors += bit; 
		return calcFitness(coverageGrid, usedTransmissors, alfa);
	}
}

	
