package sensorsProblem;

import java.util.ArrayList;

import geneticAlgorithms.ProblemData;

public class SensorsCoverageOptimizationProblemData extends ProblemData{
	private SearchSpaceProblemData squareGridProblemData;
	private ArrayList<Location> transmissorsPositions;
	
	public SensorsCoverageOptimizationProblemData(double maxFit, int alfa, SearchSpaceProblemData squareGridProblemData, 
			ArrayList<Location> transmissorsPositions, SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction) {
		super(sensorsProblemObjectiveFunction, maxFit, alfa);
		this.squareGridProblemData = squareGridProblemData;
		this.transmissorsPositions = transmissorsPositions;
	}
	
	public SearchSpaceProblemData getSquareGridProblemData() {
		return squareGridProblemData;
	}

	public ArrayList<Location> getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setSquareGridProblemData(SearchSpaceProblemData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}

	public void setTransmissorsPositions(ArrayList<Location> transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}
}
