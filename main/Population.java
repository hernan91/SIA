package main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class Population {
	private ArrayList<Individual> individuals = new ArrayList<Individual>();
	private int alleleLength;
	private ObjectiveFunction objFunct;
	
	public Population(int alleleLength, int numberOfIndividuals, ObjectiveFunction objFunct) { //L es la variable longitud del alelo
		this.alleleLength = alleleLength;
		this.objFunct = objFunct;
		for(int i=0; i<numberOfIndividuals; i++){
			int[] genotype = getBinaryString(alleleLength);
			individuals.add(new Individual(genotype));
		}
	}
	
	public Population(ArrayList<Individual> individuals, ObjectiveFunction objFunct) {
		this.individuals = individuals;
		this.alleleLength = individuals.get(0).getAllele().length;
		this.objFunct = objFunct;
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
	
	public ObjectiveFunction getObjFunct() {
		return objFunct;
	}

	public void setNumberOfIndividuals(ObjectiveFunction objFunct) {
		this.objFunct = objFunct;
	}
	
	public int[] getBinaryString(int l){
		Random rand = new Random();
		int[] num = new int[l];
		for(int i=0; i<l; i++){
			num[i] = rand.nextInt(2);
		}
		return num;
	}

	public void evaluatePopulation() {
		Iterator<Individual> it = this.individuals.iterator();
		while(it.hasNext()) {
			Individual ind = it.next();
			ind.setFitness(objFunct.obtainFitness(ind));
		}
	}	
	
	public ArrayList<Individual> getIndividuals() {
		return individuals;
	}
	
	public Population clone() {
		Iterator<Individual> it = this.getIndividuals().iterator();
		ArrayList<Individual> clone = new ArrayList<Individual>();
		while(it.hasNext()) {
			clone.add(it.next().clone());
		}
		return new Population(clone, objFunct);
	}
	
	public void sortByFitness() {
		evaluatePopulation();
		Collections.sort(individuals, new Comparator<Individual>(){
			@Override
			public int compare(Individual ind1, Individual ind2) {
				return ind1.compareTo(ind2, objFunct);
			}	
		});
	}
	
	public Individual getBestFitIndividual() {
		evaluatePopulation();
		return this.getIndividuals().get(0);
	}
	
	public double getPopulationFitnessMean() {
		evaluatePopulation();
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
	
	public void printStatisticInfo(float optimalScore) {
		Individual best = getBestFitIndividual();
		double bestFitnessScore = best.getFitness();
		double mean = getPopulationFitnessMean();
		Iterator<Individual> it = individuals.iterator();
		float sumatoriaCuad = 0;
		while(it.hasNext()) sumatoriaCuad += Math.pow(it.next().getFitness()-mean, 2);
		DecimalFormat format = new DecimalFormat("#.###");
		String str = "Mejor solucion:\n"
				+ "Fitness= "+format.format(bestFitnessScore)+"\n"
				+ "Error porcentual Ebest= "+format.format(((bestFitnessScore-optimalScore)/optimalScore)*100)+"%\n"
				+ "Media poblacional Epop= "+format.format(mean)+"\n"
				+ "Desviacion estándar= "+format.format(Math.sqrt(sumatoriaCuad/getNumberOfIndividuals()))+"\n"
				+ ""+"\n";
		System.out.println(str);
	}
}
