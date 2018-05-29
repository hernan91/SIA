package generics;

import java.util.ArrayList;
import java.util.Iterator;

public class Population<T extends Individual>{
	private ArrayList<T> individuals = new ArrayList<>();
	private int alleleLength;
	private ProblemData data;
	
	public Population(int alleleLength, ArrayList<T> individuals, ProblemData data ) {
		this.alleleLength = alleleLength;
		this.data = data;
		this.individuals = individuals;
	}
	
	public Population(int alleleLength, int numberOfIndividuals, ProblemData data ) {
		this.alleleLength = alleleLength;
		this.data = data;
		for(int i=0; i<numberOfIndividuals; i++){
			try {
				individuals.add((T)data.getIndividualInstance(alleleLength));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("La clase seleccionada no existe");
			}
		}
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
		return new Population<T>(alleleLength, clone, data);
	}
	
	public void printPop() {
		for( T ind : getIndividuals()) {
			ind.printData();
		}
	}
	
	public void removeLastNIndividualsFromPopulation(Population<T> population, int n) {
		for(int i=1; i<population.getNumberOfIndividuals(); i++) {
			population.getIndividuals().remove(population.getNumberOfIndividuals()-1);
		}
	}
}
