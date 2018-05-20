package sensorsProblem;

import java.util.ArrayList;

import generics.Individual;

public class CircularRatioObjectiveFunction extends SensorsProblemObjectiveFunction {
	public CircularRatioObjectiveFunction(SearchSpaceProblemData conf, ArrayList<Location> transmissorsPositions, float alfa){
		super(conf, transmissorsPositions, alfa);
	}
	
	//cambiar a protected luego de la prueba
	public int[][] getCoverageGrid(Individual possibleSolution) {
		int coverageGrid[][] = initializeGrid();
		int availableTransmissorsNumber = possibleSolution.getAllele().length; //transmisores disponibles para ser usados
		
		for(int t=0; t<availableTransmissorsNumber; t++) { //t = transmissor
			int[] transmissorStatusAllele = possibleSolution.getAllele(); //describe el estado de los transmisores //apagados, desactivados
			if(transmissorStatusAllele[t]==1) {
				Location transmissorLocation = getTransmissorsPositions().get(t);
				int centerX = transmissorLocation.getPosX();
				int centerY = transmissorLocation.getPosY();
				CoverageLimits limits = new CircleCoverageLimits(transmissorLocation, getConf());
				for(int i=limits.getLimInfX(); i<limits.getLimSupX(); i++){
					for(int j=limits.getLimInfY(); j<limits.getLimSupY(); j++) {
						int ratio = super.getConf().getTransmissorRangeRatio();
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