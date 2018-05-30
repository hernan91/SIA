package problemData;

import individuals.BinaryProblemIndividual;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import objectiveFunctions.ObjectiveFunction;
import objectiveFunctions.SensorsProblemObjectiveFunction;
import others.Location;

public class SensorsProblemData extends ProblemData{
	private SensorsFieldData sensorsFieldData;
	private Location[] prefixedPositions;
	private int randomlyDistributedTransmissors;
	
	public SensorsProblemData(double maxFit, int alfa, ObjectiveFunction objectiveFunction,
			Individual individual, Location[] prefixedPositions) {
		super(objectiveFunction, maxFit, alfa, individual);
		this.sensorsFieldData = ((SensorsProblemIndividual)getIndividual()).getSensorsFieldData();
		this.prefixedPositions = prefixedPositions;
		this.randomlyDistributedTransmissors = getAlleleLength()-prefixedPositions.length;
	}
	
	public SensorsFieldData getSensorsFieldProblemData() {
		return sensorsFieldData;
	}

	public void setSensorsFieldProblemData(SensorsFieldData squareGridProblemData) {
		this.sensorsFieldData = squareGridProblemData;
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
		return sensorsFieldData;
	}

	public void setSquareGridProblemData(SensorsFieldData squareGridProblemData) {
		this.sensorsFieldData = squareGridProblemData;
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
