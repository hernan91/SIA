package objectiveFunctions;

import coverageLimits.CoverageLimits;
import coverageLimits.SquareCoverageLimits;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import others.Location;
import problemData.SensorsFieldData;

public class SquareRatioObjectiveFunction extends SensorsProblemObjectiveFunction {
	public SquareRatioObjectiveFunction(SensorsFieldData sensorsFieldData, int alfa) {
		super(sensorsFieldData, alfa);
	}
	
	public int[][] getCoverageGrid(Individual ind) {
		SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
		int coverageGrid[][] = initializeGrid();
		int availableTransmissorsNumber = individual.getAllele().length; //transmisores disponibles para ser usados
		
		for(int t=0; t<availableTransmissorsNumber; t++) { //t = transmissor
			int[] transmissorStatusAllele = individual.getAllele(); //describe el estado de los transmisores //apagados, desactivados
			if(transmissorStatusAllele[t]==1) {
				Location transmissorLocation = individual.getTransmissorsPositions()[t];
				
				CoverageLimits limits = new SquareCoverageLimits(transmissorLocation, sensorsFieldData);
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
