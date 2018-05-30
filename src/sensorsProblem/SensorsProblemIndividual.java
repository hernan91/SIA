package sensorsProblem;

import java.util.Random;

import generics.BinaryRepresentationIndividual;
import generics.Location;

public class SensorsProblemIndividual extends BinaryRepresentationIndividual {
	private Location[] transmissorsPositions;
	private SensorsFieldData sensorsFieldData;
	
	public SensorsProblemIndividual(int[] allele, double fitness, Location[] transmissorsPositions, SensorsFieldData sensorsFieldData) {
		super(allele, fitness);
		this.transmissorsPositions = transmissorsPositions;
		this.sensorsFieldData = sensorsFieldData;
	}
	
	public SensorsProblemIndividual(int alleleLength, SensorsFieldData sensorFieldData) {
		super(alleleLength);
		this.setAllele(getBinaryString(alleleLength)); 
		this.sensorsFieldData = sensorFieldData;
		this.transmissorsPositions = generateRandomLocations();
	}
	
	private Location[] generateRandomLocations(){
		int alleleLength = this.getAllele().length;
		Location[] locationArray = new Location[alleleLength];
		for(int i=0; i<alleleLength; i++) {
			locationArray[i] = this.getAllele()[i]==1? getRandomLocation():null;
		}
		return locationArray;
	}
	
	@Override
	public SensorsProblemIndividual copy() {
		int[] oldAllele = this.getAllele();
		int[] newAllele = new int[oldAllele.length];
		Location[] oldLocations = this.getTransmissorsPositions();
		Location[] newLocations = new Location[oldLocations.length];
		for(int i=0; i<newAllele.length; i++) {
			if(oldAllele[i]==1) newLocations[i] = new Location(oldLocations[i].getPosX(), oldLocations[i].getPosY());
			else newLocations[i] = null;
			newAllele[i] = oldAllele[i];
		}
		return new SensorsProblemIndividual(newAllele, this.getFitness(), newLocations, sensorsFieldData);
	}

	public Location[] getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setTransmissorsPositions(Location[] transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}

	public SensorsFieldData getSensorsFieldData() {
		return sensorsFieldData;
	}

	public void setSensorFieldData(SensorsFieldData sensorFieldData) {
		this.sensorsFieldData = sensorFieldData;
	}
	
	private Location getRandomLocation() {
		Random randX = new Random();
		Random randY = new Random();
		return new Location(randX.nextInt(sensorsFieldData.getGridSizeX()), randY.nextInt(sensorsFieldData.getGridSizeY()));
	}
	
	public void moveSensorToRandomLocation(int allelePos) {
		transmissorsPositions[allelePos] = getRandomLocation();
	}
	
	@Override
	public void printData() {
		super.printData();
		System.out.println("Locations: ");
		for(Location l : transmissorsPositions) System.out.println("x= "+l.getPosX()+"y= "+l.getPosY());
	}
}
