package test;

import individuals.SensorsProblemIndividual;
import objectiveFunctions.CircularRatioObjectiveFunction;
import others.Location;
import problemData.SensorsFieldData;

public class Test {
	public static void main(String args[]) {
		double start = System.nanoTime();
		int range = 10;
		int alleleLength = 9;
		int numberOfIndividuals = 2;
		SensorsFieldData areaData = new SensorsFieldData(range, 60, 60);
		CircularRatioObjectiveFunction objFunc = new CircularRatioObjectiveFunction(areaData, 2);
		String dir = "/home/hernan/git/SIA/pruebona";
		
		int[] allele = { 1,1,1,1,1,1,1,1,1 };
		Location[] positions = {
				new Location(10,10),
				new Location(10,30),
				new Location(10,50),
				new Location(30,10),
				new Location(30,30),
				new Location(30,50),
				new Location(50,10),
				new Location(50,30),
				new Location(50,50),
		};
		SensorsProblemIndividual ind = new SensorsProblemIndividual(allele, 0, positions, areaData);
		double fitness = objFunc.getFitness(ind);
		System.out.println(fitness);
		
		double end = (System.nanoTime() - start)/Math.pow(10, 7);
		System.out.println("end = "+end);
		
		//CsvWriter.writeSolution(dir, "solution", individual);
		//ArrayList<SensorsProblemIndividual> individuals = new ArrayList<SensorsProblemIndividual>();
		//for(int i=0; i<numberOfIndividuals; i++) individuals.add(new SensorsProblemIndividual(alleleLength, areaData));
				
	}
}