package executor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;

import generics.BinaryRepresentationIndividual;
import generics.Location;
import generics.ObjectiveFunction;
import generics.Population;
import operators.Operator;
import sensorsProblem.SensorsFieldData;
import sensorsProblem.SensorsProblemObjectiveFunction;

public class RunConfiguration <T extends BinaryRepresentationIndividual>{
	private int numExecutions;
	private Operator crossoverOperator;
	private Operator mutationOperator;
	private int maxGen;
	private float crossoverProbability;
	private float mutationProbability; //1/popSOoutionNumber
	private ObjectiveFunction<T> objectiveFunction;
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private int alfa; //siempre tiene que ser >1 para que funcione bien la func objetivo
	private double maxFit; //maximo fitness a encontrar hasta parar
	private boolean tracing;
	private int randomlyDistributedTransmissors;
	private SensorsFieldData searchSpaceProblemData;
	private Location[] prefixedPositions;
	private int alleleLength;
	private Population<T> bestIndividualsAfterRun;
	private T bestFitIndividual;
	
	public RunConfiguration(int numExecutions, Operator crossoverOperator, Operator mutationOperator, int maxGen, float crossoverProbability,
			ObjectiveFunction<T> objectiveFunction, int popSolutionNumber, int alfa, double maxFit, boolean tracing, 
			SensorsFieldData searchSpaceProblemData, Location[] prefixedPositions, int alleleLength) {
		super();
		this.numExecutions = numExecutions;
		this.crossoverOperator = crossoverOperator;
		this.mutationOperator = mutationOperator;
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
		this.alleleLength = alleleLength;
		this.randomlyDistributedTransmissors = alleleLength-prefixedPositions.length;
		this.prefixedPositions = prefixedPositions;
	}
	
	public String getInfo() {
		double mean = objectiveFunction.getPopulationFitnessMean( bestIndividualsAfterRun);
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("#.###", symbol);
		return  "DATOS DE CORRIDA Y POBLACION\n"+
				"Media= "+ formatter.format(mean) +"\n" +
				"Desvío estándar= "+ formatter.format(objectiveFunction.getPopulationFitnessStandardDeviation((Population<T>)bestIndividualsAfterRun)) +"\n" +
				"Fitness del mejor individuo= "+ formatter.format(bestFitIndividual.getFitness()) +"\n" +
				"Cromosoma del mejor individuo= "+ bestFitIndividual.getAlleleString() +"\n" +
				"Numero de sensores utilizado= " + bestFitIndividual.getAllele().length +"\n" +
				"Numero de sensores aleatoriamente distribuidos= " + randomlyDistributedTransmissors +"\n"+
				"Numero de ejecuciones= "+ numExecutions +"\n" +
				"Operador de cruza= "+ getCrossoverOperatorName() +"\n" +
				"Operador de mutacion= "+ getMutationOperatorName() +"\n" +
				"Numero de generaciones= "+ maxGen +"\n" +
				"Probabilidad de cruza= "+ crossoverProbability +"\n" +
				"Probabilidad de mutación= "+ mutationProbability +"\n" +
				"Función objetivo= "+ getObjectiveFunctionName() +"\n" +
				"Número de individuos de la población= "+ popSolutionNumber +"\n" +
				"Alfa= "+ alfa +"\n" +
				"Fitness óptimo= "+ maxFit +"\n" +
				"Radio de cobertura de los sensores= "+ searchSpaceProblemData.getTransmissorRangeRatio() +"\n" +
				"Tamaño del área de despliegue en X= "+ searchSpaceProblemData.getGridSizeX() +"\n" +
				"Tamaño del área de despliegue en Y= "+ searchSpaceProblemData.getGridSizeY();
	}
	
	public String[] getRelevantInfo() {
		String[] array = new String[5];
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("#.###", symbol);
		double mean = objectiveFunction.getPopulationFitnessMean((Population<T>)bestIndividualsAfterRun);
		array[0] = getName();
		array[1] = formatter.format(getBestFitIndividual(objectiveFunction).getFitness());
		array[2] = formatter.format(getWorstFitIndividual(objectiveFunction).getFitness());
		array[3] = formatter.format(mean);
		array[4] = formatter.format(objectiveFunction.getPopulationFitnessMean((Population<T>)bestIndividualsAfterRun));
		return array;
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
		s = s.substring(s.indexOf(".")+1);
		switch(s) {
			case "OnePointCrossoverOperator": return "Cruza1Punto";
			case "TwoPointCrossoverOperator": return "Cruza2Puntos";
			case "ThreePointCrossoverOperator": return "Cruza3Puntos";
		}
		return "None";
	}
	
	public String getMutationOperatorName() {
		String s = mutationOperator.getClass().getName();
		s = s.substring(s.indexOf(".")+1);
//		switch(s) {
//			case "OnePointCrossoverOperator": return "Cruza1Punto";
//			case "TwoPointCrossoverOperator": return "Cruza2Puntos";
//			case "ThreePointCrossoverOperator": return "Cruza3Puntos";
//		}
		return s;
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

	public ObjectiveFunction<T> getObjectiveFunction() {
		return objectiveFunction;
	}
	
	public String getObjectiveFunctionName() {
		String s = objectiveFunction.getClass().getName();
		s = s.substring(s.indexOf(".")+1);
		switch(s) {
			case "SquareRatioObjectiveFunction": return "RadioCuadrado";
			case "CircularRatioObjectiveFunction": return "RadioCircular";
		}
		return "None";
	}
	
	public String getName() {
		return getObjectiveFunctionName()+"-"+getCrossoverProbability()+"-"+getCrossoverOperatorName()+"-"+getMaxGen();
	}

	public void setObjectiveFunction(ObjectiveFunction<T> objectiveFunction) {
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

	public SensorsFieldData getSearchSpaceProblemData() {
		return searchSpaceProblemData;
	}

	public void setSearchSpaceProblemData(SensorsFieldData searchSpaceProblemData) {
		this.searchSpaceProblemData = searchSpaceProblemData;
	}

	public Location[] getPrefixedPositions() {
		return prefixedPositions;
	}

	public void setPrefixedPositions(Location[] arrayCoord) {
		this.prefixedPositions = arrayCoord;
	}

	public T getBestFitIndividual(ObjectiveFunction<T> objFunc) {
		return objFunc.getPopulationBestFitIndividual( bestIndividualsAfterRun);
	}
	
	public T getWorstFitIndividual(ObjectiveFunction<T> objFunc) {
		return objFunc.getPopulationWorstFitIndividual( bestIndividualsAfterRun);
	}
	
	public void setBestFitIndividual(T bestIndividual) {
		this.bestFitIndividual = bestIndividual;
	}

	public Population<T> getBestIndividualsAfterRun() {
		return bestIndividualsAfterRun;
	}

	public void setBestIndividualsAfterRun(Population<T> bestIndividualsAfterRun) {
		this.bestIndividualsAfterRun = bestIndividualsAfterRun;
	}

	public Operator getMutationOperator() {
		return mutationOperator;
	}

	public void setMutationOperator(Operator mutationOperator) {
		this.mutationOperator = mutationOperator;
	}

	public BinaryRepresentationIndividual getBestFitIndividual() {
		return bestFitIndividual;
	}

	public int getAlleleLength() {
		return alleleLength;
	}

	public void setAlleleLength(int alleleLength) {
		this.alleleLength = alleleLength;
	}
}
