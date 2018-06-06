package geneticAlgorithms;

import java.util.ArrayList;
import java.util.Random;

import individuals.BinaryRepresentationIndividual;
import individuals.Individual;
import objectiveFunctions.ObjectiveFunction;
import operatorsModels.CrossoverOperator;
import operatorsModels.MutationOperator;
import operatorsModels.Operator;
import operatorsModels.ReplacementOperator;
import operatorsModels.SelectionOperator;
import others.Population;
import problemData.ProblemData;
import utils.TwoIndividualsTuple;

public class CanonicalGA{
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private int maxGen; //2000 numero màximo de generaciones
	private float crossoverProbability;
	private float mutationProbability;
	private SelectionOperator selectionOperator;
	private CrossoverOperator crossoverOperator;
	private MutationOperator mutationOperator;
	private ReplacementOperator replacementOperator;
	private ProblemData problemData;
	
	public CanonicalGA(int alleleLength, int popSolutionNumber, int maxGen,
			float crossoverProbability, float mutationProbability, 
			SelectionOperator selectionOperator,	CrossoverOperator crossoverOperator, MutationOperator mutationOperator, ReplacementOperator replacementOperator, 
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
			Individual individual1 = population.getIndividuals().remove(0);
			Individual individual2 = population.getIndividuals().remove(0);
			new TwoIndividualsTuple(individual1, individual2);
			float p = rand.nextFloat();
			if(p<=crossoverProbability) 
				crossoverOperator.operate(individual1, individual2);
			if(p<=mutationProbability) {
				mutationOperator.operate(individual1);
				mutationOperator.operate(individual2);
			}
			offSprings.add(individual1);
			offSprings.add(individual2);
		}
		return new Population(offSprings, problemData);
	}
	
	private Population applyReplacementStrategy(Population oldPop, Population newPop) {
		oldPop = oldPop.copy();
		newPop = newPop.copy();		
		Population replacedPop = replacementOperator.operate(oldPop, newPop);
		return new Population(replacedPop.getIndividuals(), problemData);
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

	public void setSelectionOperator(SelectionOperator selectionOperator) {
		this.selectionOperator = selectionOperator;
	}

	public void setCrossoverOperator(CrossoverOperator crossoverOperator) {
		this.crossoverOperator = crossoverOperator;
	}

	public void setMutationOperator(MutationOperator mutationOperator) {
		this.mutationOperator = mutationOperator;
	}

	public void setReplacementOperator(ReplacementOperator replacementOperator) {
		this.replacementOperator = replacementOperator;
	}

	public void setProblemData(ProblemData data) {
		this.problemData = data;
	}

	public ProblemData getProblemData() {
		return problemData;
	}
}
