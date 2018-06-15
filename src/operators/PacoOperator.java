package operators;

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
public class PacoOperator extends TranslocationOperator{

	public PacoOperator(ProblemData problemData, float threshold) {
		super(problemData, threshold);
	}
	
	public Population operate(Population population) {
		ArrayList<Individual> offSprings = new ArrayList<>();
		for(Individual ind : population.getIndividuals()) {
			SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
			PairOfLocationsTuple tooClosePairs = getPairOfCloseNodes(individual);
			while(tooClosePairs!=null){
				Location newLoc = translocate(tooClosePairs.getLoc1(), tooClosePairs.getLoc2());
				if(newLoc!=null) {
					Location oldLocation = tooClosePairs.getLoc1();
					oldLocation = newLoc;
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
		int[][] intersectionGrid = getIntersectionGrid(loc1, loc2);
		if(!isEmpty(intersectionGrid)) {
			return betweenTwoPointsLocation(loc1, loc2);
		}
		return null;
	}
	
	private ArrayList<PairOfLocationsTuple> getPairsOfCloseNodes(SensorsProblemIndividual individual) {
		SensorsFieldData fieldData = ((SensorsProblemData) getProblemData()).getSensorsFieldProblemData();
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
	}
	
	private PairOfLocationsTuple getPairOfCloseNodes(SensorsProblemIndividual individual) {
		SensorsFieldData fieldData = ((SensorsProblemData) getProblemData()).getSensorsFieldProblemData();
		//float thresHold = (float) = Math.sqrt(3f*fieldData.getTransmissorRangeRatio());
		for(Location loc1 : individual.getTransmissorsPositions()) {
			for(Location loc2 : individual.getTransmissorsPositions()) {
				if(!loc1.equals(loc2) && euclideanDistance(loc1, loc2)<getThreshold()) {
					return new PairOfLocationsTuple(loc1, loc2);
				}
			}
		}
		return null;
	}
	
	private boolean arrayContainsPair(ArrayList<PairOfLocationsTuple> pairOfLocationsArray, PairOfLocationsTuple locPair1) {
		for(PairOfLocationsTuple locPair2 : pairOfLocationsArray) {
			if(locPair1.equals(locPair2)) return true;
		}
		return false;
	}
	
	private float euclideanDistance(Location loc1, Location loc2) {
		return (float) Math.sqrt(Math.pow(loc1.getPosX()-loc2.getPosX(), 2)+Math.pow(loc1.getPosY()-loc2.getPosY(), 2));
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
	
	public int[][] getIntersectionGrid(Location loc1, Location loc2){
		CircularRatioObjectiveFunction objFunc = (CircularRatioObjectiveFunction) getProblemData().getObjFunc();
		SensorsFieldData fieldData = ((SensorsProblemData) getProblemData()).getSensorsFieldProblemData();
		int[] allele = {1};
		Location[] l = {loc1};
		int[][] coverageGrid1 = objFunc.getCoverageGrid(new SensorsProblemIndividual(allele, 0, l, fieldData));
		l[0] = loc2;
		int[][] coverageGrid2 = objFunc.getCoverageGrid(new SensorsProblemIndividual(allele, 0, l, fieldData));
		int[][] intersectionGrid = objFunc.initializeGrid();
		for(int i=0; i<coverageGrid1.length; i++) {
			for(int j=0; j<coverageGrid1[0].length; j++) {
				if(coverageGrid1[i][j]==1 && coverageGrid2[i][j]==1) {
					intersectionGrid[i][j] = 1;
				}
			}
		}
		return intersectionGrid;
	}
	
	protected Location betweenTwoPointsLocation(Location loc1, Location loc2) {
		int xDisplacement = (loc1.getPosX()-loc2.getPosX())/2;
		int yDisplacement = (loc1.getPosY()-loc2.getPosY())/2;
		int x = loc1.getPosX() + xDisplacement;
		int y = loc1.getPosX() + yDisplacement;
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
