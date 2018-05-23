package sensorsProblem;

import java.util.ArrayList;
import java.util.Random;

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
	
	public static ArrayList<Location> getRandomDistributedSensors(int numSensors, int xLim, int yLim) {
		ArrayList<Location> transmissorsPositions = new ArrayList<Location>();
		for(int i=0; i<numSensors; i++) {
			Random randX = new Random();
			Random randY = new Random();
			transmissorsPositions.add(new Location(randX.nextInt(xLim), randY.nextInt(yLim)));
		}
		return transmissorsPositions;
	}
}
