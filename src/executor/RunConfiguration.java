package executor;

import java.util.StringTokenizer;
import generics.Individual;
import generics.ObjectiveFunction;
import generics.Population;
import operators.Operator;
import sensorsProblem.DeploymentAreaData;
import sensorsProblem.SensorsProblemObjectiveFunction;

public class RunConfiguration {
	private int numExecutions;
	private Operator crossoverOperator;
	private int maxGen;
	private float crossoverProbability;
	private float mutationProbability; //1/popSOoutionNumber
	private SensorsProblemObjectiveFunction objectiveFunction;
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private int alfa; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private double maxFit; //maximo fitness a encontrar hasta parar
	private boolean tracing;
	private int randomlyDistributedTransmissors;
	private DeploymentAreaData searchSpaceProblemData;
	private int[] arrayCoord;
	private Population bestIndividualsAfterRun;
	private Individual bestFitIndividual;
	
	public RunConfiguration(int numExecutions, Operator crossoverOperator, int maxGen, float crossoverProbability,
			SensorsProblemObjectiveFunction objectiveFunction, int popSolutionNumber, int alfa, double maxFit, boolean tracing, 
			DeploymentAreaData searchSpaceProblemData, int randomlyDistributedTransmissors,	int[] arrayCoord) {
		super();
		this.numExecutions = numExecutions;
		this.crossoverOperator = crossoverOperator;
		this.maxGen = maxGen;
		this.crossoverProbability = crossoverProbability;
		//this.mutationProbability = mutationProbability;
		this.mutationProbability = (1f/popSolutionNumber);
		this.objectiveFunction = objectiveFunction;
		this.popSolutionNumber = popSolutionNumber;
		this.alfa = alfa;
		this.maxFit = maxFit;
		this.tracing = tracing;
		this.searchSpaceProblemData = searchSpaceProblemData;
		this.randomlyDistributedTransmissors = randomlyDistributedTransmissors;
		this.arrayCoord = arrayCoord;
	}
	
	public String getInfo() {
		double mean = bestIndividualsAfterRun.getPopulationFitnessMean(objectiveFunction);
		return  "DATOS DE CORRIDA Y POBLACION\n"+
				"Media= "+ mean +"\n" +
				"Desvío estándar= "+ bestIndividualsAfterRun.getPopulationFitnessStandardDeviation(mean) +"\n" +
				"Fitness del mejor individuo= "+ bestFitIndividual.getFitness() +"\n" +
				"Cromosoma del mejor individuo= "+ bestFitIndividual.getAlleleString() +"\n" +
				"Numero de sensores utilizado= " + bestFitIndividual.getAllele().length +"\n" +
				"Numero de sensores aleatoriamente distribuidos= " + randomlyDistributedTransmissors +"\n"+
				"Numero de ejecuciones= "+ numExecutions +"\n" +
				"Operador de cruza= "+ getCrossoverOperatorName() +"\n" +
				"Numero de generaciones= "+ maxGen +"\n" +
				"Probabilidad de cruza= "+ crossoverProbability +"\n" +
				"Probabilidad de mutación= "+ mutationProbability +"\n" +
				"Función objetivo= "+ getObjectiveFunctionName() +"\n" +
				"Número de individuos de la población= "+ popSolutionNumber +"\n" +
				"Alfa= "+ alfa +"\n" +
				"Fitness óptimo= "+ maxFit +"\n" +
				"Radio de cobertura de los sensores= "+ searchSpaceProblemData.getTransmissorRangeRatio() +"\n" +
				"Tamaño del área de despliegue en X= "+ searchSpaceProblemData.getGridSizeX() +"\n" +
				"Tamaño del área de despliegue en Y= "+ searchSpaceProblemData.getGridSizeY() +"\n" +
	}
	
	public StringTokenizer getInfoTokenizer(){
		return new StringTokenizer(getInfo(), "\n");
	}

	public int getNumExecutions() {
		return numExecutions;
	}

