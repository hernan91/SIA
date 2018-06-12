package test;

import individuals.SensorsProblemIndividual;
import objectiveFunctions.CircularRatioObjectiveFunction;
import operators.SensorsProblemSimpleReplacementWithoutRepeatedInd;
import others.Location;
import others.Population;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;

public class Test2 {
	public static void main(String args[]) {
		int popSize = 6;
		int alfa = 2;
		int alelleLength = 9;
		SensorsFieldData sfData = new SensorsFieldData(10, 60, 60);
		Location[] prefixedPositions = {};
		SensorsProblemIndividual ind = new SensorsProblemIndividual(alelleLength, sfData);
		int[] alleleInd = {1,1,1,1,1,1,1,1,1};
		Location[] trans = {new Location(10,10), new Location(10,30), new Location(10,50),
				new Location(30,10), new Location(30,30), new Location(30,50),
				new Location(50,10), new Location(50,30), new Location(50,50)};
		CircularRatioObjectiveFunction objFunc = new CircularRatioObjectiveFunction(sfData, alfa);
		SensorsProblemIndividual ind1 = new SensorsProblemIndividual(alleleInd, 0, trans, sfData);
		
		SensorsProblemData spData = new SensorsProblemData(99999, alfa, 0, objFunc, ind, prefixedPositions);
		Population oldGeneration = new Population(popSize-2, spData);
		oldGeneration.getIndividuals().add(ind1);
		oldGeneration.getIndividuals().add(ind1);
		oldGeneration.printPop();
		System.out.println();
		Population newGeneration = new Population(popSize-2, spData);
		newGeneration.getIndividuals().add(ind1);
		newGeneration.getIndividuals().add(ind1);
		newGeneration.printPop();
		System.out.println();
		
		SensorsProblemSimpleReplacementWithoutRepeatedInd repOperator = new SensorsProblemSimpleReplacementWithoutRepeatedInd(spData);
		Population replacedGen = repOperator.operate(oldGeneration, newGeneration);
		replacedGen.printPop();
		
		//CsvWriter.writeSolution(dir, "solution", individual);
		//ArrayList<SensorsProblemIndividual> individuals = new ArrayList<SensorsProblemIndividual>();
		//for(int i=0; i<numberOfIndividuals; i++) individuals.add(new SensorsProblemIndividual(alleleLength, areaData));
				
	}
}