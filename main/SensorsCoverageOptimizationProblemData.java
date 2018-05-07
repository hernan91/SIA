package main;

public class SensorsCoverageOptimizationProblemData extends GAProblemData{
	private SquareGridProblemData squareGridProblemData;
	private Location[] transmissorsPositions;
	
	public SensorsCoverageOptimizationProblemData(int alleleLength, int popSolutionNumber, int maxGen, float maxFit,
			int alfa, float crossoverProbability, float mutationProbability, int sensorRatio, int gridSizeX,
			int gridSizeY, Location[] transmissorsPositions) {
		super(alleleLength, popSolutionNumber, maxGen, maxFit, alfa, crossoverProbability, mutationProbability);
		this.squareGridProblemData = new SquareGridProblemData(sensorRatio, gridSizeX, gridSizeY);;
		this.transmissorsPositions = transmissorsPositions;
	}
	
	public SquareGridProblemData getSquareGridProblemData() {
		return squareGridProblemData;
	}

	public Location[] getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setSquareGridProblemData(SquareGridProblemData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}

	public void setTransmissorsPositions(Location[] transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}
	
}
