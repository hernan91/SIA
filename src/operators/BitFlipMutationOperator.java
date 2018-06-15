package operators;

import java.util.Random;

import individuals.BinaryRepresentationIndividual;
import individuals.Individual;
import operatorsModels.MutationOperator;
import problemData.ProblemData;

public class BitFlipMutationOperator extends MutationOperator{
	
	public BitFlipMutationOperator(ProblemData problemData, float mutationProbability) {
		super(problemData, mutationProbability);

	}

	/**
	 * Operador unario que recibe un individuo y lo copia, para, acorde a una determinada probabilidad
	 * de mutación, operar mutando sus alelos (seleccionando una posicion aleatoria) utilizando el método bitflip.
	 * @param individual
	 * @return 
	 */

	@Override
	public void mutate(Individual ind) {
		BinaryRepresentationIndividual individual = (BinaryRepresentationIndividual) ind; //copy?
		int[] allele = individual.getAllele();
		Random rand = new Random();
		int limitPosition = allele.length;
		int choosenPos = rand.nextInt(limitPosition);
		allele[choosenPos] = allele[choosenPos]==0 ? 1:0;
	}
}
