package geneticAlgorithms;
//probar usar polimorfismo con binaryTournament y esas yerbas para hacer mas reusable el codigo mas adelante

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.apache.poi.ss.formula.functions.T;

import generics.BinaryRepresentationIndividual;
import generics.ObjectiveFunction;
import generics.Population;
import generics.ProblemData;
import operators.Operator;

public class CanonicalGA <T extends BinaryRepresentationIndividual>{
	private int alleleLength; //longitud del alelo
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private int maxGen; //2000 numero màximo de generaciones
	private float crossoverProbability;
	private float mutationProbability;
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

	public T execute(Boolean tracing) {
		int genNumber = 0;
		double bestFitness = 0;
		ObjectiveFunction<T> objFunc = data.getObjFunc();
		Population<T> replacedPopulation = new Population<T>(getAlleleLength(), getPopSolutionNumber());
		do{
			Population<T> selectedParentsPopulation = applySelectionStrategy(replacedPopulation);
			Population<T> offspringPopulation = applyReproductionStrategy(selectedParentsPopulation);
			replacedPopulation = applyReplacementStrategy(replacedPopulation, offspringPopulation);
			bestFitness = objFunc.getPopulationBestFitIndividual((Population<T>) replacedPopulation).getFitness();
			if(tracing) objFunc.printPopulationStatisticInfo((Population<T>) replacedPopulation, data.getMaxFit());
			genNumber++;
		}
		while( genNumber<getMaxGen() && bestFitness<data.getMaxFit() );
		T bestIndividual = (T) objFunc.getPopulationBestFitIndividual(replacedPopulation);
		if(tracing) {
			System.out.println("Numero de iteraciones necesarias= "+genNumber);
			bestIndividual.printAllele();
			System.out.println("Fitness = " + objFunc.getPopulationBestFitIndividual(replacedPopulation).getFitness());
		}
		return bestIndividual;
	}
	
	private Population<T> applySelectionStrategy(Population<T> population) {
		population = population.copy();
		ArrayList<T> selectedIndividuals = new ArrayList<>();
		for(int i=0; i<population.getNumberOfIndividuals(); i++) {
			ArrayList<T> selectedInd =  population.getIndividuals();
			selectedIndividuals.add(selectedInd.get(0));
		}
		return new Population<T>(selectedIndividuals);
	}
	
	
	private Population<T> applyReproductionStrategy(Population<T> population) {
		population = population.copy();
		ArrayList<T> offSprings = new ArrayList<>();
		Random rand = new Random();
		while(population.getNumberOfIndividuals()>0) {
			ArrayList<BinaryRepresentationIndividual> individuals = new ArrayList<>();//Individuals son padres, terminan siendo hijos
			for(int i=0; i<2; i++)
				individuals.add(population.getIndividuals().remove(0));
			float p = rand.nextFloat();
			if(p<=crossoverProbability && individuals.size()==2) crossoverOperator.operate(individuals);
			if(p<=mutationProbability) {
				mutationOperator.operate(individuals);
				individuals.set(0, individuals.get(1));
				mutationOperator.operate(individuals);
			}
			offSprings.addAll( (Collection<? extends T>) individuals);
		}
		return new Population<T>(offSprings);
	}
	
	private Population<T> applyReplacementStrategy(
			Population<T> population1, Population<T> population2) {
		Population<T> nextPop = population1.copy();
		nextPop.getIndividuals().addAll(population2.copy().getIndividuals());
		return new Population<T>( (ArrayList<T>) replacementOperator.operate((ArrayList<BinaryRepresentationIndividual>) nextPop.getIndividuals()));
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
