package generics;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class Population {
	private ArrayList<Individual> individuals = new ArrayList<Individual>();
	private int alleleLength;
	
	public Population(int alleleLength, int numberOfIndividuals) { //L es la variable longitud del alelo
		this.alleleLength = alleleLength;
		for(int i=0; i<numberOfIndividuals; i++){
			int[] genotype = getBinaryString(alleleLength);
			individuals.add(new Individual(genotype));
		}
	}
	
	public Population(ArrayList<Individual> individuals) {
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
	
	public int[] getBinaryString(int l){
		Random rand = new Random();
		int[] num = new int[l];
		for(int i=0; i<l; i++){
			num[i] = rand.nextInt(2);
		}
		return num;
	}

	public void evaluatePopulation(ObjectiveFunction objFunc) {
		Iterator<Individual> it = this.individuals.iterator();
		while(it.hasNext()) {
			Individual ind = it.next();
			ind.setFitness(objFunc.obtainFitness(ind));
		}
	}	
	
	public ArrayList<Individual> getIndividuals() {
		return individuals;
	}
	
	public Population copy() {
		Iterator<Individual> it = this.getIndividuals().iterator();
		ArrayList<Individual> clone = new ArrayList<Individual>();
		while(it.hasNext()) {
			clone.add(it.next().copy());
		}
		return new Population(clone);
	}
	
	public void sortByFitness(ObjectiveFunction objFunc) {
		evaluatePopulation(objFunc);
		Collections.sort(individuals, new Comparator<Individual>(){
			@Override
			public int compare(Individual ind1, Individual ind2) {
				return ind1.compareTo(ind2);
			}	
		});
		//
		Collections.reverse(individuals);
	}
	
	public Individual getBestFitIndividual(ObjectiveFunction objFunc) {
		sortByFitness(objFunc);
		return this.getIndividuals().get(0);
	}
	
	public double getPopulationFitnessMean(ObjectiveFunction objFunc) {
		evaluatePopulation(objFunc);
		Iterator<Individual> it = individuals.iterator();
		double summatory = 0; 
		while(it.hasNext()) summatory += it.next().getFitness();
		return summatory/getNumberOfIndividuals();
	}
	
	public void removeLastNIndividuals(int n) {
		for(int i=1; i<getNumberOfIndividuals(); i++) {
			individuals.remove(individuals.size()-1);
		}
	}
	
	public void printStatisticInfo(double optimalScore, ObjectiveFunction objFunc) {
		Individual best = getBestFitIndividual(objFunc);
		double bestFitnessScore = best.getFitness();
		double mean = getPopulationFitnessMean(objFunc);
		Iterator<Individual> it = individuals.iterator();
		float sumatoriaCuad = 0;
		while(it.hasNext()) sumatoriaCuad += Math.pow(it.next().getFitness()-mean, 2);
		DecimalFormat format = new DecimalFormat("#.###");
//		String str = "Mejor solucion:\n"
//				+ "Fitness= "+format.format(bestFitnessScore)+"\n"
//				+ "Error porcentual Ebest= "+format.format(((bestFitnessScore-optimalScore)/optimalScore)*100)+"%\n"
//				+ "Media poblacional Epop= "+format.format(mean)+"\n"
//				+ "Desviacion estandar= "+format.format(Math.sqrt(sumatoriaCuad/getNumberOfIndividuals()))+"\n"
//				+ ""+"\n";
		String str = format.format(bestFitnessScore);
		System.out.println(str);
	}
	
	public double getPopulationFitnessStandardDeviation(double mean) {
		Iterator<Individual> it = individuals.iterator();
		float sumatoriaCuad = 0;
		while(it.hasNext()) sumatoriaCuad += Math.pow(it.next().getFitness()-mean, 2);
		return sumatoriaCuad/getNumberOfIndividuals();
	}
	
	public void printIndividualsAllels() {
		for( Individual ind : getIndividuals()) {
			for( int allele : ind.getAllele()) {
				System.out.print(allele);
			}
			System.out.println();
		}
	}
}
