package test;

import java.util.ArrayList;

import fileWriters.CsvWriter;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import objectiveFunctions.CircularRatioObjectiveFunction;
import others.Location;
import others.Population;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;
import translocationOperators.PacoTranslocationOperator;

public class Test2 {
	public static void main(String args[]) {
//		int popSize = 6;
//		int alfa = 2;
//		int alelleLength = 9;
//		SensorsFieldData sfData = new SensorsFieldData(10, 60, 60);
//		Location[] prefixedPositions = {};
//		SensorsProblemIndividual ind = new SensorsProblemIndividual(alelleLength, sfData);
//		int[] alleleInd = {1,1,1,1,1,1,1,1,1};
//		Location[] trans = {new Location(10,10), new Location(10,30), new Location(10,50),
//				new Location(30,10), new Location(30,30), new Location(30,50),
//				new Location(50,10), new Location(50,30), new Location(50,50)};
//		CircularRatioObjectiveFunction objFunc = new CircularRatioObjectiveFunction(sfData, alfa);
//		SensorsProblemIndividual ind1 = new SensorsProblemIndividual(alleleInd, 0, trans, sfData);
//		
//		SensorsProblemData spData = new SensorsProblemData(99999, alfa, 0, objFunc, ind, prefixedPositions);
//		Population oldGeneration = new Population(popSize-2, spData);
//		oldGeneration.getIndividuals().add(ind1);
//		oldGeneration.getIndividuals().add(ind1);
//		oldGeneration.printPop();
//		System.out.println();
//		Population newGeneration = new Population(popSize-2, spData);
//		newGeneration.getIndividuals().add(ind1);
//		newGeneration.getIndividuals().add(ind1);
//		newGeneration.printPop();
//		System.out.println();
//		
//		SensorsProblemSimpleReplacementWithoutRepeatedInd repOperator = new SensorsProblemSimpleReplacementWithoutRepeatedInd(spData);
//		Population replacedGen = repOperator.operate(oldGeneration, newGeneration);
//		replacedGen.printPop();
//		
//		//CsvWriter.writeSolution(dir, "solution", individual);
//		//ArrayList<SensorsProblemIndividual> individuals = new ArrayList<SensorsProblemIndividual>();
//		//for(int i=0; i<numberOfIndividuals; i++) individuals.add(new SensorsProblemIndividual(alleleLength, areaData));
//				
		String dir = "/home/hernan/git/SIA/pruebas/M1";
		int alfa = 2;
		int alelleLength = 4;
		int maxGen = 100;
		float threshold = 10*1f;
		Location[] prefixedPositions = {};
		SensorsFieldData sfData = new SensorsFieldData(10, 60, 60);
		CircularRatioObjectiveFunction objFunc = new CircularRatioObjectiveFunction(sfData, alfa);
		SensorsProblemIndividual ind = new SensorsProblemIndividual(alelleLength, sfData);
		SensorsProblemData spData = new SensorsProblemData(99999, alfa, 0, objFunc, ind, prefixedPositions, maxGen);
		PacoTranslocationOperator paco = new PacoTranslocationOperator(spData, threshold);
		ArrayList<Individual> individuals = new ArrayList<>();
		int[] allele = {1,1,1,1};
		Location[] loc = {new Location(10,10), new Location(15,15), new Location(20,20), new Location(25,25)};
		SensorsProblemIndividual spIndividual = new SensorsProblemIndividual(60, sfData);
		CsvWriter.writeSensorsLocations(dir, "pre", spIndividual.getTransmissorsPositions());
		individuals.add(spIndividual);
		Population population = new Population(individuals, spData);
		paco.operate(population);
		CsvWriter.writeSensorsLocations(dir, "post", spIndividual.getTransmissorsPositions());
	}
}