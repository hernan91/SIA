package executor;

import java.util.ArrayList;
import java.util.Random;

import generics.BinaryRepresentationIndividual;
import generics.Location;
import generics.ObjectiveFunction;
import generics.Population;
import geneticAlgorithms.CanonicalGA;
import operators.BinaryTournamentSelectionOperator;
import operators.BitFlipMutationOperator;
import operators.Operator;
import operators.ReplacementOperator;
import operators.SensorsProblemMutationOperator;
import operators.SensorsProblemOnePointCrossoverOperator;
import other.CsvWriter;
import other.POIWriter;
import sensorsProblem.CircularRatioObjectiveFunction;
import sensorsProblem.SensorsFieldData;
import sensorsProblem.SensorsProblemData;
import sensorsProblem.SensorsProblemIndividual;
import sensorsProblem.SensorsProblemObjectiveFunction;
import utils.IndividualsListCreator;

public class SensorsCoverageTestInstanceExecutor{
	static int sensorRatio = 10;
	static int gridSizeX = 60;
	static int gridSizeY = 60;
	static int randomlyDistributedTransmissors = 0;
	static int[] prefixedLocations = {10,10 , 10,30 , 10,50 , 30,10 , 30,30 , 30,50 , 50,10 , 50,30 , 50,50 , 23,38 , 1,11 , 5,26 , 38,56 , 34,50,
			18,36, 48,14, 8,1, 57,27, 18,56, 49,44, 2,28, 49,51, 47,44, 21,4, 9,25, 6,42, 3,0, 50,31, 31,41, 11,47, 20,15, 30,22,
			42,25, 48,36, 36,47, 18,45, 3,58, 29,59, 58,59, 42,4, 56,37, 57,52, 8,17, 19,23, 24,31, 38,12, 34,36, 41,37, 58,3,
			53,19, 2,54, 12,60, 37,11, 54,14, 44,19, 31,3, 48,57, 8,36, 46,59, 25,37};
	static String outputDir = "/home/darkside/git/SIA/instanciaPruebaRadioCuadrado1";
	static SensorsFieldData searchSpaceProblemData = new SensorsFieldData(sensorRatio, gridSizeX, gridSizeY);
	
	private Class individualClass = SensorsProblemIndividual.class;
	private static int[] numExecutions = {30};
	private static Operator[] crossoverOperators = {
			new SensorsProblemOnePointCrossoverOperator()};
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static Operator[] mutationOperators = {
			new SensorsProblemMutationOperator()};
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static int[] maxGens = {100,500};
	private static float[] crossoverProbabilities = {0.8f, 0.9f, 1.0f};
	private static float[] mutationProbability; //1/popSOoutionNumber
	private static int alfa = 2; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private static SensorsProblemObjectiveFunction<SensorsProblemIndividual>[] objectiveFunctions = {
			new CircularRatioObjectiveFunction(searchSpaceProblemData, alfa)};
	//new SensorsProblemSquareRatioObjectiveFunction(searchSpaceProblemData, getTransmissorsPositions(), alfa)
	private static int[] popSolutionNumbers = {100}; //numero de soluciones de la poblacion
	private static double[] maxFit = {99999f}; //1111.11
	private static boolean tracing = false;
	
	private static String filename = "SquareRadioTest";
	
	

