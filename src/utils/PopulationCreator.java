package utils;

import java.util.ArrayList;
import java.util.Iterator;

import individuals.BinaryRepresentationIndividual;
import individuals.SensorsProblemIndividual;
import others.Population;
import problemData.SensorsFieldData;

public class PopulationCreator {
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
