package generics;

public abstract class ProblemData{
	private ObjectiveFunction objFunc;
	private double maxFit; //maximo fitness a encontrar hasta parar
	private int alfa;
	
	public ProblemData(ObjectiveFunction objFunc, double maxFit, int alfa) {
		super();
		this.maxFit = maxFit;
		this.alfa = alfa;
		this.objFunc = objFunc;
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
}
