package sensorsProblem;

import generics.Location;

public class CircularRatioObjectiveFunction<T extends SensorsProblemIndividual> extends SensorsProblemObjectiveFunction<T> {
	public CircularRatioObjectiveFunction(SensorsFieldData conf, float alfa){
		super(conf, alfa);
	}
	
	//cambiar a protected luego de la prueba
	public int[][] getCoverageGrid(T ind) {
		int coverageGrid[][] = initializeGrid();
		int availableTransmissorsNumber = ind.getAllele().length; //transmisores disponibles para ser usados
		
		for(int t=0; t<availableTransmissorsNumber; t++) { //t = transmissor
			int[] transmissorStatusAllele = ind.getAllele(); //describe el estado de los transmisores //apagados, desactivados
			if(transmissorStatusAllele[t]==1) {
				SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
				Location transmissorLocation = individual.getTransmissorsPositions()[t];
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