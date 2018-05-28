package utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import generics.BinaryRepresentationIndividual;
import generics.Population;
import sensorsProblem.SensorsFieldData;
import sensorsProblem.SensorsProblemIndividual;

public class IndividualsListCreator {
	public static ArrayList<SensorsProblemIndividual> createSensorsProblemIndividualList(int alleleLength, int numberOfIndividuals, SensorsFieldData data){
		ArrayList<SensorsProblemIndividual> individuals = new ArrayList<>();
		for(int i=0; i<numberOfIndividuals; i++){
			individuals.add(new SensorsProblemIndividual(alleleLength, data));
		}
		return individuals;
	}
	
	public static Population<BinaryRepresentationIndividual> fromSensorsProblemPopToPop(Population<SensorsProblemIndividual> pop){
		ArrayList<BinaryRepresentationIndividual> inds = (ArrayList<BinaryRepresentationIndividual>) pop.getIndividuals().stream().map(ind->(BinaryRepresentationIndividual) ind).collect(Collectors.toList());
		return new Population<BinaryRepresentationIndividual>(inds);
	}
}
