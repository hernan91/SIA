package other;

import generics.Population;
import operators.SensorsProblemMutationOperator;
import operators.SensorsProblemOnePointCrossoverOperator;
import sensorsProblem.SensorsFieldData;
import sensorsProblem.SensorsProblemIndividual;
import sensorsProblem.SensorsProblemPopulation;
import sensorsProblem.SquareRatioObjectiveFunction;

public class Test2 {
	public static void main(String args[]) {
		int range = 5;
		int alleleLength = 4;
		int numberOfIndividuals = 2;
		SensorsFieldData areaData = new SensorsFieldData(range, 20, 20);
		SquareRatioObjectiveFunction objFunc = new SquareRatioObjectiveFunction(areaData, 2);
		String dir = "/home/hernan/git/SIA/pruebona";
		
		
		Population pop1 = new SensorsProblemPopulation(alleleLength, numberOfIndividuals, areaData);
//		for(int i=0; i<numberOfIndividuals; i++) {
//			new SensorsProblemIndividual(alleleLength, areaData);
//		}
		
	
		objFunc.sortPopulationByFitness(pop1);
		objFunc.evaluatePopulation(pop1);
		pop1.printPop();
		CsvWriter.writeIndividuals(dir+"/pop1", pop1);
		
		Population pop2 = pop1.copy();
		pop2.setIndividuals(pop1.copy().getIndividuals());
		SensorsProblemOnePointCrossoverOperator crossOp = new SensorsProblemOnePointCrossoverOperator();
		crossOp.operate(pop2.getIndividuals());
		objFunc.evaluatePopulation(pop2);
		pop2.printPop();
		CsvWriter.writeIndividuals(dir+"/pop2", pop2);
		
		Population pop3 = pop1.copy();
		SensorsProblemMutationOperator mutOp = new SensorsProblemMutationOperator();
		mutOp.operate(pop3.getIndividuals());
		objFunc.evaluatePopulation(pop3);
		pop3.printPop();
		CsvWriter.writeIndividuals(dir+"/pop3", pop3);
		
		//CsvWriter.writeSolution(dir, "solution", individual);
		//ArrayList<SensorsProblemIndividual> individuals = new ArrayList<SensorsProblemIndividual>();
		//for(int i=0; i<numberOfIndividuals; i++) individuals.add(new SensorsProblemIndividual(alleleLength, areaData));
				
	}
}