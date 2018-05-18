package sensorsProblem;

import java.util.ArrayList;

import geneticAlgorithms.ProblemData;

public class SensorsCoverageOptimizationProblemData extends ProblemData{
	private SquareGridProblemData squareGridProblemData;
	private ArrayList<Location> transmissorsPositions;
	
	public SensorsCoverageOptimizationProblemData(double maxFit, int alfa, SquareGridProblemData squareGridProblemData, 
			ArrayList<Location> transmissorsPositions, SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction) {
		super(sensorsProblemObjectiveFunction, maxFit, alfa);
		this.squareGridProblemData = squareGridProblemData;
		this.transmissorsPositions = transmissorsPositions;
	}
	
	public SquareGridProblemData getSquareGridProblemData() {
		return squareGridProblemData;
	}

	public ArrayList<Location> getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setSquareGridProblemData(SquareGridProblemData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}

	public void setTransmissorsPositions(ArrayList<Location> transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}
}
