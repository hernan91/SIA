package other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import generics.Individual;
import generics.Population;
import oneMaxProblem.OneMaxObjectiveFunction;
import operators.MutationOperator;
import operators.TwoPointsCrossover;

public class asd {

	public static void main(String[] args) {
		TwoPointsCrossover tpc = new TwoPointsCrossover(1);
		MutationOperator mop = new MutationOperator(1);
		OneMaxObjectiveFunction om = new OneMaxObjectiveFunction();
		Population pop1 = new Population(10,40,om);
		Population pop1Copy = pop1.clone();
		ArrayList<Individual> arrayPop2 = new ArrayList<Individual>();
		Iterator<Individual> itPop1 = pop1.getIndividuals().iterator();
		while(itPop1.hasNext()) {
			Iterator<Individual> itPop2 = tpc.crossover(itPop1.next(), itPop1.next());
			arrayPop2.add(itPop2.next()); arrayPop2.add(itPop2.next());
		}
		Iterator<Individual> itArrayPop2 = arrayPop2.iterator();
		while(itArrayPop2.hasNext()) {
			arrayPop2.add(itArrayPop2.next());
		}
		Population pop2 = new Population(arrayPop2, om);
		System.out.println(pop1Copy.getPopulationFitnessMean());
		System.out.println(pop2.getPopulationFitnessMean());
		System.out.println();
		
		//Terminar de probar esto y
		//PRobar que pasa si se copia una poblacion y luego se la modifica manualmente, a ver si se modifica o no. Por mas que parezca que esta funcionando bien la copia
	}
	/*	public static void main(String[] args) {
	OneMaxObjectiveFunction om = new OneMaxObjectiveFunction();
	Population pop1 = new Population(20, 10, om);asdasdsad
	Population pop2 = pop1.clone();
	Individual ind = pop1.getIndividuals().iterator().next();
	System.out.println(pop1.getIndividuals().get(0).getAllele());
	ind.getAllele()[0] = ind.getAllele()[0]==1?0:1;
	System.out.println(pop2.getIndividuals().get(0).getAllele());
	System.out.println();
	//Terminar de probar esto y
	//PRobar que pasa si se copia una poblacion y luego se la modifica manualmente, a ver si se modifica o no. Por mas que parezca que esta funcionando bien la copia
}*/
}
