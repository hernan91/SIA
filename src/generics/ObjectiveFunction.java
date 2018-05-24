package generics;

import sensorsProblem.SensorsProblemIndividual;

public abstract class ObjectiveFunction {
	//ver como hacer mas generico
	public abstract double getFitness(SensorsProblemIndividual individual);
}
