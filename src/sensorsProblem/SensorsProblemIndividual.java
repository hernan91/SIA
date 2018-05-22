package sensorsProblem;

import java.util.ArrayList;

import generics.Individual;
import generics.Location;

public class SensorsProblemIndividual extends Individual {
	private ArrayList<Location> transmissorsPositions;
	
	public SensorsProblemIndividual(int[] allele, ArrayList<Location> transmissorsPositions) {
		super(allele);
		this.transmissorsPositions = transmissorsPositions;
	}

	public ArrayList<Location> getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setTransmissorsPositions(ArrayList<Location> transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}
}
