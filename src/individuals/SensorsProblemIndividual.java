package individuals;

import java.util.Arrays;
import java.util.Random;

import others.Location;
import problemData.SensorsFieldData;

public class SensorsProblemIndividual extends BinaryRepresentationIndividual {
	private Location[] transmissorsPositions;
	private SensorsFieldData sensorsFieldData;
	
	public SensorsProblemIndividual(int[] allele, double fitness, Location[] transmissorsPositions, SensorsFieldData sensorsFieldData) {
		super(allele, fitness);
		this.transmissorsPositions = transmissorsPositions;
		this.sensorsFieldData = sensorsFieldData;
	}
	
	public SensorsProblemIndividual(int alleleLength, SensorsFieldData sensorsFieldData) {
		super(alleleLength);
		this.setAllele(getBinaryString(alleleLength)); 
		this.sensorsFieldData = sensorsFieldData;
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

	public void setSensorsFieldData(SensorsFieldData sensorsFieldData) {
		this.sensorsFieldData = sensorsFieldData;
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
		for(Location l : transmissorsPositions) {
			if (l!=null) System.out.println("x="+l.getPosX()+" y="+l.getPosY());
		}
	}

	@Override
	public int compareTo(BinaryRepresentationIndividual i2) {
		SensorsProblemIndividual ind2 = (SensorsProblemIndividual) i2;
		if(this.getFitness()!=ind2.getFitness()) return -1;
		if(this.getActiveSensorsNumber()!=ind2.getActiveSensorsNumber()) return -1;
    	if( !(Arrays.equals(this.getAllele(), ind2.getAllele())) ) return -1;
    	for(int i=0; i<this.getTransmissorsPositions().length; i++) {
    		int comparation = this.getTransmissorsPositions()[i].compareTo(ind2.getTransmissorsPositions()[i]);
    		if(comparation!=0) return -1;
    	}
        return 0;
	}
}
