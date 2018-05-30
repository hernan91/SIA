package sensorsProblem;

import generics.Individual;
import generics.Location;

public class CircularRatioObjectiveFunction extends SensorsProblemObjectiveFunction {
	public CircularRatioObjectiveFunction(SensorsProblemData sensorsProblemData){
		super(sensorsProblemData);
	}
	
	public int[][] getCoverageGrid(Individual ind) {
		SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
		int coverageGrid[][] = initializeGrid();
		int availableTransmissorsNumber = individual.getAllele().length; //transmisores disponibles para ser usados
		SensorsProblemData sensorsProblemData = (SensorsProblemData) getProblemData();
		
		for(int t=0; t<availableTransmissorsNumber; t++) { //t = transmissor
			int[] transmissorStatusAllele = individual.getAllele(); //describe el estado de los transmisores //apagados, desactivados
			if(transmissorStatusAllele[t]==1) {
				Location transmissorLocation = individual.getTransmissorsPositions()[t];
				int centerX = transmissorLocation.getPosX();
				int centerY = transmissorLocation.getPosY();
				CoverageLimits limits = new CircleCoverageLimits(transmissorLocation, sensorsProblemData.getSensorsFieldProblemData());
				for(int i=limits.getLimInfX(); i<limits.getLimSupX(); i++){
					for(int j=limits.getLimInfY(); j<limits.getLimSupY(); j++) {
						int ratio = sensorsProblemData.getSensorsFieldProblemData().getTransmissorRangeRatio();
						if( ( Math.pow(centerX-i,2) + Math.pow(centerY-j,2) ) <= Math.pow(ratio,2) ) {
							try {
								coverageGrid[i][j] = 1;
							}
							catch(IndexOutOfBoundsException ex) {
								System.out.println("centerX= "+centerX);
								System.out.println("centerY= "+centerY);
								System.out.println("(i,j) "+"("+i+","+j+")");
								System.exit(0);
							}
						}
					}
				}
			}	
		}
		return coverageGrid;
	}


}