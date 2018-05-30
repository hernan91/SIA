package operators;

import java.util.ArrayList;
import java.util.Random;

import individuals.BinaryRepresentationIndividual;
import individuals.Individual;
import problemData.ProblemData;

public class BitFlipMutationOperator extends Operator{
	
	public BitFlipMutationOperator(ProblemData problemData) {
		super(problemData);

	}

	/**
	 * Operador unario que recibe un individuo y lo copia, para, acorde a una determinada probabilidad
	 * de mutación, operar mutando sus alelos (seleccionando una posicion aleatoria) utilizando el método bitflip.
	 * @param individual
	 * @return 
	 */
	public ArrayList<Individual> operate(ArrayList<Individual> ind) {
		BinaryRepresentationIndividual individual = (BinaryRepresentationIndividual) ind.get(0); //copy?
		int[] allele = individual.getAllele();
		Random rand = new Random();
		int limitPosition = allele.length;
		int choosenPos = rand.nextInt(limitPosition);
		allele[choosenPos] = allele[choosenPos]==0 ? 1:0;
		ArrayList<Individual> out = new ArrayList<>();
		out.add(individual);
		return out;
	}
}
