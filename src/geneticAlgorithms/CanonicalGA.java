package geneticAlgorithms;

import java.util.ArrayList;
import java.util.Random;

import individuals.BinaryRepresentationIndividual;
import individuals.Individual;
import objectiveFunctions.ObjectiveFunction;
import operatorsModels.Operator;
import others.Population;
import problemData.ProblemData;

public class CanonicalGA{
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private int maxGen; //2000 numero màximo de generaciones
	private float crossoverProbability;
	private float mutationProbability;
	private Operator selectionOperator;
	private Operator crossoverOperator;
	private Operator mutationOperator;
	private Operator replacementOperator;
	private ProblemData problemData;
	
	public CanonicalGA(int alleleLength, int popSolutionNumber, int maxGen,
			float crossoverProbability, float mutationProbability, 
			Operator selectionOperator,	Operator crossoverOperator, Operator mutationOperator, Operator replacementOperator, 
			ProblemData data) {
		super();
		this.popSolutionNumber = popSolutionNumber;
		this.maxGen = maxGen;
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = mutationProbability;
		this.selectionOperator = selectionOperator;
		this.crossoverOperator = crossoverOperator;
		this.mutationOperator = mutationOperator;
		this.replacementOperator = replacementOperator;
		this.problemData = data;
	}

	public Individual execute(Boolean tracing) {
		Population replacedPopulation = new Population(getPopSolutionNumber(), problemData);
		ObjectiveFunction objFunc = problemData.getObjFunc();
		int genNumber = 0;
		double bestFitness = 0;
		
		do{
			Population selectedParentsPopulation = applySelectionStrategy(replacedPopulation);
			Population offspringPopulation = applyReproductionStrategy(selectedParentsPopulation);
			replacedPopulation = applyReplacementStrategy(replacedPopulation, offspringPopulation);
			bestFitness = objFunc.getPopulationBestFitIndividual(replacedPopulation).getFitness();
			if(tracing) {
				System.out.println("Generación nro "+genNumber);
				objFunc.printPopulationStatisticInfo(replacedPopulation, problemData.getMaxFit());
			}
			genNumber++;
		}
		while( genNumber<getMaxGen() && bestFitness<problemData.getMaxFit() );
		Individual bestIndividual = objFunc.getPopulationBestFitIndividual(replacedPopulation);
		if(tracing) {
			System.out.println("Numero de iteraciones necesarias= "+genNumber);
			System.out.println("Mejor individuo");
			((BinaryRepresentationIndividual)bestIndividual).printData();
			System.out.println("\n");
		}
		return bestIndividual;
	}
	
	private Population applySelectionStrategy(Population population) {
		population = population.copy();
		ArrayList<Individual> selectedIndividuals = new ArrayList<>();
		for(int i=0; i<population.getNumberOfIndividuals(); i++) {
			ArrayList<Individual> selectedInd =  selectionOperator.operate(population.getIndividuals());
			selectedIndividuals.add(selectedInd.get(0));
		}
		return new Population(selectedIndividuals, problemData);
	}
	
	
	private Population applyReproductionStrategy(Population population) {
		population = population.copy();
		ArrayList<Individual> offSprings = new ArrayList<>();
		Random rand = new Random();
		while(population.getNumberOfIndividuals()>0) {
			ArrayList<Individual> individuals = new ArrayList<>();//Individuals son padres, terminan siendo hijos
			for(int i=0; i<2; i++)
				individuals.add(population.getIndividuals().remove(0));
			float p = rand.nextFloat();
			if(p<=crossoverProbability && individuals.size()==2) crossoverOperator.operate(individuals);
			if(p<=mutationProbability) {
				individuals.add(0, individuals.remove(1));
				mutationOperator.operate(individuals);
			}
			offSprings.addAll(individuals);
		}
		return new Population(offSprings, problemData);
	}
	
	private Population applyReplacementStrategy(Population population1, Population population2) {
		Population nextPop = population1.copy();
		nextPop.getIndividuals().addAll(population2.copy().getIndividuals());
		return new Population(replacementOperator.operate(nextPop.getIndividuals()), problemData);
	}

	public int getPopSolutionNumber() {
		return popSolutionNumber;
	}

	public int getMaxGen() {
		return maxGen;
	}

	public float getCrossoverProbability() {
		return crossoverProbability;
	}

	public float getMutationProbability() {
		return mutationProbability;
	}

	public Operator getSelectionOperator() {
		return selectionOperator;
	}

	public Operator getCrossoverOperator() {
		return crossoverOperator;
	}

	public Operator getMutationOperator() {
		return mutationOperator;
	}

	public Operator getReplacementOperator() {
		return replacementOperator;
	}

	public ProblemData getData() {
		return problemData;
	}

	public void setPopSolutionNumber(int popSolutionNumber) {
		this.popSolutionNumber = popSolutionNumber;
	}

	public void setMaxGen(int maxGen) {
		this.maxGen = maxGen;
	}

	public void setCrossoverProbability(float crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}

	public void setMutationProbability(float mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public void setSelectionOperator(Operator selectionOperator) {
		this.selectionOperator = selectionOperator;
	}

	public void setCrossoverOperator(Operator crossoverOperator) {
		this.crossoverOperator = crossoverOperator;
	}

	public void setMutationOperator(Operator mutationOperator) {
		this.mutationOperator = mutationOperator;
	}

	public void setReplacementOperator(Operator replacementOperator) {
		this.replacementOperator = replacementOperator;
	}

	public void setProblemData(ProblemData data) {
		this.problemData = data;
	}

	public ProblemData getProblemData() {
		return problemData;
	}
}
