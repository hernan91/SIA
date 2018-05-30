package sensorsProblem;

import generics.BinaryProblemIndividual;
import generics.Individual;
import generics.Location;
import generics.ProblemData;

public class SensorsProblemData extends ProblemData{
	private SensorsFieldData squareGridProblemData;
	private Location[] prefixedPositions;
	private int randomlyDistributedTransmissors;
	
	public SensorsProblemData(double maxFit, int alfa, SensorsProblemObjectiveFunction sensorsProblemObjectiveFunction,
			Individual individual, SensorsFieldData squareGridProblemData, Location[] prefixedPositions) {
		super(sensorsProblemObjectiveFunction, maxFit, alfa, individual);
		this.squareGridProblemData = squareGridProblemData;
		this.prefixedPositions = prefixedPositions;
		this.randomlyDistributedTransmissors = getAlleleLength()-prefixedPositions.length;
	}
	
	public SensorsFieldData getSensorsFieldProblemData() {
		return squareGridProblemData;
	}

	public void setSensorsFieldProblemData(SensorsFieldData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}

	@Override
	public Individual getIndividualInstance() throws ClassNotFoundException{
		String className = this.getIndividual().getClass().getName().substring(this.getIndividual().getClass().getName().indexOf(".")+1);
		switch(className) {
			case "BinaryProblemIndividual": return new BinaryProblemIndividual(getAlleleLength());
			case "SensorsProblemIndividual": return new SensorsProblemIndividual(getAlleleLength(), getSensorsFieldData());
			default: throw new ClassNotFoundException();
		}
	}
	
	public int getAlleleLength() {
		SensorsProblemIndividual sensorsProblemIndividual = (SensorsProblemIndividual) getIndividual();
		return sensorsProblemIndividual.getAllele().length;
	}
	
	public SensorsFieldData getSensorsFieldData() {
		SensorsProblemIndividual sensorsProblemIndividual = (SensorsProblemIndividual) getIndividual();
		return sensorsProblemIndividual.getSensorsFieldData();
	}

	public SensorsFieldData getSquareGridProblemData() {
		return squareGridProblemData;
	}

	public void setSquareGridProblemData(SensorsFieldData squareGridProblemData) {
		this.squareGridProblemData = squareGridProblemData;
	}

	public Location[] getPrefixedPositions() {
		return prefixedPositions;
	}

	public void setPrefixedPositions(Location[] prefixedPositions) {
		this.prefixedPositions = prefixedPositions;
	}

	public int getRandomlyDistributedTransmissors() {
		return randomlyDistributedTransmissors;
	}

	public void setRandomlyDistributedTransmissors(int randomlyDistributedTransmissors) {
		this.randomlyDistributedTransmissors = randomlyDistributedTransmissors;
	}
	
	@Override
	public SensorsProblemObjectiveFunction getObjFunc() {
		return (SensorsProblemObjectiveFunction)super.getObjFunc();
	}
	
	@Override
	public SensorsProblemIndividual getIndividual() {
		return (SensorsProblemIndividual)super.getIndividual();
	}
}
