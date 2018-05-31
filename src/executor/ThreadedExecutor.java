package executor;

import java.util.ArrayList;

import fileWriters.CsvWriter;
import geneticAlgorithms.CanonicalGA;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import others.Population;

public class ThreadedExecutor extends Thread{
	SensorsProblemRunConfiguration runConf;
	String outputDir;

	public ThreadedExecutor(SensorsProblemRunConfiguration runConf, String outputDir) {
		super();
		this.runConf = runConf;
		this.outputDir = outputDir;
	}

	public void run() {
		ArrayList<Individual> bestIndividuals = new ArrayList<>();
		for(int i=0; i<runConf.getNumExecutions(); i++) {
			bestIndividuals.add(sensorsCoverage(runConf));
		}		
		runConf.setBestIndividualsAfterRun(new Population(bestIndividuals, runConf.getSensorsProblemData()));
		SensorsProblemIndividual bestFitIndividual = runConf.getBestFitIndividual();
		runConf.setBestFitIndividual(bestFitIndividual);
		String filename = String.valueOf(runConf.getCrossoverProbability())+"-"+runConf.getCrossoverOperatorName()+"-"+runConf.getMaxGen()+".csv";
		//CsvWriter.writeRunConfigurationInfo(outputDir, filename, runConf);
		CsvWriter.writeSolution(outputDir, filename, bestFitIndividual);
		System.out.println("Hilo terminado");
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
}
