package geneticAlgorithms;

import generics.ObjectiveFunction;

public abstract class GAProblemData{
	private ObjectiveFunction objFunc;
	private int alleleLength; //longitud del alelo
	private int popSolutionNumber; //numero de soluciones de la poblacion
	private int maxGen; //2000 numero màximo de generaciones
	private float maxFit; //maximo fitness a encontrar hasta parar
	private int alfa;
	private float crossoverProbability;
	private float mutationProbability;
	
	public GAProblemData(ObjectiveFunction objFunc, int alleleLength, int popSolutionNumber, int maxGen, float maxFit, int alfa,
			float crossoverProbability, float mutationProbability) {
		super();
		this.alleleLength = alleleLength;
		this.popSolutionNumber = popSolutionNumber;
		this.maxGen = maxGen;
		this.maxFit = maxFit;
		this.alfa = alfa;
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = mutationProbability;
		this.objFunc = objFunc;
	}
	
	public ObjectiveFunction getObjFunc() {
		return objFunc;
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

	public float getMaxFit() {
		return maxFit;
	}

	public int getAlfa() {
		return alfa;
	}

	public float getCrossoverProbability() {
		return crossoverProbability;
	}

	public float getMutationProbability() {
		return mutationProbability;
	}
	
	public void setObjFunc(ObjectiveFunction objFunc) {
		this.objFunc = objFunc;
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

	public void setMaxFit(float maxFit) {
		this.maxFit = maxFit;
	}

	public void setAlfa(int alfa) {
		this.alfa = alfa;
	}

	public void setCrossoverProbability(float crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}

	public void setMutationProbability(float mutationProbability) {
		this.mutationProbability = mutationProbability;
	}	
}
