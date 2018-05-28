package operators;

import java.util.ArrayList;
import java.util.Random;

import generics.BinaryRepresentationIndividual;
import generics.ObjectiveFunction;

/**
 * Clase que permite instanciar un operador binario (recibe dos individuos) y devolver el ganador de un torneo.
 * @author hernan
 *
 */
public class BinaryTournamentSelectionOperator extends Operator{
	private ObjectiveFunction objFunction;
	
	public BinaryTournamentSelectionOperator(ObjectiveFunction objFunction) {
		this.objFunction = objFunction;
	}
	
	
	/**
	 * Selecciona dos individuos de la población al azar y los compara. Devuelve el mas apto segun la funcion de fitness
	 * @param population La poblacion de la cual se seleccionaran los individuos
	 * @return El individuo que gane el torneo
	 */
	private BinaryRepresentationIndividual doTournamentRound(ArrayList<BinaryRepresentationIndividual> population) {
		Random rand = new Random();
		int randNum1 = rand.nextInt(population.size()-1);
		int randNum2 = rand.nextInt(population.size()-1);
		BinaryRepresentationIndividual individual1 = population.get(randNum1).copy();
		BinaryRepresentationIndividual individual2 = population.get(randNum2).copy();
		return (individual1.compareTo(individual2, objFunction)==1)? individual1: individual2;
	}

	/**
	 * Devuelve un arrayList con el individuo ganador del torneo
	 */
	@Override
	public ArrayList<BinaryRepresentationIndividual> operate(ArrayList<BinaryRepresentationIndividual> population) {
		ArrayList<BinaryRepresentationIndividual> individuals = new ArrayList<BinaryRepresentationIndividual>();
		individuals.add(doTournamentRound(population));
		return individuals;
	}
}
