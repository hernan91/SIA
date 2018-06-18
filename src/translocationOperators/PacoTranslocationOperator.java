package translocationOperators;

import java.util.ArrayList;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import objectiveFunctions.CircularRatioObjectiveFunction;
import operatorsModels.TranslocationOperator;
import others.Location;
import others.Population;
import problemData.ProblemData;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;
import utils.PairOfLocationsTuple;

//Es un operador de cruza de sensores, no de individuos
public class PacoTranslocationOperator extends TranslocationOperator{

	public PacoTranslocationOperator(ProblemData problemData, float threshold) {
		super(problemData, threshold);
	}
	
	@Override
	public Population operate(Population population) {
		ArrayList<Individual> offSprings = new ArrayList<>();
		for(Individual ind : population.getIndividuals()) {
			SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
			PairOfLocationsTuple tooClosePairs = getPairOfCloseNodes(individual);
			while(tooClosePairs!=null){
				Location newLoc = translocate(tooClosePairs.getLoc1(), tooClosePairs.getLoc2());
				if(newLoc!=null) {
					updateLocation(tooClosePairs.getLoc1(), newLoc, individual);
					removeLocation(individual, tooClosePairs.getLoc2());
				}
				tooClosePairs = getPairOfCloseNodes(individual);
			}
			offSprings.add(ind);
		}
		return new Population(offSprings, getProblemData());	
	}
	
	@Override
	protected Location translocate(Location loc1, Location loc2) {
		//if(intersectionExists(loc1,loc2)) {
			Location betweenTwoPointsLocation = betweenTwoPointsLocation(loc1, loc2);
			if(betweenTwoPointsLocation == null) return loc1;
			return betweenTwoPointsLocation;
		//}
		//return null;
	}
	
	protected void updateLocation(Location oldLocation, Location newLocation, SensorsProblemIndividual individual) {
		Location[] locations = individual.getTransmissorsPositions();
		for(int i=0; i<locations.length; i++) {
			if(locations[i]!= null && locations[i].equals(oldLocation)){
				locations[i] = newLocation;
				return;
			}
		}
	}
/*	private ArrayList<PairOfLocationsTuple> getPairsOfCloseNodes(SensorsProblemIndividual individual) {
		ArrayList<PairOfLocationsTuple> tooClosePairsArray = new ArrayList<>();
		//float thresHold = (float) = Math.sqrt(3f*fieldData.getTransmissorRangeRatio());
		for(Location loc1 : individual.getTransmissorsPositions()) {
			for(Location loc2 : individual.getTransmissorsPositions()) {
				if(!loc1.equals(loc2) && euclideanDistance(loc1, loc2)<getThreshold()) {
					PairOfLocationsTuple pair = new PairOfLocationsTuple(loc1, loc2);
					if(!arrayContainsPair(tooClosePairsArray, pair)) {
						tooClosePairsArray.add(pair);
					}
				}
			}
		}
		return tooClosePairsArray;
	}*/
	
	private PairOfLocationsTuple getPairOfCloseNodes(SensorsProblemIndividual individual) {
		//SensorsFieldData fieldData = ((SensorsProblemData) getProblemData()).getSensorsFieldProblemData();
		//float thresHold = (float) = Math.sqrt(3f*fieldData.getTransmissorRangeRatio());
		for(Location loc1 : individual.getTransmissorsPositions()) {
			if(loc1==null) continue;
			for(Location loc2 : individual.getTransmissorsPositions()) {
				if(loc2==null) continue;
				if(!loc1.equals(loc2) && euclideanDistance(loc1, loc2)<getThreshold()) {
					return new PairOfLocationsTuple(loc1, loc2);
				}
			}
		}
		return null;
	}
	
/*	private boolean arrayContainsPair(ArrayList<PairOfLocationsTuple> pairOfLocationsArray, PairOfLocationsTuple locPair1) {
		for(PairOfLocationsTuple locPair2 : pairOfLocationsArray) {
			if(locPair1.equals(locPair2)) return true;
		}
		return false;
	}*/
	
	private float euclideanDistance(Location loc1, Location loc2) {
		return (float) Math.sqrt(Math.pow(loc1.getPosX()-loc2.getPosX(), 2)+Math.pow(loc1.getPosY()-loc2.getPosY(), 2));
	}
	
	private void removeLocation(SensorsProblemIndividual individual, Location oldLoc) {
		for(int i=0; i<individual.getTransmissorsPositions().length; i++) {
			Location loc = individual.getTransmissorsPositions()[i];
			if(loc != null && loc.equals(oldLoc)) {
				individual.getTransmissorsPositions()[i] = null;
				individual.getAllele()[i] = 0;
				i++;
			}
		}
	}
	
//	public int[][] getIntersectionGrid(Location loc1, Location loc2){
//		CircularRatioObjectiveFunction objFunc = (CircularRatioObjectiveFunction) getProblemData().getObjFunc();
//		SensorsFieldData fieldData = ((SensorsProblemData) getProblemData()).getSensorsFieldProblemData();
//		int[] allele = {1};
//		Location[] l = {loc1};
//		int[][] coverageGrid1 = objFunc.getCoverageGrid(new SensorsProblemIndividual(allele, 0, l, fieldData));
//		l[0] = loc2;
//		int[][] coverageGrid2 = objFunc.getCoverageGrid(new SensorsProblemIndividual(allele, 0, l, fieldData));
//		int[][] intersectionGrid = objFunc.initializeGrid();
//		for(int i=0; i<coverageGrid1.length; i++) {
//			for(int j=0; j<coverageGrid1[0].length; j++) {
//				if(coverageGrid1[i][j]==1 && coverageGrid2[i][j]==1) {
//					intersectionGrid[i][j] = 1;
//				}
//			}
//		}
//		return intersectionGrid;
//	}
	
	public boolean intersectionExists(Location loc1, Location loc2){
		CircularRatioObjectiveFunction objFunc = (CircularRatioObjectiveFunction) getProblemData().getObjFunc();
		SensorsFieldData fieldData = ((SensorsProblemData) getProblemData()).getSensorsFieldProblemData();
		int[] allele = {1};
		Location[] l = {loc1};
		int[][] coverageGrid1 = objFunc.getCoverageGrid(new SensorsProblemIndividual(allele, 0, l, fieldData));
		l[0] = loc2;
		int[][] coverageGrid2 = objFunc.getCoverageGrid(new SensorsProblemIndividual(allele, 0, l, fieldData));
		//int[][] intersectionGrid = objFunc.initializeGrid();
		for(int i=0; i<coverageGrid1.length; i++) {
			for(int j=0; j<coverageGrid1[0].length; j++) {
				if(coverageGrid1[i][j]==1 && coverageGrid2[i][j]==1) {
					return true;
				}
			}
		}
		return false;
	}
	
	protected Location betweenTwoPointsLocation(Location loc1, Location loc2) {
		if( euclideanDistance(loc1, loc2) < 2 ) return null; //La grilla es discreta, tiene que haber una separacion de al menos un casillero entre dos puntos
		int xDisplacement = Math.round((loc1.getPosX()-loc2.getPosX())/2);
		int yDisplacement = Math.round((loc1.getPosY()-loc2.getPosY())/2);
		int x = loc2.getPosX() + xDisplacement;
		int y = loc2.getPosY() + yDisplacement;
		return new Location(x,y);
	}
	
	protected boolean isEmpty(int[][] grid) {
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j]==1) return false;
			}
		}
		return true;
	}
}
