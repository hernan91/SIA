package GeneticAlgorithms;
//probar usar polimorfismo con binaryTournament y esas yerbas para hacer mas reusable el codigo mas adelante

import generics.Population;
import strategies.ReplacementStrategy;
import strategies.ReproductionStrategy;
import strategies.SelectionStrategy;

public class CanonicalGA {
	private float crossoverProbability = 0.5f;
	private float mutationProbability = 0.01f;
	
	public void execute(GAProblemData data) {
		int genNumber = 0;
		double bestFitness = 0;
		
		
		
		Population replacedPopulation = new Population(data.getAlleleLength(), data.getPopSolutionNumber());
		SelectionStrategy selectionStrategy = new SelectionStrategy(); 
		ReproductionStrategy reproductionStrategy = new ReproductionStrategy(data.getCrossoverProbability(), data.getMutationProbability());
		ReplacementStrategy replacementStrategy = new ReplacementStrategy(data.getObjFunc());
		do{
			Population selectedParentsPopulation = selectionStrategy.selectWithBinaryTournament(replacedPopulation, data.getObjFunc());
			Population offspringPopulation = reproductionStrategy.crossover2pAndBitFlipMutation(selectedParentsPopulation);
			//Preguntar que hacer si n es impar en el caso de el crossover
			replacedPopulation = replacementStrategy.elitistSteadyStateReplacement(replacedPopulation, offspringPopulation);
			bestFitness = replacedPopulation.getBestFitIndividual(data.getObjFunc()).getFitness();
			replacedPopulation.printStatisticInfo(data.getMaxFit(), data.getObjFunc());
			genNumber++;
		}
		while( genNumber<data.getMaxGen() && bestFitness<data.getMaxFit() );
		System.out.println("Numero de iteraciones necesarias= "+genNumber);
	}
}
