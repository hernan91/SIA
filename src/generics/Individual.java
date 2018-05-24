package generics;

import java.util.Random;

public class Individual implements Cloneable, Comparable<Individual>{
	private int genotype[]; //sensor status allele
	private double fitness;
	
	public Individual(int[] allele) {
		this.genotype = allele;
	}
	
	public Individual(int[] allele, double fitness) {
		super();
		this.genotype = allele;
		this.fitness = fitness;
	}
	
	public Individual(int alleleLength) {
		this.genotype = getBinaryString(alleleLength);
	}

	public int[] getBinaryString(int l){
		Random rand = new Random();
		int[] num = new int[l];
		for(int i=0; i<l; i++){
			num[i] = rand.nextInt(2);
		}
		return num;
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
	
	public Individual copy() {
		int[] oldAllele = this.getAllele();
		int[] newAllele = new int[oldAllele.length];
		for(int i=0; i<newAllele.length; i++) {
			newAllele[i] = oldAllele[i];
		}
		return new Individual(newAllele, this.fitness);
	}

	public int compareTo(Individual ind, ObjectiveFunction objFunction) {
		if(objFunction.getFitness(this) < objFunction.getFitness(ind)) return 1;
		else if (objFunction.getFitness(this) == objFunction.getFitness(ind)) return 0;
		else if (objFunction.getFitness(this) > objFunction.getFitness(ind)) return -1; //lo agregue porque si no salta un error que dice que la funcion no es transitiva
		else return 0;
	}
	
	public void printAllele() {
		System.out.print("Alelo = ");
		for(int x : genotype) System.out.print(x);
		System.out.println();
	}
	
	public String getAlleleString() {
		String alleleString = "";
		for(int x : genotype) alleleString += x;
		return alleleString;
	}

	@Override
	public int compareTo(Individual ind2) {
		if(fitness < ind2.getFitness()) return -1;
		else if(fitness == ind2.getFitness()) return 0;
		else return 1;
	}
}