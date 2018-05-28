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

public class SensorsCoverageTestInstanceExecutor{
	static int sensorRatio = 10;
	static int gridSizeX = 60;
	static int gridSizeY = 60;
	static String outputDir = "/home/darkside/git/SIA/instanciaPruebaRadioCuadrado1";
	static SensorsFieldData searchSpaceProblemData = new SensorsFieldData(sensorRatio, gridSizeX, gridSizeY);
	static Location[] prefixedLocations = {};
	
	private static int alleleLength = 10;
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
	private static ObjectiveFunction[] objectiveFunctions = {
			new CircularRatioObjectiveFunction<SensorsProblemIndividual>(searchSpaceProblemData, alfa)};
	//new SensorsProblemSquareRatioObjectiveFunction(searchSpaceProblemData, getTransmissorsPositions(), alfa)
	private static int[] popSolutionNumbers = {100}; //numero de soluciones de la poblacion
	private static double[] maxFit = {99999f}; //1111.11
	private static boolean tracing = false;
	
	private static String filename = "SquareRadioTest";
	
	
	public static void main(String[] args) {
		ArrayList<RunConfiguration<SensorsProblemIndividual>> runConfigurations = getRunConfigurations();
		int r = 0;
		for(RunConfiguration<SensorsProblemIndividual> runConf : runConfigurations) {
			ArrayList<SensorsProblemIndividual> bestIndividuals = new ArrayList<>();
			System.out.println("RunConf= "+r);
			for(int i=0; i<runConf.getNumExecutions(); i++) {
				bestIndividuals.add(sensorsCoverage(runConf));
			}
			r++;
			
			runConf.setBestIndividualsAfterRun(new Population<SensorsProblemIndividual>(bestIndividuals));
			SensorsProblemIndividual bestFitIndividual = runConf.getBestFitIndividual(runConf.getObjectiveFunction());
			runConf.setBestFitIndividual(bestFitIndividual);
			String filename = String.valueOf(runConf.getCrossoverProbability())+"-"+runConf.getCrossoverOperatorName()+"-"+runConf.getMaxGen()+".csv";
			//CsvWriter.writeRunConfigurationInfo(outputDir, filename, runConf);
			CsvWriter.writeSolution(outputDir, filename, bestFitIndividual);
		}
		//CsvWriter.writeLocations(outputDir, getTransmissorsPositions());
		//POIWriter.writeData(outputDir, filename, runConfigurations);
		//POIWriter.writeRelevantData(outputDir, "ResumenDatos", runConfigurations);
	}
	
	public static SensorsProblemIndividual sensorsCoverage(RunConfiguration<SensorsProblemIndividual> conf) {
		//ArrayList<Location> transmissorsPositions = getTransmissorsPositions();
		//addRandomDistributedSensors(conf.getRandomlyDistributedTransmissors(), conf.getGridSizeX(), conf.getGridSizeY(), transmissorsPositions);
		
		//int alleleLength = transmissorsPositions.size()+randomlyDistributedTransmissors;
		SensorsProblemObjectiveFunction<SensorsProblemIndividual> sensorsProblemObjectiveFunction = (SensorsProblemObjectiveFunction<SensorsProblemIndividual>) conf.getObjectiveFunction();
		SensorsProblemData sensorsCoverageOptimizationProblemData = new SensorsProblemData(
				conf.getMaxFit(), conf.getAlfa(), conf.getSearchSpaceProblemData(), sensorsProblemObjectiveFunction);
		
		Operator selectionOperator = new BinaryTournamentSelectionOperator(sensorsCoverageOptimizationProblemData.getObjFunc());
		Operator crossoverOperator = conf.getCrossoverOperator();
		Operator mutationOperator = new BitFlipMutationOperator();
		Operator replacementOperator = new ReplacementOperator(sensorsCoverageOptimizationProblemData.getObjFunc());
		
		CanonicalGA<SensorsProblemIndividual> ga = new CanonicalGA<>(conf.getAlleleLength(), conf.getPopSolutionNumber(), conf.getMaxGen(), conf.getCrossoverProbability(), conf.getMutationProbability(), 
				selectionOperator, crossoverOperator, mutationOperator, replacementOperator, sensorsCoverageOptimizationProblemData);
		SensorsProblemIndividual bestIndividual = ga.execute(conf.getTracing());
		return bestIndividual;
	}
	
	public static ArrayList<RunConfiguration<SensorsProblemIndividual>> getRunConfigurations(){
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
												searchSpaceProblemData, prefixedLocations, alleleLength));
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
}
