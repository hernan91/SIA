package geneticAlgorithms;
//probar usar polimorfismo con binaryTournament y esas yerbas para hacer mas reusable el codigo mas adelante

import java.util.ArrayList;
import java.util.Random;
import generics.Individual;
import generics.Population;
import operators.Operator;

public class CanonicalGA {
	private float crossoverProbability = 0.5f;
	private float mutationProbability = 0.01f;
	private Operator selectionOperator;
	private Operator crossoverOperator;
	private Operator mutationOperator;
	private Operator replacementOperator;
	private ProblemData data;
	
	public CanonicalGA(float crossoverProbability, float mutationProbability, Operator selectionOperator,
			Operator crossoverOperator, Operator mutationOperator, Operator replacementOperator, ProblemData data) {
		super();
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = mutationProbability;
		this.selectionOperator = selectionOperator;
		this.crossoverOperator = crossoverOperator;
		this.mutationOperator = mutationOperator;
		this.replacementOperator = replacementOperator;
		this.data = data;
	}

	public void execute() {
		int genNumber = 0;
		double bestFitness = 0;
		Population replacedPopulation = new Population(data.getAlleleLength(), data.getPopSolutionNumber());
		do{
			Population selectedParentsPopulation = applySelectionStrategy(replacedPopulation);
			Population offspringPopulation = applyReproductionStrategy(selectedParentsPopulation);
			replacedPopulation = applyReplacementStrategy(replacedPopulation, offspringPopulation);
			bestFitness = replacedPopulation.getBestFitIndividual(data.getObjFunc()).getFitness();
			replacedPopulation.printStatisticInfo(data.getMaxFit(), data.getObjFunc());
			genNumber++;
		}
		while( genNumber<data.getMaxGen() && bestFitness<data.getMaxFit() );
		System.out.println("Numero de iteraciones necesarias= "+genNumber);
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
}
