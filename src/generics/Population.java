package generics;

import java.util.ArrayList;
import java.util.Iterator;

public class Population{
	private ArrayList<Individual> individuals = new ArrayList<>();
	private ProblemData data;
	
	public Population(ArrayList<Individual> individuals, ProblemData data ) {
		this.data = data;
		this.individuals = individuals;
	}
	
	public Population(int numberOfIndividuals, ProblemData data ) {
		this.data = data;
		for(int i=0; i<numberOfIndividuals; i++){
			try {
				individuals.add(data.getIndividualInstance());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("La clase seleccionada no existe");
			}
		}
	}

	public int getNumberOfIndividuals() {
		return individuals.size();
	}
	
	public void setIndividuals(ArrayList<Individual> individuals) {
		this.individuals = individuals;
	}
	public ArrayList<Individual> getIndividuals() {
		return individuals;
	}
	
	public Population copy() {
		Iterator<Individual> it = this.getIndividuals().iterator();
		ArrayList<Individual> clone = new ArrayList<Individual>();
		while(it.hasNext()) {
			Individual ind = it.next();
			clone.add((Individual)ind.copy());
		}
		return new Population(clone, data);
	}
	
	public void printPop() {
		for( Individual ind : getIndividuals()) {
			ind.printData();
		}
	}
	
	public void removeLastNIndividualsFromPopulation(Population population, int n) {
		for(int i=1; i<population.getNumberOfIndividuals(); i++) {
			population.getIndividuals().remove(population.getNumberOfIndividuals()-1);
		}
	}
}
