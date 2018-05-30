package test;

import java.util.ArrayList;

import fileWriters.CsvWriter;
import individuals.BinaryRepresentationIndividual;
import individuals.SensorsProblemIndividual;
import objectiveFunctions.CircularRatioObjectiveFunction;
import operators.SensorsProblemMutationOperator;
import operators.SensorsProblemOnePointCrossoverOperator;
import others.Population;
import problemData.SensorsFieldData;
import utils.IndividualsListCreator;

public class Test {
	public static void main(String args[]) {
		int range = 5;
		int alleleLength = 4;
		int numberOfIndividuals = 2;
		SensorsFieldData areaData = new SensorsFieldData(range, 20, 20);
		CircularRatioObjectiveFunction objFunc = new CircularRatioObjectiveFunction(areaData, 2);
		String dir = "/home/hernan/git/SIA/pruebona";
		
		Population<SensorsProblemIndividual> pop1 = 
				new Population<>(IndividualsListCreator.createSensorsProblemIndividualList(alleleLength, numberOfIndividuals, areaData));
		objFunc.evaluatePopulation((Population<BinaryRepresentationIndividual>)(Population)(pop1));
		pop1.printPop();
		CsvWriter.writeIndividuals(dir+"/pop1", pop1);
		
		Population<SensorsProblemIndividual> pop2 = pop1.copy();
		SensorsProblemOnePointCrossoverOperator crossOp = new SensorsProblemOnePointCrossoverOperator();
		crossOp.operate((ArrayList<BinaryRepresentationIndividual>)(ArrayList)pop2.getIndividuals());
		objFunc.evaluatePopulation((Population<BinaryRepresentationIndividual>)(Population)pop2);
		pop2.printPop();
		//CsvWriter.writeIndividuals(dir+"/pop2", pop2);
		
		Population<SensorsProblemIndividual> pop3 = pop1.copy();
		SensorsProblemMutationOperator mutOp = new SensorsProblemMutationOperator();
		for(BinaryRepresentationIndividual ind : pop3.getIndividuals()) {
			ArrayList<BinaryRepresentationIndividual> individual = new ArrayList<BinaryRepresentationIndividual>();
			individual.add(ind);
			mutOp.operate(individual);
		}
		objFunc.evaluatePopulation((Population<BinaryRepresentationIndividual>)(Population)(pop3));
		pop3.printPop();
		CsvWriter.writeIndividuals(dir+"/pop3", pop3);
		
		//CsvWriter.writeSolution(dir, "solution", individual);
		//ArrayList<SensorsProblemIndividual> individuals = new ArrayList<SensorsProblemIndividual>();
		//for(int i=0; i<numberOfIndividuals; i++) individuals.add(new SensorsProblemIndividual(alleleLength, areaData));
				
	}
}