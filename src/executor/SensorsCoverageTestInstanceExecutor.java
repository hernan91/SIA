package executor;

import java.util.ArrayList;

import fileWriters.CsvWriter;
import geneticAlgorithms.CanonicalGA;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import objectiveFunctions.CircularRatioObjectiveFunction;
import objectiveFunctions.ObjectiveFunction;
import operators.BinaryTournamentSelectionOperator;
import operators.Operator;
import operators.ReplacementOperator;
import operators.SensorsProblemMutationOperator;
import operators.SensorsProblemOnePointCrossoverOperator;
import others.Location;
import others.Population;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;

public class SensorsCoverageTestInstanceExecutor{
	static int sensorRatio = 10;
	static int gridSizeX = 60;
	static int gridSizeY = 60;
	static SensorsFieldData sensorsFieldData = new SensorsFieldData(sensorRatio, gridSizeX, gridSizeY);
	static Location[] prefixedLocations = {};
	
	private static int alleleLength = 10;
	private static int[] numExecutions = {30};
	private static int[] maxGens = {100,500};
	private static int[] popSolutionNumbers = {6};
	private static float[] crossoverProbabilities = {0.8f, 0.9f, 1.0f};
	private static float[] mutationProbabilities = {-1}; // para que la poblacion sea 1/popSOoutionNumber, ingresar un negativo
	private static int alfa = 2; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private static ObjectiveFunction objectiveFunction = new CircularRatioObjectiveFunction(sensorsFieldData, alfa);
	//new SensorsProblemSquareRatioObjectiveFunction(sensorsFieldData, getTransmissorsPositions(), alfa)
	private static double maxFit = 99999f; //1111.11
	private static Individual individual = new SensorsProblemIndividual(alleleLength, sensorsFieldData);
	
	private static SensorsProblemData sensorsProblemData = new SensorsProblemData(maxFit, alfa, objectiveFunction, individual, prefixedLocations);
	private static Operator[] selectionOperators = { new BinaryTournamentSelectionOperator(sensorsProblemData) };
	private static Operator[] crossoverOperators = {
			new SensorsProblemOnePointCrossoverOperator(sensorsProblemData)};
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static Operator[] mutationOperators = {
			new SensorsProblemMutationOperator(sensorsProblemData)};
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static Operator[] replacementOperators = {new ReplacementOperator(sensorsProblemData)};
	private static boolean tracing = false;
	static String outputDir = "/home/hernan/git/SIA/pruebaA1";
	private static String filename = "CircularRadioTest";
	
	
	public static void main(String[] args) {
		ArrayList<SensorsProblemRunConfiguration> runConfigurations = getRunConfigurations();
		int r = 0;
		for(SensorsProblemRunConfiguration runConf : runConfigurations) {
			ArrayList<Individual> bestIndividuals = new ArrayList<>();
			System.out.println("RunConf= "+r);
			for(int i=0; i<runConf.getNumExecutions(); i++) {
				bestIndividuals.add(sensorsCoverage(runConf));
			}
			r++;
			
			runConf.setBestIndividualsAfterRun(new Population(bestIndividuals, sensorsProblemData));
			SensorsProblemIndividual bestFitIndividual = runConf.getBestFitIndividual();
			runConf.setBestFitIndividual(bestFitIndividual);
			String filename = String.valueOf(runConf.getCrossoverProbability())+"-"+runConf.getCrossoverOperatorName()+"-"+runConf.getMaxGen()+".csv";
			//CsvWriter.writeRunConfigurationInfo(outputDir, filename, runConf);
			CsvWriter.writeSolution(outputDir, filename, bestFitIndividual);
		}
		//CsvWriter.writeLocations(outputDir, getTransmissorsPositions());
		//POIWriter.writeData(outputDir, filename, runConfigurations);
		//POIWriter.writeRelevantData(outputDir, "ResumenDatos", runConfigurations);
	}
	
	public static Individual sensorsCoverage(SensorsProblemRunConfiguration runConf) {		
		CanonicalGA ga = new CanonicalGA(
				runConf.getAlleleLength(),
				runConf.getPopSolutionNumber(),
				runConf.getMaxGen(),
				runConf.getCrossoverProbability(),
				runConf.getMutationProbability(),
				runConf.getSelectionOperator(),
				runConf.getCrossoverOperator(),
				runConf.getMutationOperator(),
				runConf.getReplacementOperator(),
				runConf.getSensorsProblemData()
		);
		Individual bestIndividual = ga.execute(runConf.getTracing());
		return bestIndividual;
	}
	
	public static ArrayList<SensorsProblemRunConfiguration> getRunConfigurations(){
		ArrayList<SensorsProblemRunConfiguration> runConfigurations = new ArrayList<>();
		for(int i=0; i<numExecutions.length; i++) {
			for(int j=0; j<selectionOperators.length; j++) {
				for(int k=0; k<crossoverOperators.length; k++) {
					for(int l=0; l<mutationOperators.length; l++) {
						for(int m=0; m<replacementOperators.length; m++) {
							for(int n=0; n<maxGens.length; n++) {
								for(int o=0; o<crossoverProbabilities.length; o++) {
									for(int p=0; p<mutationProbabilities.length; p++) {
										for(int q=0; q<popSolutionNumbers.length; q++) {
											//for(int p=0; p<maxFit.length; p++) {
												runConfigurations.add(new SensorsProblemRunConfiguration(
														numExecutions[i], 
														selectionOperators[j], 
														crossoverOperators[k], 
														mutationOperators[l], 
														replacementOperators[m], 
														maxGens[n], 
														crossoverProbabilities[o], 
														mutationProbabilities[p],/*objectiveFunctions[n],*/ 
														popSolutionNumbers[q], 
														tracing, 
														sensorsProblemData)
												);
											//}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return runConfigurations;
		//for(int n=0; n<objectiveFunctions.length; n++) {}
	}
}
