package executor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;

import org.apache.poi.ss.formula.functions.T;

import generics.BinaryRepresentationIndividual;
import generics.Location;
import generics.ObjectiveFunction;
import generics.Population;
import generics.ProblemData;
import operators.Operator;
import sensorsProblem.SensorsFieldData;
import sensorsProblem.SensorsProblemData;
import sensorsProblem.SensorsProblemIndividual;
import sensorsProblem.SensorsProblemObjectiveFunction;

public class SensorsProblemRunConfiguration {
	private SensorsProblemData sensorsProblemData;
	private int numExecutions;
	private int maxGen;
	private Operator crossoverOperator;
	private Operator mutationOperator;
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private float crossoverProbability;
	private float mutationProbability; //1/popSOoutionNumber
	private boolean tracing;
	private int randomlyDistributedTransmissors;
	private Location[] prefixedPositions;
	private Population bestIndividualsAfterRun;
	private SensorsProblemIndividual bestFitIndividual;
	
	public void RunSensorsConfiguration(int numExecutions, Operator crossoverOperator, Operator mutationOperator, int maxGen, float crossoverProbability,
			int popSolutionNumber, boolean tracing, SensorsProblemData sensorsProblemData) {
		this.numExecutions = numExecutions;
		this.crossoverOperator = crossoverOperator;
		this.mutationOperator = mutationOperator;
		this.maxGen = maxGen;
		this.crossoverProbability = crossoverProbability;
		//this.mutationProbability = mutationProbability;
		this.mutationProbability = (1f/popSolutionNumber);
		this.popSolutionNumber = popSolutionNumber;
		this.tracing = tracing;
		this.sensorsProblemData = sensorsProblemData;
	}
	
	public String getInfo() {
		double mean = getObjectiveFunction().getPopulationFitnessMean( bestIndividualsAfterRun);
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("#.###", symbol);
		return  "DATOS DE CORRIDA Y POBLACION\n"+
				"Media= "+ formatter.format(mean) +"\n" +
				"Desvío estándar= "+ formatter.format(getObjectiveFunction().getPopulationFitnessStandardDeviation(bestIndividualsAfterRun)) +"\n" +
				"Fitness del mejor individuo= "+ formatter.format(bestFitIndividual.getFitness()) +"\n" +
				"Cromosoma del mejor individuo= "+ bestFitIndividual.getAlleleString() +"\n" +
				"Numero de sensores utilizado= " + bestFitIndividual.getAlleleLength() +"\n" +
				"Numero de sensores aleatoriamente distribuidos= " + randomlyDistributedTransmissors +"\n"+
				"Numero de ejecuciones= "+ numExecutions +"\n" +
				"Operador de cruza= "+ getCrossoverOperatorName() +"\n" +
				"Operador de mutacion= "+ getMutationOperatorName() +"\n" +
				"Numero de generaciones= "+ maxGen +"\n" +
				"Probabilidad de cruza= "+ crossoverProbability +"\n" +
				"Probabilidad de mutación= "+ mutationProbability +"\n" +
				"Función objetivo= "+ getObjectiveFunctionName() +"\n" +
				"Número de individuos de la población= "+ popSolutionNumber +"\n" +
				"Alfa= "+ getAlfa() +"\n" +
				"Fitness óptimo= "+ getMaxFit() +"\n" +
				"Radio de cobertura de los sensores= "+ getSearchSpaceProblemData().getTransmissorRangeRatio() +"\n" +
				"Tamaño del área de despliegue en X= "+ getSearchSpaceProblemData().getGridSizeX() +"\n" +
				"Tamaño del área de despliegue en Y= "+ getSearchSpaceProblemData().getGridSizeY();
	}
	
	public String[] getRelevantInfo() {
		String[] array = new String[5];
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("#.###", symbol);
		double mean = getO.getPopulationFitnessMean((Population<T>)bestIndividualsAfterRun);
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

	public SensorsProblemObjectiveFunction getObjectiveFunction() {
		return sensorsProblemData.getObjFunc();
	}
	
	public String getObjectiveFunctionName() {
		String s = getObjectiveFunction().getClass().getName();
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

	public int getPopSolutionNumber() {
		return popSolutionNumber;
	}

	public void setPopSolutionNumber(int popSolutionNumber) {
		this.popSolutionNumber = popSolutionNumber;
	}

	public int getAlfa() {
		return sensorsProblemData.getAlfa();
	}

	public double getMaxFit() {
		return sensorsProblemData.getMaxFit();
	}

	public boolean getTracing() {
		return tracing;
	}

	public void setTracing(boolean tracing) {
		this.tracing = tracing;
	}

	public int getSensorRatio() {
		return sensorsProblemData.getSquareGridProblemData().getTransmissorRangeRatio();
	}

	public int getGridSizeX() {
		return sensorsProblemData.getSquareGridProblemData().getGridSizeX();
	}

	public int getGridSizeY() {
		return sensorsProblemData.getSquareGridProblemData().getGridSizeY();
	}

	public int getRandomlyDistributedTransmissors() {
		return randomlyDistributedTransmissors;
	}

	public void setRandomlyDistributedTransmissors(int randomlyDistributedTransmissors) {
		this.randomlyDistributedTransmissors = randomlyDistributedTransmissors;
	}

	public SensorsFieldData getSearchSpaceProblemData() {
		return sensorsProblemData.getSquareGridProblemData();
	}

	public Location[] getPrefixedPositions() {
		return prefixedPositions;
	}

	public void setPrefixedPositions(Location[] arrayCoord) {
		this.prefixedPositions = arrayCoord;
	}

	public SensorsProblemIndividual getBestFitIndividual() {
		return getObjectiveFunction().getPopulationBestFitIndividual( bestIndividualsAfterRun);
	}
	
	public SensorsProblemIndividual getWorstFitIndividual() {
		return getObjectiveFunction().getPopulationWorstFitIndividual( bestIndividualsAfterRun);
	}
	
	public void setBestFitIndividual(SensorsProblemIndividual bestIndividual) {
		this.bestFitIndividual = bestIndividual;
	}

	public Population getBestIndividualsAfterRun() {
		return bestIndividualsAfterRun;
	}

	public void setBestIndividualsAfterRun(Population bestIndividualsAfterRun) {
		this.bestIndividualsAfterRun = bestIndividualsAfterRun;
	}

	public Operator getMutationOperator() {
		return mutationOperator;
	}

	public void setMutationOperator(Operator mutationOperator) {
		this.mutationOperator = mutationOperator;
	}

	public int getAlleleLength() {
		return sensorsProblemData.getAlleleLength();
	}
}
