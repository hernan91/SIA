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
	private float takenFromNewGenProportion;

	public SensorsProblemData(double maxFit, int alfa, float takenFromNewGenProportion, ObjectiveFunction objectiveFunction,
			Individual individual, Location[] prefixedPositions, int maxGen) {
		super(objectiveFunction, maxFit, alfa, individual, maxGen);
		this.sensorsFieldData = ((SensorsProblemIndividual)getIndividual()).getSensorsFieldData();
		this.prefixedPositions = prefixedPositions;
		this.randomlyDistributedTransmissors = getAlleleLength()-prefixedPositions.length;
		this.takenFromNewGenProportion = takenFromNewGenProportion;
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
		Individual individual;
		switch(className) {
			case "BinaryProblemIndividual": {
				individual = new BinaryProblemIndividual(getAlleleLength()); 
				break;
			}
			case "SensorsProblemIndividual": {
				individual = new SensorsProblemIndividual(getAlleleLength(), getSensorsFieldProblemData()); 
				break;
			}
			default: throw new ClassNotFoundException();
		}
		SensorsProblemObjectiveFunction objFunc = (SensorsProblemObjectiveFunction) this.getObjFunc();
		individual.setFitness(objFunc.getFitness(individual));
		return individual;
	}
	
	public int getAlleleLength() {
		SensorsProblemIndividual sensorsProblemIndividual = (SensorsProblemIndividual) getIndividual();
		return sensorsProblemIndividual.getAllele().length;
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

	public float getTakenFromNewGenProportion() {
		return takenFromNewGenProportion;
	}

	public void setTakenFromNewGenProportion(float takenFromNewGenProportion) {
		this.takenFromNewGenProportion = takenFromNewGenProportion;
	}

}
