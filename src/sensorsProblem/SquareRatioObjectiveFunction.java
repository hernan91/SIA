package sensorsProblem;

import java.util.ArrayList;

import generics.Individual;

public class SquareRatioObjectiveFunction extends SensorsProblemObjectiveFunction {
	public SquareRatioObjectiveFunction(SearchSpaceProblemData conf, ArrayList<Location> transmissorsPositions, float alfa){
		super(conf, transmissorsPositions, alfa);
	}
	
	public int[][] getCoverageGrid(Individual possibleSolution) {
		int coverageGrid[][] = initializeGrid();
		int availableTransmissorsNumber = possibleSolution.getAllele().length; //transmisores disponibles para ser usados
		
		for(int t=0; t<availableTransmissorsNumber; t++) { //t = transmissor
			int[] transmissorStatusAllele = possibleSolution.getAllele(); //describe el estado de los transmisores //apagados, desactivados
			if(transmissorStatusAllele[t]==1) {
				Location transmissorLocation = getTransmissorsPositions().get(t);
				CoverageLimits limits = new SquareCoverageLimits(transmissorLocation, getConf());
				for(int i=limits.getLimInfX(); i<limits.getLimSupX(); i++){
					for(int j=limits.getLimInfY(); j<limits.getLimSupY(); j++) {
						coverageGrid[i][j] = 1; //aca podria meter un contador para medir la interferencia
					}
				}
			}	
		}
		return coverageGrid;
	}
}