	public static void main(String[] args) {
		new SensorsCoverageTestInstanceExecutor<>();
		ArrayList<RunConfiguration<SensorsProblemIndividual>> runConfigurations = getRunConfigurations();
		int r = 0;
		for(RunConfiguration<SensorsProblemIndividual> runConf : runConfigurations) {
			ArrayList<SensorsProblemIndividual> bestIndividuals = new ArrayList<>();
			System.out.println("RunConf= "+r);
			for(int i=0; i<runConf.getNumExecutions(); i++) {
				bestIndividuals.add(sensorsCoverage(runConf));
			}
			r++;
			
			runConf.setBestIndividualsAfterRun(new Population<T>(IndividualsListCreator.));
			SensorsProblemIndividual bestFitIndividual = runConf.getBestFitIndividual(runConf.getObjectiveFunction());
			runConf.setBestFitIndividual(bestFitIndividual);
			String filename = String.valueOf(runConf.getCrossoverProbability())+"-"+runConf.getCrossoverOperatorName()+"-"+runConf.getMaxGen()+".csv";
			//CsvWriter.writeRunConfigurationInfo(outputDir, filename, runConf);
			CsvWriter.writeSolution(outputDir, filename, bestFitIndividual);
		}
		CsvWriter.writeLocations(outputDir, getTransmissorsPositions());
		POIWriter.writeData(outputDir, filename, runConfigurations);
		POIWriter.writeRelevantData(outputDir, "ResumenDatos", runConfigurations);
	}
	
	public T sensorsCoverage(RunConfiguration<T> conf) {
		ArrayList<Location> transmissorsPositions = getTransmissorsPositions();
		//addRandomDistributedSensors(conf.getRandomlyDistributedTransmissors(), conf.getGridSizeX(), conf.getGridSizeY(), transmissorsPositions);
		
		//int alleleLength = transmissorsPositions.size()+randomlyDistributedTransmissors;
		SensorsProblemObjectiveFunction<T> sensorsProblemObjectiveFunction = conf.getObjectiveFunction();
		SensorsProblemData sensorsCoverageOptimizationProblemData = new SensorsProblemData(
				conf.getMaxFit(), conf.getAlfa(), conf.getSearchSpaceProblemData(), sensorsProblemObjectiveFunction);
		
		Operator selectionOperator = new BinaryTournamentSelectionOperator(sensorsCoverageOptimizationProblemData.getObjFunc());
		Operator crossoverOperator = conf.getCrossoverOperator();
		Operator mutationOperator = new BitFlipMutationOperator();
		Operator replacementOperator = new ReplacementOperator(sensorsCoverageOptimizationProblemData.getObjFunc());
		
		CanonicalGA<T> ga = new CanonicalGA<>(transmissorsPositions.size(), conf.getPopSolutionNumber(), conf.getMaxGen(), conf.getCrossoverProbability(), conf.getMutationProbability(), 
				selectionOperator, crossoverOperator, mutationOperator, replacementOperator, sensorsCoverageOptimizationProblemData);
		T bestIndividual = ga.execute(conf.getTracing());
		return bestIndividual;
	}
	
	public ArrayList<RunConfiguration<SensorsProblemIndividual>> getRunConfigurations(){
		ArrayList<RunConfiguration<SensorsProblemIndividual>> runConfigurations = new ArrayList<>();
		for(int i=0; i<numExecutions.length; i++) {
			for(int j=0; j<crossoverOperators.length; j++) {
				for(int k=0; k<mutationOperators.length; k++) {
					for(int l=0; l<maxGens.length; l++) {
						for(int m=0; m<crossoverProbabilities.length; m++) {
							for(int n=0; n<objectiveFunctions.length; n++) {
								for(int o=0; o<popSolutionNumbers.length; o++) {
									for(int p=0; p<maxFit.length; p++) {
										runConfigurations.add(new RunConfiguration<SensorsProblemIndividual>(numExecutions[i], crossoverOperators[j], mutationOperators[k], maxGens[l],
												crossoverProbabilities[m], objectiveFunctions[n], popSolutionNumbers[o], alfa, maxFit[p], tracing, 
												searchSpaceProblemData, randomlyDistributedTransmissors, prefixedLocations));
									}
								}
							}
						}
					}
				}
			}
		}
		return runConfigurations;
	}
	
	public ArrayList<Location> getTransmissorsPositions(){
		ArrayList<Location> transmissorsPositions = new ArrayList<Location>();
		for(int i=0; i<prefixedLocations.length; i=i+2) transmissorsPositions.add(new Location(prefixedLocations[i], prefixedLocations[i+1]));
		return transmissorsPositions;
	}
}
