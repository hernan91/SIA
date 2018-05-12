package sensorsProblem;

import geneticAlgorithms.ProblemData;

public class SensorsCoverageOptimizationProblemData extends ProblemData{
	private SquareGridProblemData squareGridProblemData;
	private Location[] transmissorsPositions;
	
	public SensorsCoverageOptimizationProblemData(float maxFit,	int alfa, SquareGridProblemData squareGridProblemData, 
			Location[] transmissorsPositions, SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction) {
		super(sensorsProblemObjectiveFunction, maxFit, alfa);
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
