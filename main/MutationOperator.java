package main;

import java.util.Random;

//Reproduction strategy - Mutation
public class MutationOperator {
	private float mutationProbability; 
	
	public MutationOperator(float mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
	
	/**
	 * Aca iba a elegir aleatoriamente una posicion del alelo para el bitflip, 
	 * pero lei que es importante el tema de la localizaciòn, que sean cambios minimos
	 * dado que el objetivo de la mutación es explotación. 
	 * Tomo los ultimos bits? solo el ultimo? Todos?
	 * @param individual
	 * @return
	 */
	//No le puse mutate porque no necesariamente muta
	public Individual operate(Individual individual) {
		individual = individual.clone();
		int[] allele = individual.getAllele();
		Random rand = new Random();
		float pm = rand.nextFloat();
		int limitPosition = allele.length;
		if(pm<=mutationProbability) { //Menor o igual esta bien no?
			int choosenPos = rand.nextInt(limitPosition);
			allele[choosenPos] = allele[choosenPos]==0 ? 1:0;
		}
		return individual;
	}
}
