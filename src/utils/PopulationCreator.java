package utils;

import java.util.ArrayList;
import java.util.Iterator;

import generics.BinaryRepresentationIndividual;
import generics.Population;
import sensorsProblem.SensorsFieldData;
import sensorsProblem.SensorsProblemIndividual;

public class PopulationCreator {
	public static Population<SensorsProblemIndividual> createSensorsProblemPopulation(int alleleLength, int numberOfIndividuals, SensorsFieldData fieldData) {
		Population<SensorsProblemIndividual> pop = new Population<>();
		for(int i=0; i<numberOfIndividuals; i++) {
			BinaryRepresentationIndividual ind = (BinaryRepresentationIndividual) new SensorsProblemIndividual(alleleLength, fieldData);
			pop.getIndividuals().add(ind);
		}
		pop.setAlleleLength(alleleLength);
		return pop;
	}
	
	public static Population<SensorsProblemIndividual> copySensorsPopulation(Population<SensorsProblemIndividual> pop, int alleleLength, int numberOfIndividuals) {
		Iterator<BinaryRepresentationIndividual> it = pop.getIndividuals().iterator();
		ArrayList<BinaryRepresentationIndividual> clone = new ArrayList<>();
		while(it.hasNext()) {
			SensorsProblemIndividual ind = (SensorsProblemIndividual) it.next();
			clone.add(ind.copy());
		}
		return new Population<SensorsProblemIndividual>(clone);
	}
}
