package executor;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fileWriters.CsvWriter;
import fileWriters.POIWriter;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import objectiveFunctions.CircularRatioObjectiveFunction;
import objectiveFunctions.ObjectiveFunction;
import operators.BinaryTournamentSelectionOperator;
import operators.SensorsProblemMutationOperator;
import operators.SensorsProblemOnePointCrossoverOperator;
import operators.SensorsProblemPercentageReplacementOperator;
import operatorsModels.CrossoverOperator;
import operatorsModels.MutationOperator;
import operatorsModels.ReplacementOperator;
import operatorsModels.SelectionOperator;
import others.Location;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;

public class SensorsCoverageTestInstanceExecutor{
	private static final int MAX_NUM_HILOS = 8;
	private static boolean tracing = false;
	static String outputDir = "/home/hernan/git/SIA/pruebaE1";
	private static String filename = "CircularRadioTest";
	
	static int sensorRatio = 10;
	static int gridSizeX = 60;
	static int gridSizeY = 60;
	static SensorsFieldData sensorsFieldData = new SensorsFieldData(sensorRatio, gridSizeX, gridSizeY);
	static Location[] prefixedLocations = {};
	
	private static int alleleLength = 60;
	private static int[] numExecutions = {30}; //30
	private static int[] maxGens = {200}; //100,500
	private static int[] popSolutionNumbers = {100};
	private static float[] crossoverProbabilities = {0.4f, 0.5f, 0.6f,0.7f, 0.8f};
	private static float[] mutationProbabilities = {0.1f, 0.3f, 0.5f, 0.7f, 0.9f}; // para que la poblacion sea 1/popSOoutionNumber, ingresar un negativo
	private static int alfa = 2; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private static ObjectiveFunction objectiveFunction = new CircularRatioObjectiveFunction(sensorsFieldData, alfa);
	//new SensorsProblemSquareRatioObjectiveFunction(sensorsFieldData, getTransmissorsPositions(), alfa)
	private static double maxFit = 684.694f; //1111,111111111 cuadrado  684,694444444 para 9 sensores circulares radio 10
	private static Individual individual = new SensorsProblemIndividual(alleleLength, sensorsFieldData);
	
	private static SensorsProblemData sensorsProblemData = new SensorsProblemData(maxFit, alfa, objectiveFunction, individual, prefixedLocations);
	private static SelectionOperator[] selectionOperators = { new BinaryTournamentSelectionOperator(sensorsProblemData) };
	private static CrossoverOperator[] crossoverOperators = {
			new SensorsProblemOnePointCrossoverOperator(sensorsProblemData)};
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static MutationOperator[] mutationOperators = {
			new SensorsProblemMutationOperator(sensorsProblemData)};
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static ReplacementOperator[] replacementOperators = {new SensorsProblemPercentageReplacementOperator(sensorsProblemData)};
	
	public static void main(String[] args) {
		double startTime = System.nanoTime();
		ArrayList<SensorsProblemRunConfiguration> runConfigurations = getRunConfigurations();
		ExecutorService executor = Executors.newFixedThreadPool(MAX_NUM_HILOS);
		int r = 1;
		for(SensorsProblemRunConfiguration runConf : runConfigurations) {
			ThreadedExecutor thread = new ThreadedExecutor(runConf, outputDir, r);
			executor.execute(thread);
			r++;
		}
		executor.shutdown();
        while (!executor.isTerminated()) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        System.out.println("Terminan todos los hilos de ejecución");
    
		POIWriter.writeSensorsData(outputDir, filename, runConfigurations);
		POIWriter.writeRelevantData(outputDir, "ResumenDatos", runConfigurations);
		CsvWriter.writeSensorsIndividual(outputDir, runConfigurations);
		double runTime = ((System.nanoTime() - startTime)/Math.pow(10, 9))/60;
		System.out.println("Tiempo de ejecución: "+runTime+"min");
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
