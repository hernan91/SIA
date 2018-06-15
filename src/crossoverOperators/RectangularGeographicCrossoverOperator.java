package crossoverOperators;

import java.util.ArrayList;
import java.util.Random;

import individuals.Individual;
import individuals.SensorsProblemIndividual;
import operatorsModels.CrossoverOperator;
import others.Location;
import problemData.ProblemData;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;

public class RectangularGeographicCrossoverOperator extends CrossoverOperator{

	public RectangularGeographicCrossoverOperator(ProblemData problemData, float crossoverProbability) {
		super(problemData, crossoverProbability);
	}
	
	protected void cross(Individual ind1, Individual ind2) {
		SensorsProblemIndividual individual1 = (SensorsProblemIndividual) ind1;
		SensorsProblemIndividual individual2 = (SensorsProblemIndividual) ind2;
		SensorsFieldData fieldData = ((SensorsProblemData) getProblemData()).getSensorsFieldProblemData();
		Random rand = new Random();
		int gridSizeX = fieldData.getGridSizeX();
		int gridSizeY = fieldData.getGridSizeY();
		int infBoundX = rand.nextInt(gridSizeX-1);
		int infBoundY = rand.nextInt(gridSizeY-1);
		int supBoundX = rand.nextInt(gridSizeX-infBoundX-1)+infBoundX+1;
		int supBoundY = rand.nextInt(gridSizeY-infBoundY-1)+infBoundY+1;
		Location[] transm1 = individual1.getTransmissorsPositions();
		Location[] transm2 = individual2.getTransmissorsPositions();
		ArrayList<Location> crossArea1Locations = new ArrayList<>();
		ArrayList<Location> crossArea2Locations = new ArrayList<>();
		for(int i=0; i<transm1.length; i++) {
			if( isInsideRectangle(transm1[i], infBoundX, infBoundY, supBoundX, supBoundY) ){
				crossArea1Locations.add(transm1[i]);
			}
			if( isInsideRectangle(transm2[i], infBoundX, infBoundY, supBoundX, supBoundY) ){
				crossArea2Locations.add(transm2[i]);
			}
		}
		interchangeSensors(individual1, individual2, crossArea1Locations, crossArea2Locations);
	}
	
	private boolean isInsideRectangle(Location loc, int infBoundX, int infBoundY, int supBoundX, int supBoundY) {
		if( loc == null) return false;
		if( (loc.getPosX() >= infBoundX && loc.getPosY() >= infBoundY) && (loc.getPosX() <= supBoundX && loc.getPosY() <= supBoundY) ){
			return true;
		}
		return false;
	}
	
	private void interchangeSensors(SensorsProblemIndividual individual1, SensorsProblemIndividual individual2, 
			ArrayList<Location> crossArea1Locations, ArrayList<Location> crossArea2Locations) {
		if(crossArea1Locations.size()==0 && crossArea2Locations.size()==0) return;
		int diff = crossArea1Locations.size()-crossArea2Locations.size();
		if(diff==0) {
			interchangeLocations(crossArea1Locations, crossArea2Locations);
		}
		else if(diff>0) {
			interchangeLocations(crossArea2Locations, crossArea1Locations);
			for(int i=crossArea2Locations.size(); i<crossArea1Locations.size(); i++) {
				generateNewLocation(individual2, crossArea1Locations.get(i));
				removeLocation(individual1, crossArea1Locations.get(i));
			}
		}
		else if(diff<0) {
			interchangeLocations(crossArea1Locations, crossArea2Locations);
			for(int i=crossArea1Locations.size(); i<crossArea2Locations.size(); i++) {
				generateNewLocation(individual1, crossArea2Locations.get(i));
				removeLocation(individual2, crossArea2Locations.get(i));
			}
		}
	}
	
	private void interchangeLocations(ArrayList<Location> crossArea1Locations, ArrayList<Location> crossArea2Locations) {
		for(int i=0; i<crossArea1Locations.size(); i++) {
			Location aux = crossArea1Locations.get(i);
			Location loc1 = crossArea1Locations.get(i);
			Location loc2 = crossArea2Locations.get(i);
			loc1 = loc2;
			loc2 = aux;
		}
	}
	
	private void generateNewLocation(SensorsProblemIndividual individual, Location newLoc) {
		int activeSensors = individual.getActiveSensorsNumber();
		int inactiveSensors = individual.getAlleleLength()-activeSensors;
		if(inactiveSensors==0) return;
		Location[] loc = individual.getTransmissorsPositions();
		for(int i=0; i<inactiveSensors; i++) {
			if(loc[i]==null) {
				loc[i] = new Location(newLoc.getPosX(), newLoc.getPosY());
				individual.getAllele()[i] = 1;
				return;
			}
		}
	}
	
	private void removeLocation(SensorsProblemIndividual individual, Location oldLoc) {
		int i = 0;
		for(Location loc : individual.getTransmissorsPositions()) {
			if(loc != null && loc.equals(oldLoc)) {
				loc = null;
				individual.getAllele()[i] = 0;
				i++;
			}
		}
	}
}
