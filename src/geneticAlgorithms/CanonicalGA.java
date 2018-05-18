package geneticAlgorithms;
//probar usar polimorfismo con binaryTournament y esas yerbas para hacer mas reusable el codigo mas adelante

import java.util.ArrayList;
import java.util.Random;
import generics.Individual;
import generics.Population;
import operators.Operator;

public class CanonicalGA {
	private int alleleLength; //longitud del alelo
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private int maxGen; //2000 numero màximo de generaciones
	private float crossoverProbability = 0.5f;
	private float mutationProbability = 0.01f;
	private Operator selectionOperator;
	private Operator crossoverOperator;
	private Operator mutationOperator;
	private Operator replacementOperator;
	private ProblemData data;
	
	public CanonicalGA(int alleleLength, int popSolutionNumber, int maxGen,
			float crossoverProbability, float mutationProbability, 
			Operator selectionOperator,	Operator crossoverOperator, Operator mutationOperator, Operator replacementOperator, 
			ProblemData data) {
		super();
		this.alleleLength = alleleLength;
		this.popSolutionNumber = popSolutionNumber;
		this.maxGen = maxGen;
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = mutationProbability;
		this.selectionOperator = selectionOperator;
		this.crossoverOperator = crossoverOperator;
		this.mutationOperator = mutationOperator;
		this.replacementOperator = replacementOperator;
		this.data = data;
	}

	public Individual execute(Boolean tracing) {
		int genNumber = 0;
		double bestFitness = 0;
		Population replacedPopulation = new Population(getAlleleLength(), getPopSolutionNumber());
		do{
			Population selectedParentsPopulation = applySelectionStrategy(replacedPopulation);
			Population offspringPopulation = applyReproductionStrategy(selectedParentsPopulation);
			replacedPopulation = applyReplacementStrategy(replacedPopulation, offspringPopulation);
			bestFitness = replacedPopulation.getBestFitIndividual(data.getObjFunc()).getFitness();
			if(tracing) replacedPopulation.printStatisticInfo(data.getMaxFit(), data.getObjFunc());
			genNumber++;
		}
		while( genNumber<getMaxGen() && bestFitness<data.getMaxFit() );
		replacedPopulation.printStatisticInfo(data.getMaxFit(), data.getObjFunc());
		System.out.println("Numero de iteraciones necesarias= "+genNumber);
		Individual bestIndividual = replacedPopulation.getBestFitIndividual(data.getObjFunc());
		bestIndividual.printAllele();
		System.out.println("Fitness = " + replacedPopulation.getBestFitIndividual(data.getObjFunc()).getFitness());
		return bestIndividual;
		
	}
	
	private Population applySelectionStrategy(Population population) {
		population = population.copy();
		ArrayList<Individual> selectedIndividuals = new ArrayList<Individual>();
		for(int i=0; i<population.getNumberOfIndividuals(); i++) {
			ArrayList<Individual> selectedInd = selectionOperator.operate(population.getIndividuals());
			selectedIndividuals.add(selectedInd.get(0));
		}
		return new Population(selectedIndividuals);
	}
	
	
	private Population applyReproductionStrategy(Population population) {
		population = population.copy();
		ArrayList<Individual> offSprings = new ArrayList<Individual>();
		Random rand = new Random();
		while(population.getNumberOfIndividuals()>0) {
			ArrayList<Individual> individuals = new ArrayList<Individual>();//Individuals son padres, terminan siendo hijos
			for(int i=0; i<2; i++)
				individuals.add(population.getIndividuals().remove(0));
			float p = rand.nextFloat();
			if(p<=crossoverProbability && individuals.size()==2) crossoverOperator.operate(individuals);
			if(p<=mutationProbability) {
				mutationOperator.operate(individuals);
				individuals.set(0, individuals.get(1));
				mutationOperator.operate(individuals);
			}
			offSprings.addAll(individuals);
		}
		return new Population(offSprings);
	}
	
	private Population applyReplacementStrategy(Population population1, Population population2) {
		Population nextPop = population1.copy();
		nextPop.getIndividuals().addAll(population2.copy().getIndividuals());
		return new Population(replacementOperator.operate(nextPop.getIndividuals()));
	}

	public int getAlleleLength() {
		return alleleLength;
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
		return data;
	}

	public void setAlleleLength(int alleleLength) {
		this.alleleLength = alleleLength;
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

	public void setData(ProblemData data) {
		this.data = data;
	}
	
	
}
