package sensorsProblem;

import geneticAlgorithms.GAProblemData;

public class SensorsCoverageOptimizationProblemData extends GAProblemData{
	private SquareGridProblemData squareGridProblemData;
	private Location[] transmissorsPositions;
	
	public SensorsCoverageOptimizationProblemData(int alleleLength, int popSolutionNumber, int maxGen, float maxFit,
			int alfa, float crossoverProbability, float mutationProbability, SquareGridProblemData squareGridProblemData, Location[] transmissorsPositions) {
		super(
				new SensorsProblemObjectiveFunction(squareGridProblemData, transmissorsPositions, alfa),
				alleleLength, popSolutionNumber, maxGen, maxFit, alfa, crossoverProbability, mutationProbability);
		this.squareGridProblemData = squareGridProblemData;
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
