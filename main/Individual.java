package main;

public class Individual implements Cloneable{
	private int genotype[]; //sensor status allele
	private double fitness;
	
	public Individual(int[] allele) {
		super();
		this.genotype = allele;
	}
	
	public Individual(int[] allele, double fitness) {
		super();
		this.genotype = allele;
		this.fitness = fitness;
	}

	public int[] getAllele() {
		return genotype;
	}

	public void setAllele(int[] allele) {
		this.genotype = allele;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public Individual clone() {
		int[] oldAllele = this.getAllele();
		int[] newAllele = new int[oldAllele.length];
		for(int i=0; i<newAllele.length; i++) {
			newAllele[i] = oldAllele[i];
		}
		return new Individual(newAllele, this.fitness);
	}

	public int compareTo(Individual ind, ObjectiveFunction objFunction) {
		if(objFunction.obtainFitness(this) < objFunction.obtainFitness(ind)) return 1;
		else if (objFunction.obtainFitness(this) == objFunction.obtainFitness(ind)) return 0;
		else return -1;
	}
}