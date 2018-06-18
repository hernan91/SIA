package executor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;

import individuals.SensorsProblemIndividual;
import objectiveFunctions.SensorsProblemObjectiveFunction;
import operatorsModels.CrossoverOperator;
import operatorsModels.MutationOperator;
import operatorsModels.Operator;
import operatorsModels.ReplacementOperator;
import operatorsModels.SelectionOperator;
import operatorsModels.TranslocationOperator;
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
	private TranslocationOperator translocationOperator;
	private MutationOperator mutationOperator;
	private ReplacementOperator replacementOperator;
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private float crossoverProbability;
	private float translocationOperatorThrershold;
	private float mutationProbability; //1/popSOoutionNumber
	private boolean tracing;
	private Location[] prefixedPositions;
	private Population bestIndividualsAfterRun;
	private SensorsProblemIndividual bestFitIndividual;
	
	public SensorsProblemRunConfiguration (int numExecutions, SelectionOperator selectionOperator, CrossoverOperator crossoverOperator, 
			TranslocationOperator translocationOperator, MutationOperator mutationOperator, ReplacementOperator replacementOperator, 
			int maxGen, float crossoverProbability,	float translocationOperatorThrershold, float mutationProbability, int popSolutionNumber, boolean tracing, 
			SensorsProblemData sensorsProblemData) {
		this.numExecutions = numExecutions;
		this.selectionOperator = selectionOperator;
		this.crossoverOperator = crossoverOperator;
		this.translocationOperator = translocationOperator;
		this.mutationOperator = mutationOperator;
		this.replacementOperator = replacementOperator;
		this.maxGen = maxGen;
		this.crossoverProbability = crossoverProbability;
		this.translocationOperatorThrershold = translocationOperatorThrershold;
		this.mutationProbability = (mutationProbability<0)? 1f/popSolutionNumber: mutationProbability;
		this.popSolutionNumber = popSolutionNumber;
		this.tracing = tracing;
		this.sensorsProblemData = sensorsProblemData;
	}
	
	public String getInfo() {
		double mean = getObjectiveFunction().getPopulationFitnessMean( bestIndividualsAfterRun);
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("#.###", symbol);
		String translocationOp = "";
		String translocationOpThrershold = "";
		if(translocationOperator!=null) {
			translocationOp = "Operador de translocación= "+ getOperatorClassname(translocationOperator) +"\n";
			translocationOpThrershold = "Threshold de operador de translocación" + translocationOpThrershold + "\n";
		}
		return  "DATOS DE CORRIDA Y POBLACION\n"+
				"Media= "+ formatter.format(mean) +"\n" +
				"Desvío estándar= "+ formatter.format(getObjectiveFunction().getPopulationFitnessStandardDeviation(bestIndividualsAfterRun)) +"\n" +
				"Fitness del mejor individuo= "+ formatter.format(bestFitIndividual.getFitness()) +"\n" +
				"Cromosoma del mejor individuo= "+ bestFitIndividual.getAlleleString() +"\n" +
				"Numero de sensores utilizado= " + bestFitIndividual.getAlleleLength() +"\n" +
				"Numero de sensores aleatoriamente distribuidos= " + getRandomlyDistributedTransmissors() +"\n"+
				"Numero de ejecuciones= "+ numExecutions +"\n" +
				"Operador de seleccion= "+ getSelectionOperator() +"\n" +
				"Operador de cruza= "+ getOperatorClassname(crossoverOperator) +"\n" +
				translocationOp +
				"Operador de mutacion= "+ getOperatorClassname(mutationOperator) +"\n" +
				"Operador de reemplazo= "+ getReplacementOperator() +"\n" +
				"Numero de generaciones= "+ maxGen +"\n" +
				"Probabilidad de cruza= "+ crossoverProbability +"\n" +
				translocationOpThrershold +
				"Probabilidad de mutación= "+ mutationProbability +"\n" +
				"Proporción de individuos tomados de la nueva generacion= "+ getTakenFromNewGenProportion()+"\n"+
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
		//DecimalFormat formatter = new DecimalFormat("#.###", symbol);
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

	public String getOperatorClassname(Operator operator) {
		String s = operator.getClass().getName();
		return s.substring(s.indexOf(".")+1);
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
		String crossoverProbability = String.valueOf(getCrossoverProbability());
		String mutationProbability = String.valueOf(getMutationProbability());
		String takenFromNewGen = getTakenFromNewGenProportion()>=0 ? "PR="+String.valueOf(getTakenFromNewGenProportion())+"_" : "";
		String objectiveFunction = getObjectiveFunctionName();
		String selectionOperatorShorterName = shortenOperatorName(getOperatorClassname(selectionOperator));
		String crossoverOperatorShorterName = shortenOperatorName(getOperatorClassname(crossoverOperator));
		String translocationOperatorShorterName = (translocationOperator!=null)? "OPT="+shortenOperatorName(getOperatorClassname(translocationOperator))+"_":"";
		String mutationOperatorShorterName = shortenOperatorName(getOperatorClassname(mutationOperator));
		String replacementOperatorShorterName = shortenOperatorName(getOperatorClassname(replacementOperator));
		String genNumber = String.valueOf(getMaxGen());
		String name = "PC="+crossoverProbability +"_"+ "PM="+mutationProbability+"_" +"TTH="+translocationOperatorThrershold+"_"+ takenFromNewGen +
				"It="+genNumber +"_"+"OPS="+selectionOperatorShorterName +"_"+"OPC="+crossoverOperatorShorterName +"_"+translocationOperatorShorterName+
				"OPM="+mutationOperatorShorterName+"_"+"OPR="+replacementOperatorShorterName+"_"+"Pop="+popSolutionNumber + "_"+"OF="+objectiveFunction;
				
		return name;
	}
	
	public static String shortenOperatorName(String OperatorName) {
		switch (OperatorName) {
			case "OnePointCrossoverOperator":
				return "1PC";
			case "TwoPointCrossoverOperator":
				return "2PC";
			case "SensorsProblemTwoPointCrossoverOperator":
				return "SP2PC";
			case "ThreePointCrossoverOperator":
				return "3PC";
			case "SensorsProblemOnePointCrossoverOperator":
				return "SP1PC";
			case "SingleVortexNeighborhoodMutationOperator":
				return "SVNMO";
			case "SensorsProblemMutationOperator":
				return "SPMOP";
			case "PacoTranslocationOperator":
				return "Paco";
			case "RandomMutationOperator":
				return "RMO";
			case "ElitistReplacementOperator":
				return "Elit";
			case "SensorsProblemProportionReplacementOperator":
				return "Prop";
			case "RectangularGeographicCrossoverOperator":
				return "RGC";		
			case "BinaryTournamentSelectionOperator":
				return "BT";
			case "RouletteWheelSelectionOperator":
				return "RWS";
			default: return OperatorName;
		}
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
		return sensorsProblemData.getSensorsFieldProblemData().getTransmissorRangeRatio();
	}

	public int getGridSizeX() {
		return sensorsProblemData.getSensorsFieldProblemData().getGridSizeX();
	}

	public int getGridSizeY() {
		return sensorsProblemData.getSensorsFieldProblemData().getGridSizeY();
	}

	public int getRandomlyDistributedTransmissors() {
		return this.getSensorsProblemData().getRandomlyDistributedTransmissors();
	}

	public void setRandomlyDistributedTransmissors(int randomlyDistributedTransmissors) {
		this.getSensorsProblemData().setRandomlyDistributedTransmissors(randomlyDistributedTransmissors);
	}

	public SensorsFieldData getSensorsFieldData() {
		return sensorsProblemData.getSensorsFieldProblemData();
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
		return replacementOperator;
	}

	public void setReplacementOperator(ReplacementOperator replacementOperator) {
		this.replacementOperator = replacementOperator;
	}

	public float getTakenFromNewGenProportion() {
		return getSensorsProblemData().getTakenFromNewGenProportion();
	}

	public void setTakenFromNewGen(float takenFromNewGenProportion) {
		getSensorsProblemData().setTakenFromNewGenProportion(takenFromNewGenProportion);
	}

	public TranslocationOperator getTranslocationOperator() {
		return translocationOperator;
	}

	public void setTranslocationOperator(TranslocationOperator translocationOperator) {
		this.translocationOperator = translocationOperator;
	}

	public float getTranslocationOperatorThrershold() {
		return translocationOperatorThrershold;
	}

	public void setTranslocationOperatorThrershold(float translocationOperatorThrershold) {
		this.translocationOperatorThrershold = translocationOperatorThrershold;
	}
}
