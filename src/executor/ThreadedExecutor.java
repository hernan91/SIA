package executor;

import java.util.ArrayList;

import fileWriters.CsvWriter;
import geneticAlgorithms.CanonicalGA;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import others.Population;

public class ThreadedExecutor implements Runnable {
	private SensorsProblemRunConfiguration runConf;
	private String outputDir;
	private int number;

	public ThreadedExecutor(SensorsProblemRunConfiguration runConf, String outputDir, int number) {
		super();
		this.runConf = runConf;
		this.outputDir = outputDir;
		this.number = number;
	}

	public void run() {
		System.out.println("Hilo "+number+" comienza su ejecución");
		ArrayList<Individual> bestIndividuals = new ArrayList<>();
		for(int i=0; i<runConf.getNumExecutions(); i++) {
			if(SensorsCoverageTestInstanceExecutor.progressVerbosity>0)
				System.out.println("Hilo "+number+" %"+((float)i/runConf.getNumExecutions())*100);
			bestIndividuals.add(gaExecute(runConf));
		}		
		runConf.setBestIndividualsAfterRun(new Population(bestIndividuals, runConf.getSensorsProblemData()));
		SensorsProblemIndividual bestFitIndividual = runConf.getBestFitIndividual();
		runConf.setBestFitIndividual(bestFitIndividual);
		CsvWriter.writeRunConfigurationInfo(outputDir, runConf.getName(), runConf);
		//CsvWriter.writeSolution(outputDir, filename, bestFitIndividual);
		//CsvWriter.writeLocations(outputDir, getTransmissorsPositions());
		System.out.println("Hilo "+number+" termina su ejecución");
		System.out.println("");
	}
	
	public static Individual gaExecute(SensorsProblemRunConfiguration runConf) {		
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
