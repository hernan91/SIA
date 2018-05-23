package sensorsProblem;

import java.util.ArrayList;

import generics.Location;
import geneticAlgorithms.ProblemData;

public class SensorsCoverageOptimizationProblemData extends ProblemData{
	private SensorFieldData squareGridProblemData;
	private ArrayList<Location> transmissorsPositions;
	
	public SensorsCoverageOptimizationProblemData(double maxFit, int alfa, SensorFieldData squareGridProblemData, 
			ArrayList<Location> transmissorsPositions, SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction) {
		super(sensorsProblemObjectiveFunction, maxFit, alfa);
		this.squareGridProblemData = squareGridProblemData;
		this.transmissorsPositions = transmissorsPositions;
	}
	
	public SensorFieldData getSquareGridProblemData() {
		return squareGridProblemData;
	}

	public ArrayList<Location> getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setSquareGridProblemData(SensorFieldData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}

	public void setTransmissorsPositions(ArrayList<Location> transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}
}
