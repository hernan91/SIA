package sensorsProblem;

import java.util.ArrayList;

import generics.Location;
import geneticAlgorithms.ProblemData;

public class SensorsCoverageOptimizationProblemData extends ProblemData{
	private DeploymentAreaData squareGridProblemData;
	private ArrayList<Location> transmissorsPositions;
	
	public SensorsCoverageOptimizationProblemData(double maxFit, int alfa, DeploymentAreaData squareGridProblemData, 
			ArrayList<Location> transmissorsPositions, SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction) {
		super(sensorsProblemObjectiveFunction, maxFit, alfa);
		this.squareGridProblemData = squareGridProblemData;
		this.transmissorsPositions = transmissorsPositions;
	}
	
	public DeploymentAreaData getSquareGridProblemData() {
		return squareGridProblemData;
	}

	public ArrayList<Location> getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setSquareGridProblemData(DeploymentAreaData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}

	public void setTransmissorsPositions(ArrayList<Location> transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}
}
