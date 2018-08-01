//Para calcular la interferencia ver "a sensor deployment aprroach" Wen-HWa, cuarta carilla, al final
package executor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fileWriters.CsvWriter;
import fileWriters.POIWriter;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import objectiveFunctions.CircularRatioObjectiveFunction;
import objectiveFunctions.ObjectiveFunction;
import objectiveFunctions.SquareRatioObjectiveFunction;
import operatorsModels.CrossoverOperator;
import operatorsModels.MutationOperator;
import operatorsModels.ReplacementOperator;
import operatorsModels.SelectionOperator;
import operatorsModels.TranslocationOperator;
import others.Location;
import problemData.ProblemData;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;

public class SensorsCoverageTestInstanceExecutor{
	private static int MAX_NUM_THREADS = 7;
	private static boolean tracing = true;
	public static int progressVerbosity = 0;
	private static String filename = "prueDefINS2";
	static String outputDir = "/home/hernan/git/SIA/pruebasInforme"+"/"+filename;
	
	static int sensorRatio = 10; //23
	static int gridSizeX = 60; //60
	static int gridSizeY = 60;
	static SensorsFieldData sensorsFieldData = new SensorsFieldData(sensorRatio, gridSizeX, gridSizeY);
	static Location[] prefixedLocations = {

	};
	
	private static int alleleLength = 18; //80
	private static int[] numExecutions = {30}; //30
	private static int[] maxGens = {100}; //100,500
	private static int[] popSolutionNumbers = {100}; //Solo pares
	private static float[] crossoverProbabilities = {1};// OP=0.7 {0.1f, 0.3f, 0.5f, 0.7f, 0.9f, 1};
	private static float[] translocationOpThrershold = {-1};//(float) Math.sqrt(3*sensorRatio)/2
	private static float[] mutationProbabilities = {1};// OP=0.9 {0.7f, 0.8f}; // para que la poblacion sea 1/popSOoutionNumber, ingresar un negativo
	private static float[] takenFromNewGenProportions = {0.05f};// OP=0.6 {0.3f, 0.9f}; //0.1f, 0.3f, 0.5f, 0.7f, 0.9f, 1
	private static int alfa = 2; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private static ObjectiveFunction objectiveFunction = new CircularRatioObjectiveFunction(sensorsFieldData, alfa);
	//new SensorsProblemSquareRatioObjectiveFunction(sensorsFieldData, getTransmissorsPositions(), alfa)
	private static double maxFit = 1111.111111111f; //1111,111111111 cuadrado  684,694444444/   676 para 9 sensores circulares radio 10
	private static Individual individual = new SensorsProblemIndividual(alleleLength, sensorsFieldData);
	
	private static String[] selectionOperatorsClassname = {"RouletteWheelSelectionOperator"};//"BinaryTournamentSelectionOperator", "RouletteWheelSelectionOperator" 
	private static String[] crossoverOperatorsClassname = {"RectangularGeographicCrossoverOperator"};//"SensorsProblemSimpleTwoPointCrossoverOperator", "RectangularGeographicCrossoverOperator"
	private static String[] translocationOperatorClassname = {null}; //"PacoTranslocationOperator"};
			//new SensorsProblemOnePointCrossoverOperator(sensorsProblemData),
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static String[] mutationOperatorsClassname = {"SingleVortexNeighborhoodMutationOperator"}; //"RandomMutationOperator", "SingleVortexNeighborhoodMutationOperator"
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static String[] replacementOperatorsClassname = {"ElitistReplacementOperator"}; //"ElitistReplacementOperator", "SensorsProblemProportionReplacementOperator"
			//new SensorsProblemSimpleReplacementWithoutRepeatedInd(sensorsProblemData)
	
	public static void main(String[] args) {
		if(args.length>0 && args[0]!=null) {
			outputDir = args[0]+"/"+filename;
			if(args[1]!=null) MAX_NUM_THREADS = Integer.valueOf(args[1]);
		}
		double startTime = System.nanoTime();
		ArrayList<SensorsProblemRunConfiguration> runConfigurations = getRunConfigurations();
		ExecutorService executor = Executors.newFixedThreadPool(MAX_NUM_THREADS);
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
    
		//POIWriter.writeSensorsData(outputDir, "TodosLosDatos", runConfigurations);
		POIWriter.writeRelevantData(outputDir, "ResumenDatos", runConfigurations);
		SensorsProblemIndividual bestInd = SensorsCoverageTestInstanceExecutor.getBestIndividual(runConfigurations);
		CsvWriter.writeSensorsIndividualData(outputDir, "bestIndData", bestInd, 
				runConfigurations.get(0).getObjectiveFunction()); //Puede que la funcion objetivo ingresada no sea la utilizada
		CsvWriter.writeSensorsLocations(outputDir, "bestIndLocations", bestInd.getTransmissorsPositions());
		double runTime = ((System.nanoTime() - startTime)/Math.pow(10, 9))/60;
		System.out.println("Tiempo de ejecución: "+runTime+"min");
	}
	
