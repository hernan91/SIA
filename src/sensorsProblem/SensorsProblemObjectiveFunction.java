package sensorsProblem;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import generics.ObjectiveFunction;
import generics.Population;

public abstract class SensorsProblemObjectiveFunction<T extends SensorsProblemIndividual> extends ObjectiveFunction<T> {
	private SensorsFieldData conf;
	private float alfa;
	
	
	public SensorsProblemObjectiveFunction(SensorsFieldData conf, float alfa) {
		super();
		this.setConf(conf);
		this.alfa = alfa;
	}
	
	public abstract int[][] getCoverageGrid(T individual);
	
	protected int[][] initializeGrid(){
		int grid[][] = new int[getConf().getGridSizeX()][getConf().getGridSizeY()];
		for(int i=0; i<getConf().getGridSizeX(); i++) {
			for(int j=0; j<getConf().getGridSizeY(); j++) {
				grid[i][j] = 0;
			}
		}
		return grid;
	}
	
	private double calcFitness(int coverageGrid[][], int usedTransmissors, float alfa) {
		float coverRange = (100*getCoveredPoints(coverageGrid)) /getConf().getGridSize();
		if(coverRange==0) return 0;
		return Math.pow(coverRange, alfa) / usedTransmissors;
		/**
		 * El problema con alfa=1 cuando se usa un solo transmisor, entonces queda (11**1)/1 = 11.11
		 * El fitness es el mismo que cuando se usan 9, queda (100**1)/9=11.11
		 * Alfa siempre > 1
		 */
	}
	
	private int getCoveredPoints(int coverageGrid[][]) {
		int coveredPoints = 0;
		for(int i=0; i<getConf().getGridSizeX(); i++) {
			for(int j=0; j<getConf().getGridSizeY(); j++) {
				coveredPoints += coverageGrid[i][j];
			}
		}
		return coveredPoints;
	}
	
	public SensorsFieldData getConf() {
		return conf;
	}

	public void setConf(SensorsFieldData conf) {
		this.conf = conf;
	}
	

	public double getFitness(T individual) {;
		int[][] coverageGrid =  getCoverageGrid(individual);
		int usedTransmissors = 0;
		for(int bit : individual.getAllele()) usedTransmissors += bit; 
		return calcFitness(coverageGrid, usedTransmissors, alfa);
	}

	public void evaluatePopulation(Population<T> population) {
		Iterator<T> it = population.getIndividuals().iterator();
		while(it.hasNext()) {
			T ind = it.next();
			ind.setFitness(getFitness(ind));
		}
	}

	@Override
	public void sortPopulationByFitness(Population<T> population) {
		evaluatePopulation(population);
		Collections.sort(population.getIndividuals(), new Comparator<T>(){
			@Override
			public int compare(T ind1, T ind2) {
				return ind1.compareTo(ind2);
			}	
		});
		Collections.reverse(population.getIndividuals());
	}
	
	@Override
	public T getPopulationBestFitIndividual(Population<T> population) {
		sortPopulationByFitness(population);
		return population.getIndividuals().get(0);
	}
	
	@Override
	public T getPopulationWorstFitIndividual(Population<T> population) {
		sortPopulationByFitness(population);
		return population.getIndividuals().get(0);
	}
	
	@Override
	public double getPopulationFitnessMean(Population<T> population) {
		evaluatePopulation(population);
		Iterator<T> it = population.getIndividuals().iterator();
		double summatory = 0; 
		while(it.hasNext()) summatory += it.next().getFitness();
		return summatory/population.getNumberOfIndividuals();
	}
	
	@Override
	public double getPopulationFitnessStandardDeviation(Population<T> population) {
		Iterator<T> it = population.getIndividuals().iterator();
		float sumatoriaCuad = 0;
		while(it.hasNext()) sumatoriaCuad += Math.pow(it.next().getFitness()-getPopulationFitnessMean(population), 2);
		return Math.sqrt(sumatoriaCuad/population.getNumberOfIndividuals());
	}
	
	@Override
	public void printPopulationStatisticInfo(Population<T> population, double optimalScore) {
		T best = getPopulationBestFitIndividual(population);
		double bestFitnessScore = best.getFitness();
		double mean = getPopulationFitnessMean(population);
		Iterator<T> it = population.getIndividuals().iterator();
		float sumatoriaCuad = 0;
		while(it.hasNext()) sumatoriaCuad += Math.pow(it.next().getFitness()-mean, 2);
		DecimalFormat format = new DecimalFormat("#.###");
		String str = "Mejor solucion:\n"
				+ "Fitness= "+format.format(bestFitnessScore)+"\n"
				+ "Error porcentual Ebest= "+format.format(((bestFitnessScore-optimalScore)/optimalScore)*100)+"%\n"
				+ "Media poblacional Epop= "+format.format(mean)+"\n"
				+ "Desviacion estandar= "+format.format(Math.sqrt(sumatoriaCuad/population.getNumberOfIndividuals()))+"\n"
				+ ""+"\n";
		System.out.println(str);
	}
}

	
