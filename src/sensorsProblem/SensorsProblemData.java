package sensorsProblem;

import generics.BinaryProblemIndividual;
import generics.Individual;
import generics.ProblemData;

public class SensorsProblemData extends ProblemData{
	private SensorsFieldData squareGridProblemData;
	
	public SensorsProblemData(double maxFit, int alfa, SensorsFieldData squareGridProblemData, 
			SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction, Individual individual) {
		super(sensorsProblemObjectiveFunction, maxFit, alfa, individual);
		this.squareGridProblemData = squareGridProblemData;
	}
	
	public SensorsFieldData getSensorsFieldProblemData() {
		return squareGridProblemData;
	}

	public void setSensorsFieldProblemData(SensorsFieldData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}

	@Override
	public Individual getIndividualInstance(int alleleLength) throws ClassNotFoundException{
		String className = this.getIndividual().getClass().getName().substring(this.getIndividual().getClass().getName().indexOf(".")+1);
		switch(className) {
			case "BinaryProblemIndividual": return new BinaryProblemIndividual(alleleLength);
			case "SensorsProblemIndividual": return new SensorsProblemIndividual(alleleLength, squareGridProblemData);
			default: throw new ClassNotFoundException();
		}
	}
}