	public void setNumExecutions(int numExecutions) {
		this.numExecutions = numExecutions;
	}

	public Operator getCrossoverOperator() {
		return crossoverOperator;
	}

	public String getCrossoverOperatorName() {
		String s = crossoverOperator.getClass().getName();
		return s.substring(s.indexOf(".")+1);
	}
	
	public void setCrossoverOperator(Operator crossoverOperator) {
		this.crossoverOperator = crossoverOperator;
	}

	public int getMaxGen() {
		return maxGen;
	}

	public void setMaxGen(int maxGen) {
		this.maxGen = maxGen;
	}

	public float getCrossoverProbability() {
		return crossoverProbability;
	}

	public void setCrossoverProbability(float crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}

	public float getMutationProbability() {
		return mutationProbability;
	}

	public void setMutationProbability(float mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public SensorsProblemObjectiveFunction getObjectiveFunction() {
		return objectiveFunction;
	}
	
	public String getObjectiveFunctionName() {
		String s = objectiveFunction.getClass().getName();
		return s.substring(s.indexOf(".")+1);
	}

	public void setObjectiveFunction(SensorsProblemObjectiveFunction objectiveFunction) {
		this.objectiveFunction = objectiveFunction;
	}

	public int getPopSolutionNumber() {
		return popSolutionNumber;
	}

	public void setPopSolutionNumber(int popSolutionNumber) {
		this.popSolutionNumber = popSolutionNumber;
	}

	public int getAlfa() {
		return alfa;
	}

	public void setAlfa(int alfa) {
		this.alfa = alfa;
	}

	public double getMaxFit() {
		return maxFit;
	}

	public void setMaxFit(double maxFit) {
		this.maxFit = maxFit;
	}

	public boolean getTracing() {
		return tracing;
	}

	public void setTracing(boolean tracing) {
		this.tracing = tracing;
	}

	public int getSensorRatio() {
		return searchSpaceProblemData.getTransmissorRangeRatio();
	}

	public void setSensorRatio(int sensorRatio) {
		this.searchSpaceProblemData.setSensorRatio(sensorRatio);
	}

	public int getGridSizeX() {
		return this.searchSpaceProblemData.getGridSizeX();
	}

	public void setGridSizeX(int gridSizeX) {
		this.searchSpaceProblemData.setGridSizeX(gridSizeX);
	}

	public int getGridSizeY() {
		return this.searchSpaceProblemData.getGridSizeY();
	}

	public void setGridSizeY(int gridSizeY) {
		this.searchSpaceProblemData.setGridSizeY(gridSizeY);
	}

	public int getRandomlyDistributedTransmissors() {
		return randomlyDistributedTransmissors;
	}

	public void setRandomlyDistributedTransmissors(int randomlyDistributedTransmissors) {
		this.randomlyDistributedTransmissors = randomlyDistributedTransmissors;
	}

	public DeploymentAreaData getSearchSpaceProblemData() {
		return searchSpaceProblemData;
	}

	public void setSearchSpaceProblemData(DeploymentAreaData searchSpaceProblemData) {
		this.searchSpaceProblemData = searchSpaceProblemData;
	}

	public int[] getArrayCoord() {
		return arrayCoord;
	}

	public void setArrayCoord(int[] arrayCoord) {
		this.arrayCoord = arrayCoord;
	}

	public Individual getBestFitIndividual(ObjectiveFunction objFunc) {
		return bestIndividualsAfterRun.getBestFitIndividual(objFunc);
	}
	
	public void setBestFitIndividual(Individual bestIndividual) {
		this.bestFitIndividual = bestIndividual;
	}

	public Population getBestIndividualsAfterRun() {
		return bestIndividualsAfterRun;
	}

	public void setBestIndividualsAfterRun(Population bestIndividualsAfterRun) {
		this.bestIndividualsAfterRun = bestIndividualsAfterRun;
	}
}
