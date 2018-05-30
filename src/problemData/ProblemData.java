package problemData;

import individuals.Individual;
import objectiveFunctions.ObjectiveFunction;

public abstract class ProblemData{
	private ObjectiveFunction objFunc;
	private double maxFit; //maximo fitness a encontrar hasta parar
	private int alfa;
	private Individual individual;
	
	public ProblemData(ObjectiveFunction objFunc, double maxFit, int alfa, Individual individual) {
		super();
		this.maxFit = maxFit;
		this.alfa = alfa;
		this.objFunc = objFunc;
		this.individual = individual;
	}
	
	public ObjectiveFunction getObjFunc() {
		return objFunc;
	}

	public double getMaxFit() {
		return maxFit;
	}

	public int getAlfa() {
		return alfa;
	}
	
	public void setObjFunc(ObjectiveFunction objFunc) {
		this.objFunc = objFunc;
	}

	public void setMaxFit(double maxFit) {
		this.maxFit = maxFit;
	}

	public void setAlfa(int alfa) {
		this.alfa = alfa;
	}
	
	public Individual getIndividual() {
		return individual;
	}

	public void setIndividual(Individual individual) {
		this.individual = individual;
	}

	public abstract Individual getIndividualInstance() throws ClassNotFoundException;
}
