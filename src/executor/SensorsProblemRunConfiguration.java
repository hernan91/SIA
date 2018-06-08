package executor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;

import individuals.SensorsProblemIndividual;
import objectiveFunctions.SensorsProblemObjectiveFunction;
import operatorsModels.CrossoverOperator;
import operatorsModels.MutationOperator;
import operatorsModels.ReplacementOperator;
import operatorsModels.SelectionOperator;
import others.Location;
import others.Population;
import problemData.SensorsFieldData;
import problemData.SensorsProblemData;

public class SensorsProblemRunConfiguration {
	private SensorsProblemData sensorsProblemData;
	private int numExecutions;
	private int maxGen;
	private SelectionOperator selectionOperator;
	private CrossoverOperator crossoverOperator;
	private MutationOperator mutationOperator;
	private ReplacementOperator ReplacementOperator;
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private float crossoverProbability;
	private float mutationProbability; //1/popSOoutionNumber
	private float takenFromNewGen;
	private boolean tracing;
	private int randomlyDistributedTransmissors;
	private Location[] prefixedPositions;
	private Population bestIndividualsAfterRun;
	private SensorsProblemIndividual bestFitIndividual;
	
	public SensorsProblemRunConfiguration (int numExecutions, SelectionOperator selectionOperator, CrossoverOperator crossoverOperator, 
			MutationOperator mutationOperator, ReplacementOperator replacementOperator, int maxGen, float crossoverProbability,
			float mutationProbability, float takenFromNewGen, int popSolutionNumber, boolean tracing, SensorsProblemData sensorsProblemData) {
		this.numExecutions = numExecutions;
		this.selectionOperator = selectionOperator;
		this.crossoverOperator = crossoverOperator;
		this.mutationOperator = mutationOperator;
		this.ReplacementOperator = replacementOperator;
		this.maxGen = maxGen;
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = (mutationProbability<0)? 1f/popSolutionNumber: mutationProbability;
		this.popSolutionNumber = popSolutionNumber;
		this.tracing = tracing;
		this.sensorsProblemData = sensorsProblemData;
		this.takenFromNewGen = takenFromNewGen;
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
				"Operador de seleccion= "+ getSelectionOperator() +"\n" +
				"Operador de cruza= "+ getCrossoverOperatorName() +"\n" +
				"Operador de mutacion= "+ getMutationOperatorName() +"\n" +
				"Operador de seleccion= "+ getReplacementOperator() +"\n" +
				"Numero de generaciones= "+ maxGen +"\n" +
				"Probabilidad de cruza= "+ crossoverProbability +"\n" +
				"Probabilidad de mutación= "+ mutationProbability +"\n" +
				"Proporción de individuos tomados de la nueva generacion"+ getTakenFromNewGen()+"\n"+
				"Función objetivo= "+ getObjectiveFunctionName() +"\n" +
				"Número de individuos de la población= "+ popSolutionNumber +"\n" +
				"Alfa= "+ getAlfa() +"\n" +
				"Fitness óptimo= "+ getMaxFit() +"\n" +
				"Radio de cobertura de los sensores= "+ getSensorsFieldData().getTransmissorRangeRatio() +"\n" +
				"Tamaño del área de despliegue en X= "+ getSensorsFieldData().getGridSizeX() +"\n" +
				"Tamaño del área de despliegue en Y= "+ getSensorsFieldData().getGridSizeY();
	}
	
	public String[] getRelevantInfo() {
		SensorsProblemObjectiveFunction objFunc = getObjectiveFunction();
		String[] array = new String[5];
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("#.###", symbol);
		double mean = objFunc.getPopulationFitnessMean(bestIndividualsAfterRun);
		array[0] = getName();
		array[1] = String.valueOf(getBestFitIndividual().getFitness());
		array[2] = String.valueOf(getWorstFitIndividual().getFitness());
		array[3] = String.valueOf(mean);
		array[4] = String.valueOf(objFunc.getPopulationFitnessStandardDeviation(bestIndividualsAfterRun));
//		array[1] = formatter.format(getBestFitIndividual().getFitness());
//		array[2] = formatter.format(getWorstFitIndividual().getFitness());
//		array[3] = formatter.format(mean);
//		array[4] = formatter.format(objFunc.getPopulationFitnessStandardDeviation(bestIndividualsAfterRun));
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

	public CrossoverOperator getCrossoverOperator() {
		return crossoverOperator;
	}

	public String getCrossoverOperatorName() {
		String s = crossoverOperator.getClass().getName();
		s = s.substring(s.indexOf(".")+1);
		switch(s) {
			case "OnePointCrossoverOperator": return "Cruza1Punto";
			case "TwoPointCrossoverOperator": return "Cruza2Puntos";
			case "ThreePointCrossoverOperator": return "Cruza3Puntos";
			case "SensorsProblemOnePointCrossoverOperator": return "CruzaSensores1Punto";
			case "SensorsProblemTwoPointCrossoverOperator": return "CruzaSensores2Puntos";
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
	
	public void setCrossoverOperator(CrossoverOperator crossoverOperator) {
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
		return getObjectiveFunctionName()+"-"+getCrossoverProbability()+"-"+getMutationProbability()+"-"+getCrossoverOperatorName()+"-"+getMaxGen();
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
		return sensorsProblemData.getSensorsFieldData().getTransmissorRangeRatio();
	}

	public int getGridSizeX() {
		return sensorsProblemData.getSensorsFieldData().getGridSizeX();
	}

	public int getGridSizeY() {
		return sensorsProblemData.getSensorsFieldData().getGridSizeY();
	}

	public int getRandomlyDistributedTransmissors() {
		return randomlyDistributedTransmissors;
	}

	public void setRandomlyDistributedTransmissors(int randomlyDistributedTransmissors) {
		this.randomlyDistributedTransmissors = randomlyDistributedTransmissors;
	}

	public SensorsFieldData getSensorsFieldData() {
		return sensorsProblemData.getSensorsFieldData();
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

	public MutationOperator getMutationOperator() {
		return mutationOperator;
	}

	public void setMutationOperator(MutationOperator mutationOperator) {
		this.mutationOperator = mutationOperator;
	}

	public int getAlleleLength() {
		return sensorsProblemData.getAlleleLength();
	}

	public SensorsProblemData getSensorsProblemData() {
		return sensorsProblemData;
	}

	public void setSensorsProblemData(SensorsProblemData sensorsProblemData) {
		this.sensorsProblemData = sensorsProblemData;
	}

	public SelectionOperator getSelectionOperator() {
		return selectionOperator;
	}

	public void setSelectionOperator(SelectionOperator selectionOperator) {
		this.selectionOperator = selectionOperator;
	}

	public ReplacementOperator getReplacementOperator() {
		return ReplacementOperator;
	}

	public void setReplacementOperator(ReplacementOperator replacementOperator) {
		ReplacementOperator = replacementOperator;
	}

	public float getTakenFromNewGen() {
		return takenFromNewGen;
	}

	public void setTakenFromNewGen(float takenFromNewGen) {
		this.takenFromNewGen = takenFromNewGen;
	}
}
