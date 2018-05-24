package sensorsProblem;

import java.util.Random;

import generics.Individual;
import generics.Location;

public class SensorsProblemIndividual extends Individual {
	private Location[] transmissorsPositions;
	private SensorFieldData sensorFieldData;
	
	public SensorsProblemIndividual(int[] allele, Location[] transmissorsPositions, double fitness) {
		super(allele);
		this.transmissorsPositions = transmissorsPositions;
		this.setFitness(fitness);
	}
	
	public SensorsProblemIndividual(int alleleLength, SensorFieldData sensorFieldData) {
		super(alleleLength);
		this.setAllele(getBinaryString(alleleLength)); 
		this.sensorFieldData = sensorFieldData;
		this.transmissorsPositions = generateRandomLocations();
	}
	
	private Location[] generateRandomLocations(){
		int alleleLength = this.getAllele().length;
		Location[] locationArray = new Location[alleleLength];
		for(int i=0; i<alleleLength; i++) {
			locationArray[i] = getRandomLocation();
		}
		return locationArray;
	}
	
	public SensorsProblemIndividual copy() {
		int[] oldAllele = this.getAllele();
		int[] newAllele = new int[oldAllele.length];
		Location[] oldLocations = this.getTransmissorsPositions();
		Location[] newLocations = new Location[oldLocations.length];
		for(int i=0; i<newAllele.length; i++) {
			newAllele[i] = oldAllele[i];
			newLocations[i] = new Location(oldLocations[i].getPosX(), oldLocations[i].getPosY()); 
		}
		return new SensorsProblemIndividual(newAllele, newLocations, this.getFitness());
	}

	public Location[] getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setTransmissorsPositions(Location[] transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}

	public SensorFieldData getSensorFieldData() {
		return sensorFieldData;
	}

	public void setSensorFieldData(SensorFieldData sensorFieldData) {
		this.sensorFieldData = sensorFieldData;
	}
	
	private Location getRandomLocation() {
		Random randX = new Random();
		Random randY = new Random();
		return new Location(randX.nextInt(sensorFieldData.getGridSizeX()), randY.nextInt(sensorFieldData.getGridSizeY()));
	}
	
	public void moveSensorToRandomLocation(int allelePos) {
		transmissorsPositions[allelePos] = getRandomLocation();
	}
	
}
