package sensorsProblem;

import java.util.ArrayList;
import java.util.Iterator;

import generics.BinaryRepresentationIndividual;
import generics.Population;

public class SensorsProblemPopulation2 extends Population{
	private SensorsFieldData data;
	
	public SensorsProblemPopulation(int alleleLength, int numberOfIndividuals, SensorsFieldData data) {
		ArrayList<BinaryRepresentationIndividual> individuals = new ArrayList<BinaryRepresentationIndividual>();
		for(int i=0; i<numberOfIndividuals; i++){
			individuals.add(new SensorsProblemIndividual(alleleLength, data));
		}
		this.setAlleleLength(alleleLength);
		this.setIndividuals(individuals);
		this.setData(data);
	}


	public SensorsProblemPopulation(ArrayList<SensorsProblemIndividual> inds, SensorsFieldData data) {
		ArrayList<BinaryRepresentationIndividual> individuals = new ArrayList<BinaryRepresentationIndividual>();
		for(int i=0; i<inds.size(); i++){
			individuals.add(new SensorsProblemIndividual(this.getAlleleLength(), this.getData()));
		}
		this.setIndividuals(individuals);
		this.setAlleleLength(individuals.get(0).getAllele().length);
		this.setData(data);
	}
	
	public SensorsProblemPopulation copy() {
		Iterator<SensorsProblemIndividual> it = this.getIndividuals().iterator();
		ArrayList<SensorsProblemIndividual> clone = new ArrayList<BinaryRepresentationIndividual>();
		while(it.hasNext()) {
			clone.add(it.next().copy());
		}
		return new Population(clone);
	}

	public SensorsFieldData getData() {
		return data;
	}

	public void setData(SensorsFieldData data) {
		this.data = data;
	}
}
