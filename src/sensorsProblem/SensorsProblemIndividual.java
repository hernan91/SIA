package sensorsProblem;

import java.util.Random;

import generics.BinaryRepresentationIndividual;
import generics.Location;

public class SensorsProblemIndividual extends BinaryRepresentationIndividual {
	private Location[] transmissorsPositions;
	private SensorsFieldData sensorsFieldData;
	
	public SensorsProblemIndividual(int[] allele, Location[] transmissorsPositions, double fitness, SensorsFieldData sensorsFieldData) {
		super(allele);
		this.transmissorsPositions = transmissorsPositions;
		this.setFitness(fitness);
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
			locationArray[i] = getRandomLocation();
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
			newAllele[i] = oldAllele[i];
			newLocations[i] = new Location(oldLocations[i].getPosX(), oldLocations[i].getPosY()); 
		}
		SensorsFieldData data = new SensorsFieldData(sensorsFieldData.getTransmissorRangeRatio(), 
				sensorsFieldData.getGridSizeX(), sensorsFieldData.getGridSizeY());
		return new SensorsProblemIndividual(newAllele, newLocations, this.getFitness(), data);
	}

	public Location[] getTransmissorsPositions() {
		return transmissorsPositions;
	}

	public void setTransmissorsPositions(Location[] transmissorsPositions) {
		this.transmissorsPositions = transmissorsPositions;
	}

	public SensorsFieldData getSensorFieldData() {
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
	
}