	public static ArrayList<SensorsProblemRunConfiguration> getRunConfigurations(){
		ArrayList<SensorsProblemRunConfiguration> runConfigurations = new ArrayList<>();
		for(int i=0; i<numExecutions.length; i++) {
			for(int j=0; j<selectionOperatorsClassname.length; j++) {
				for(int k=0; k<crossoverOperatorsClassname.length; k++) {
					for(int l=0; l<translocationOperatorClassname.length; l++) {
						for(int m=0; m<mutationOperatorsClassname.length; m++) {
							for(int n=0; n<replacementOperatorsClassname.length; n++) {
								for(int o=0; o<maxGens.length; o++) {
									for(int p=0; p<crossoverProbabilities.length; p++) {
										for(int q=0; q<translocationOpThrershold.length; q++) {
											for(int r=0; r<mutationProbabilities.length; r++) {
												for(int s=0; s<takenFromNewGenProportions.length; s++) {
													for(int t=0; t<popSolutionNumbers.length; t++) {
														//for(int p=0; p<maxFit.length; p++) {
														SensorsProblemData sensorsProblemData = new SensorsProblemData(maxFit, alfa, 
																takenFromNewGenProportions[s], objectiveFunction, individual, prefixedLocations, maxGens[o]);
														TranslocationOperator transOp = null;
														if(translocationOperatorClassname[l]!=null) {
															transOp = (TranslocationOperator)getInstance(translocationOperatorClassname[l], sensorsProblemData, translocationOpThrershold[q]);
														}
														runConfigurations.add(new SensorsProblemRunConfiguration(
																numExecutions[i], 
																(SelectionOperator)getInstance(selectionOperatorsClassname[j], sensorsProblemData, null), 
																(CrossoverOperator)getInstance(crossoverOperatorsClassname[k], sensorsProblemData, crossoverProbabilities[p]), 
																transOp,
																(MutationOperator)getInstance(mutationOperatorsClassname[m], sensorsProblemData, mutationProbabilities[r]),
																(ReplacementOperator)getInstance(replacementOperatorsClassname[n], sensorsProblemData, null),
																maxGens[o], 
																crossoverProbabilities[p],
																translocationOpThrershold[q],
																mutationProbabilities[r],/*objectiveFunctions[n],*/
																popSolutionNumbers[t],
																tracing,
																sensorsProblemData
																)
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
				}
			}
		}
		return runConfigurations;
		//for(int n=0; n<objectiveFunctions.length; n++) {}
	}
	
	private static Object getInstance(String operatorClassname, SensorsProblemData sensorsProblemData, Float probability) {
		try {
			String packish = "";
			if(operatorClassname.endsWith("SelectionOperator")) packish = "selectionOperators";
			if(operatorClassname.endsWith("CrossoverOperator")) packish = "crossoverOperators";
			if(operatorClassname.endsWith("TranslocationOperator")) packish = "translocationOperators";
			if(operatorClassname.endsWith("MutationOperator")) packish = "mutationOperators";
			if(operatorClassname.endsWith("ReplacementOperator")) packish = "replacementOperators";
			Class<?> clazz = Class.forName(packish+"."+operatorClassname);
			if(probability==null) {
				Constructor<?> ctor = clazz.getConstructor(ProblemData.class);
				return ctor.newInstance(new Object[] { sensorsProblemData });
			}
			else {
				Constructor<?> ctor = clazz.getConstructor(ProblemData.class, float.class);
				return ctor.newInstance(new Object[] { sensorsProblemData, probability.floatValue() });
			}
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | 
				IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}
	
	public static SensorsProblemIndividual getBestIndividual(ArrayList<SensorsProblemRunConfiguration> confs) {
		SensorsProblemIndividual bestInd = new SensorsProblemIndividual(new int[0], 0, new Location[0], SensorsCoverageTestInstanceExecutor.sensorsFieldData);
		for(SensorsProblemRunConfiguration runConfiguration: confs) {
			SensorsProblemIndividual currentBest = runConfiguration.getBestFitIndividual();
			if(currentBest.getFitness()>bestInd.getFitness()) {
				bestInd = currentBest;
			}
		}
		return bestInd;
	}
}
