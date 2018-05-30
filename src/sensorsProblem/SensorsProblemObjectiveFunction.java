package sensorsProblem;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import generics.Individual;
import generics.ObjectiveFunction;
import generics.Population;
import generics.ProblemData;

public abstract class SensorsProblemObjectiveFunction extends ObjectiveFunction {
	
	public SensorsProblemObjectiveFunction(SensorsProblemData problemData) {
		super(problemData);
	}
	
	public abstract int[][] getCoverageGrid(Individual individual);
	
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
		for(int i=0; i<getProblemData().getGridSizeX(); i++) {
			for(int j=0; j<getConf().getGridSizeY(); j++) {
				coveredPoints += coverageGrid[i][j];
			}
		}
		return coveredPoints;
	}
	
	@Override
	public double getFitness(Individual ind) {
		SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
		int[][] coverageGrid =  getCoverageGrid(individual);
		int usedTransmissors = 0;
		for(int bit : individual.getAllele()) usedTransmissors += bit; 
		return calcFitness(coverageGrid, usedTransmissors, getProblemData().getAlfa());
	}

	public void evaluatePopulation(Population<Individual> population) {
		Iterator<Individual> it = population.getIndividuals().iterator();
		while(it.hasNext()) {
			Individual ind = it.next();
			ind.setFitness(getFitness(ind));
		}
	}

	@Override
	public void sortPopulationByFitness(Population<Individual> population) {
		evaluatePopulation(population);
		Collections.sort(population.getIndividuals(), new Comparator<Individual>(){
			@Override
			public int compare(Individual ind1, Individual ind2) {
				return ind1.compareTo(ind2, getProblemData().getObjFunc());
			}	
		});
		Collections.reverse(population.getIndividuals());
	}
	
	@Override
	public SensorsProblemIndividual getPopulationBestFitIndividual(Population<Individual> population) {
		sortPopulationByFitness(population);
		return (SensorsProblemIndividual) population.getIndividuals().get(0);
	}
	
	@Override
	public SensorsProblemIndividual getPopulationWorstFitIndividual(Population<Individual> population) {
		sortPopulationByFitness(population);
		return (SensorsProblemIndividual) population.getIndividuals().get(0);
	}
	
	@Override
	public double getPopulationFitnessMean(Population<Individual> population) {
		evaluatePopulation(population);
		Iterator<Individual> it = population.getIndividuals().iterator();
		double summatory = 0; 
		while(it.hasNext()) summatory += it.next().getFitness();
		return summatory/population.getNumberOfIndividuals();
	}
	
	@Override
	public double getPopulationFitnessStandardDeviation(Population<Individual> population) {
		Iterator<Individual> it = population.getIndividuals().iterator();
		float sumatoriaCuad = 0;
		while(it.hasNext()) sumatoriaCuad += Math.pow(it.next().getFitness()-getPopulationFitnessMean(population), 2);
		return Math.sqrt(sumatoriaCuad/population.getNumberOfIndividuals());
	}
	
	@Override
	public void printPopulationStatisticInfo(Population<Individual> population, double optimalScore) {
		Individual best = getPopulationBestFitIndividual(population);
		double bestFitnessScore = best.getFitness();
		double mean = getPopulationFitnessMean(population);
		Iterator<Individual> it = population.getIndividuals().iterator();
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

	
