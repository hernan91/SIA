package individuals;

import java.util.Random;

import objectiveFunctions.ObjectiveFunction;

public abstract class BinaryRepresentationIndividual extends Individual implements Cloneable, Comparable<BinaryRepresentationIndividual>{
	private int genotype[]; //sensor status allele
	
	public BinaryRepresentationIndividual(int alleleLength) {
		this.genotype = getBinaryString(alleleLength);
	}
	
	public BinaryRepresentationIndividual(int[] allele) {
		super();
		this.genotype = allele;
	}
	
	public BinaryRepresentationIndividual(int[] allele, double fitness) {
		super(fitness);
		this.genotype = allele;
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

	@Override
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
	public int compareTo(BinaryRepresentationIndividual ind2) {
		if(getFitness() < ind2.getFitness()) return -1;
		else if(getFitness() == ind2.getFitness()) return 0;
		else return 1;
	}

	@Override
	public void printData() {
		System.out.print("Alelo: ");
		for(int x : genotype) System.out.print(x);
		System.out.println("\nFitness: "+getFitness());
	}
	
	public int getAlleleLength() {
		return getAllele().length;
	}
	
	public int getActiveSensorsNumber() {
		int count = 0;
		for(int bit : this.getAllele()) {
			if(bit==1) count++;
		}
		return count;
	}
}