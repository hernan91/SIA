package generics;

public abstract class Individual {
	private double fitness;
	public Individual() {
		
	}
	
	public Individual(double fitness) {
		this.fitness = fitness;
	}
	
	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public abstract Individual copy();
	
	public abstract int compareTo(Individual ind, ObjectiveFunction objFunc);
	
	public abstract void printData();
}
