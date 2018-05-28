package sensorsProblem;

import generics.ProblemData;

public class SensorsProblemData extends ProblemData{
	private SensorsFieldData squareGridProblemData;
	
	public SensorsProblemData(double maxFit, int alfa, SensorsFieldData squareGridProblemData, 
			SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction) {
		super(sensorsProblemObjectiveFunction, maxFit, alfa);
		this.squareGridProblemData = squareGridProblemData;
	}
	
	public SensorsFieldData getSensorsFieldProblemData() {
		return squareGridProblemData;
	}

	public void setSensorsFieldProblemData(SensorsFieldData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}
}
