package sensorsProblem;

import generics.BinaryRepresentationIndividual;
import generics.Location;

public class SquareRatioObjectiveFunction extends SensorsProblemObjectiveFunction {
	public SquareRatioObjectiveFunction(SensorsFieldData conf, float alfa){
		super(conf, alfa);
	}
	
	public int[][] getCoverageGrid(BinaryRepresentationIndividual ind) {
		int coverageGrid[][] = initializeGrid();
		int availableTransmissorsNumber = ind.getAllele().length; //transmisores disponibles para ser usados
		
		for(int t=0; t<availableTransmissorsNumber; t++) { //t = transmissor
			int[] transmissorStatusAllele = ind.getAllele(); //describe el estado de los transmisores //apagados, desactivados
			if(transmissorStatusAllele[t]==1) {
				SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
				Location transmissorLocation = individual.getTransmissorsPositions()[t];
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
