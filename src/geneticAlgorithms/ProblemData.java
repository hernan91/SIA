package geneticAlgorithms;

import generics.ObjectiveFunction;

public abstract class ProblemData{
	private ObjectiveFunction objFunc;
	private float maxFit; //maximo fitness a encontrar hasta parar
	private int alfa;
	
	public ProblemData(ObjectiveFunction objFunc, float maxFit, int alfa) {
		super();
		this.maxFit = maxFit;
		this.alfa = alfa;
		this.objFunc = objFunc;
	}
	
	public ObjectiveFunction getObjFunc() {
		return objFunc;
	}

	public float getMaxFit() {
		return maxFit;
	}

	public int getAlfa() {
		return alfa;
	}
	
	public void setObjFunc(ObjectiveFunction objFunc) {
		this.objFunc = objFunc;
	}

	public void setMaxFit(float maxFit) {
		this.maxFit = maxFit;
	}

	public void setAlfa(int alfa) {
		this.alfa = alfa;
	}
}
