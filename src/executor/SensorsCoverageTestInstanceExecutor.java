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
import operatorsModels.CrossoverOperator;
import operatorsModels.MutationOperator;
import operatorsModels.ReplacementOperator;
import operatorsModels.SelectionOperator;
import others.Location;
import problemData.ProblemData;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;

public class SensorsCoverageTestInstanceExecutor{
	private static final int MAX_NUM_HILOS = 8;
	private static boolean tracing = false;
	private static String filename = "K3";
	static String outputDir = "/home/hernan/git/SIA/pruebas/"+filename;
	
	static int sensorRatio = 10;
	static int gridSizeX = 60;
	static int gridSizeY = 60;
	static SensorsFieldData sensorsFieldData = new SensorsFieldData(sensorRatio, gridSizeX, gridSizeY);
	static Location[] prefixedLocations = {};
	
	private static int alleleLength = 60; //60
	private static int[] numExecutions = {30}; //30
	private static int[] maxGens = {100}; //100,500
	private static int[] popSolutionNumbers = {100}; //Solo pares
	private static float[] crossoverProbabilities = {0.6f, 0.7f, 0.8f};//{0.1f, 0.3f, 0.5f, 0.7f, 0.9f, 1};
	private static float[] mutationProbabilities = {0.8f, 0.9f, 1};//{0.7f, 0.8f}; // para que la poblacion sea 1/popSOoutionNumber, ingresar un negativo
	private static float[] takenFromNewGenProportions = {0.3f, 0.4f, 0.5f, 0.6f, 0.7f};//0.3f, 0.9f}; //0.1f, 0.3f, 0.5f, 0.7f, 0.9f, 1
	private static int alfa = 2; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private static ObjectiveFunction objectiveFunction = new CircularRatioObjectiveFunction(sensorsFieldData, alfa);
	//new SensorsProblemSquareRatioObjectiveFunction(sensorsFieldData, getTransmissorsPositions(), alfa)
	private static double maxFit = 676f; //1111,111111111 cuadrado  684,694444444/676 para 9 sensores circulares radio 10
	private static Individual individual = new SensorsProblemIndividual(alleleLength, sensorsFieldData);
	
	private static String[] selectionOperatorsClassname = {"BinaryTournamentSelectionOperator"};//new BinaryTournamentSelectionOperator(sensorsProblemData) 
	private static String[] crossoverOperatorsClassname = {"SensorsProblemTwoPointCrossoverOperator"};//SensorsProblemTwoPointCrossoverOperator(sensorsProblemData)
			//new SensorsProblemOnePointCrossoverOperator(sensorsProblemData),
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static String[] mutationOperatorsClassname = {"SensorsProblemMutationOperator"}; //new SensorsProblemMutationOperator(sensorsProblemData)
//	private static Operator[] crossoverOperators = {
//			new OnePointCrossoverOperator(),
//			new TwoPointCrossoverOperator(),
//			new ThreePointCrossoverOperator()};
	private static String[] replacementOperatorsClassname = {"SensorsProblemProportionReplacementOperator"}; //new SensorsProblemProportionReplacementOperator(sensorsProblemData)
			//new SensorsProblemSimpleReplacementWithoutRepeatedInd(sensorsProblemData)
	
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
    
		POIWriter.writeSensorsData(outputDir, "TodosLosDatos", runConfigurations);
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
					for(int l=0; l<mutationOperatorsClassname.length; l++) {
						for(int m=0; m<replacementOperatorsClassname.length; m++) {
							for(int n=0; n<maxGens.length; n++) {
								for(int o=0; o<crossoverProbabilities.length; o++) {
									for(int p=0; p<mutationProbabilities.length; p++) {
										for(int q=0; q<takenFromNewGenProportions.length; q++) {
											for(int r=0; r<popSolutionNumbers.length; r++) {
												//for(int p=0; p<maxFit.length; p++) {
												SensorsProblemData sensorsProblemData = new SensorsProblemData(maxFit, alfa, 
														takenFromNewGenProportions[q], objectiveFunction, individual, prefixedLocations);
												runConfigurations.add(new SensorsProblemRunConfiguration(
														numExecutions[i], 
														(SelectionOperator)getInstance(selectionOperatorsClassname[j], sensorsProblemData), 
														(CrossoverOperator)getInstance(crossoverOperatorsClassname[k], sensorsProblemData), 
														(MutationOperator)getInstance(mutationOperatorsClassname[l], sensorsProblemData), 
														(ReplacementOperator)getInstance(replacementOperatorsClassname[m], sensorsProblemData),
														maxGens[n], 
														crossoverProbabilities[o], 
														mutationProbabilities[p],/*objectiveFunctions[n],*/
														popSolutionNumbers[r], 
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
		return runConfigurations;
		//for(int n=0; n<objectiveFunctions.length; n++) {}
	}
	
	private static Object getInstance(String operatorClassname, SensorsProblemData sensorsProblemData) {		
		try {
			Class<?> clazz = Class.forName("operators."+operatorClassname);
			Constructor<?> ctor = clazz.getConstructor(ProblemData.class);
			return ctor.newInstance(new Object[] { sensorsProblemData });
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | 
				IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}
	
	public static SensorsProblemIndividual getBestIndividual(ArrayList<SensorsProblemRunConfiguration> confs) {
		SensorsProblemIndividual bestInd = new SensorsProblemIndividual(new int[0], 0, new Location[0], confs.get(0).getSensorsProblemData().getSensorsFieldProblemData());
		for(SensorsProblemRunConfiguration runConfiguration: confs) {
			SensorsProblemIndividual currentBest = runConfiguration.getBestFitIndividual();
			if(currentBest.getFitness()>bestInd.getFitness()) {
				bestInd = currentBest;
			}
		}
		return bestInd;
	}
}
