package operators;

import java.util.ArrayList;
import java.util.Random;

import generics.BinaryRepresentationIndividual;

public class BitFlipMutationOperator extends Operator{
	
	/**
	 * Operador unario que recibe un individuo y lo copia, para, acorde a una determinada probabilidad
	 * de mutación, operar mutando sus alelos (seleccionando una posicion aleatoria) utilizando el método bitflip.
	 * @param individual
	 * @return 
	 */
	public ArrayList<BinaryRepresentationIndividual> operate(ArrayList<BinaryRepresentationIndividual> ind) {
		BinaryRepresentationIndividual individual = ind.get(0); //copy?
		int[] allele = individual.getAllele();
		Random rand = new Random();
		int limitPosition = allele.length;
		int choosenPos = rand.nextInt(limitPosition);
		allele[choosenPos] = allele[choosenPos]==0 ? 1:0;
		ArrayList<BinaryRepresentationIndividual> out = new ArrayList<BinaryRepresentationIndividual>();
		out.add(individual);
		return out;
	}
}
