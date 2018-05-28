package generics;

import java.util.ArrayList;
import java.util.Iterator;

public class Population<T extends BinaryRepresentationIndividual>{
	private ArrayList<T> individuals = new ArrayList<>();
	private int alleleLength;
	
	public Population() {}
	public Population(int alleleLength, int numberOfIndividuals) {
		this.alleleLength = alleleLength;
		for(int i=0; i<numberOfIndividuals; i++){
			individuals.add((T)new BinaryRepresentationIndividual(alleleLength));
		}
	}
	
	public Population(ArrayList<T> individuals) {
		this.individuals = individuals;
		this.alleleLength = individuals.get(0).getAllele().length;
	}

	public int getAlleleLength() {
		return alleleLength;
	}

	public void setAlleleLength(int alleleLength) {
		this.alleleLength = alleleLength;
	}

	public int getNumberOfIndividuals() {
		return individuals.size();
	}
	
	public void setIndividuals(ArrayList<T> individuals) {
		this.individuals = individuals;
	}
	public ArrayList<T> getIndividuals() {
		return individuals;
	}
	
	public Population <T> copy() {
		Iterator<T> it = this.getIndividuals().iterator();
		ArrayList<T> clone = new ArrayList<T>();
		while(it.hasNext()) {
			T ind = it.next();
			clone.add((T)ind.copy());
		}
		return new Population<T>(clone);
	}
	
	public void printPop() {
		for( BinaryRepresentationIndividual ind : getIndividuals()) {
			for( int allele : ind.getAllele()) {
				System.out.print(allele);
			}
			System.out.println();
			System.out.println(ind.getFitness());
		}
	}
	
	public void removeLastNIndividualsFromPopulation(Population<T> population, int n) {
		for(int i=1; i<population.getNumberOfIndividuals(); i++) {
			population.getIndividuals().remove(population.getNumberOfIndividuals()-1);
		}
	}
	
	
}
