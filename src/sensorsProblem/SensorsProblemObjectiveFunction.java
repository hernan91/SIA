package sensorsProblem;

import java.util.ArrayList;

import generics.Individual;
import generics.ObjectiveFunction;

public class SensorsProblemObjectiveFunction extends ObjectiveFunction{
	private SquareGridProblemData conf;
	private ArrayList<Location> transmissorsPositions;
	private float alfa;
	
	
	public SensorsProblemObjectiveFunction(SquareGridProblemData conf, ArrayList<Location> transmissorsPositions, float alfa) {
		super();
		this.conf = conf;
		this.transmissorsPositions = transmissorsPositions;
		this.alfa = alfa;
	}
	public double fitnessMaximizeRange(Individual transmissorsPopulationStatus) {
		int coverageGrid[][] = initializeGrid();
		int usedTransmissors = 0;
		int availableTransmissorsNumber = transmissorsPopulationStatus.getAllele().length; //transmisores disponibles para ser usados
		
		for(int t=0; t<availableTransmissorsNumber; t++) { //t = transmissor
			int[] transmissorStatusAllele = transmissorsPopulationStatus.getAllele(); //describe el estado de los transmisores //apagados, desactivados
			if(transmissorStatusAllele[t]==1) {
				Location transmissorLocation = transmissorsPositions.get(t);
				CoverageLimits limits = new CoverageLimits(transmissorLocation, conf);
				for(int i=limits.getLimInfX(); i<limits.getLimSupX(); i++){
					for(int j=limits.getLimInfY(); j<limits.getLimSupY(); j++) {
						coverageGrid[i][j] = 1; //aca podria meter un contador para medir la interferencia
					}
				}
				usedTransmissors++;
			}	
		}
		return getFitness(coverageGrid, usedTransmissors, alfa);
	}
	
	public int[][] initializeGrid(){
		int grid[][] = new int[conf.getGridSizeX()][conf.getGridSizeY()];
		for(int i=0; i<conf.getGridSizeX(); i++) {
			for(int j=0; j<conf.getGridSizeY(); j++) {
				grid[i][j] = 0;
			}
		}
		return grid;
	}
	
	public double getFitness(int coverageGrid[][], int usedTransmissors, float alfa) {
		float coverRange = (100*getCoveredPoints(coverageGrid)) /conf.getGridSize();
		if(coverRange==0) return 0;
		return Math.pow(coverRange, alfa) / usedTransmissors;
		/**
		 * El problema es que cuando se usa un solo transmisor, entonces queda (11**1)/1 = 11.11
		 * El fitness es el mismo que cuando se usan 9, queda (100**1)/9=11.11
		 */
	}
	
	public int getCoveredPoints(int coverageGrid[][]) {
		int coveredPoints = 0;
		for(int i=0; i<conf.getGridSizeX(); i++) {
			for(int j=0; j<conf.getGridSizeY(); j++) {
				coveredPoints += coverageGrid[i][j];
			}
		}
		return coveredPoints;
	}
	
	public class CoverageLimits {
		private int limInfX;
		private int limSupX;
		private int limInfY;
		private int limSupY;
		
		public CoverageLimits(Location transmissorLocation, SquareGridProblemData conf) {
			limInfX = Math.max( transmissorLocation.getPosX()-conf.getTransmissorRangeRatio(), 0);
			limSupX = Math.min( transmissorLocation.getPosX()+conf.getTransmissorRangeRatio(), conf.getGridSizeX() );
			limInfY = Math.max( transmissorLocation.getPosY()-conf.getTransmissorRangeRatio(), 0);
			limSupY = Math.min( transmissorLocation.getPosY()+conf.getTransmissorRangeRatio(), conf.getGridSizeY() );
		}

		public int getLimInfX() {
			return limInfX;
		}

		public int getLimSupX() {
			return limSupX;
		}

		public int getLimInfY() {
			return limInfY;
		}

		public int getLimSupY() {
			return limSupY;
		}
	}

	@Override
	public double obtainFitness(Individual transmissorsPopulationStatus) {
		return fitnessMaximizeRange(transmissorsPopulationStatus);
	}
}
