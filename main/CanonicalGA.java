package main;
//probar usar polimorfismo con binaryTournament y esas yerbas para hacer mas reusable el codigo mas adelante


public class CanonicalGA {
	
	public void executeWithOneMaxObjectiveF(GAProblemData data) {
		int genNumber = 0;
		double bestFitness = 0;
		OneMaxObjectiveFunction objectiveFunction = new OneMaxObjectiveFunction();
		BinaryTournamentSelector selectionStrategy = new BinaryTournamentSelector(objectiveFunction);
		Population replacedPopulation = new Population(data.getAlleleLength(), data.getPopSolutionNumber(), objectiveFunction);
		ReproductionStrategy reproductionStrategy = new ReproductionStrategy(data.getCrossoverProbability(), data.getMutationProbability());
		ReplacementStrategy replacementStrategy = new ReplacementStrategy();
		do{
			Population selectedParentsPopulation = selectionStrategy.selectIndividuals(replacedPopulation);
			Population offspringPopulation = reproductionStrategy.crossover2pAndBitFlipMutation(selectedParentsPopulation);
			replacedPopulation = replacementStrategy.elitistSteadyStateReplacement(replacedPopulation, offspringPopulation);
			genNumber++;
			bestFitness = replacedPopulation.getBestFitIndividual().getFitness();
			replacedPopulation.printStatisticInfo(data.getMaxFit());
		}
		while( genNumber<data.getMaxGen() && bestFitness<data.getMaxFit() );
		System.out.println("Best fit= "+bestFitness);
	}
	
	public void executeWithSensorsProblemObjectiveF(SensorsCoverageOptimizationProblemData data) {
		int genNumber = 0;
		double bestFitness = 0;
		SquareGridProblemData conf = new SquareGridProblemData(data.getSquareGridProblemData().getTransmissorRangeRatio(), 
				data.getSquareGridProblemData().getGridSizeX(), data.getSquareGridProblemData().getGridSizeY());
		SensorsProblemObjectiveFunction objectiveFunction = new SensorsProblemObjectiveFunction(data.getSquareGridProblemData(), 
				data.getTransmissorsPositions(), data.getAlfa());
		BinaryTournamentSelector selectionStrategy = new BinaryTournamentSelector(objectiveFunction);
		Population replacedPopulation = new Population(data.getAlleleLength(), data.getPopSolutionNumber(), objectiveFunction);
		ReproductionStrategy reproductionStrategy = new ReproductionStrategy(data.getCrossoverProbability(), data.getMutationProbability());
		ReplacementStrategy replacementStrategy = new ReplacementStrategy();
		do{
			Population selectedParentsPopulation = selectionStrategy.selectIndividuals(replacedPopulation);
			Population offspringPopulation = reproductionStrategy.crossover2pAndBitFlipMutation(selectedParentsPopulation);
			//Preguntar que hacer si n es impar en el caso de el crossover
			replacedPopulation = replacementStrategy.elitistSteadyStateReplacement(replacedPopulation, offspringPopulation);
			bestFitness = replacedPopulation.getBestFitIndividual().getFitness();
			replacedPopulation.printStatisticInfo(data.getMaxFit());
			genNumber++;
		}
		while( genNumber<data.getMaxGen() && bestFitness<data.getMaxFit() );
		System.out.println("Numero de iteraciones necesarias= "+genNumber);
	}
}
