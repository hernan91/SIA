package generics;

public class BinaryProblemIndividual extends BinaryRepresentationIndividual{

	public BinaryProblemIndividual(int alleleLength) {//objfunc va por e tema del fitness inicializado en 0, no deberia
		super(alleleLength);
	}
	
	public BinaryProblemIndividual(int[] allele, double fitness) {//objfunc va por e tema del fitness inicializado en 0, no deberia
		super(allele, fitness);
	}

	@Override
	public Individual copy() {
		int[] oldAllele = this.getAllele();
		int[] newAllele = new int[oldAllele.length];
		for(int i=0; i<newAllele.length; i++) {
			newAllele[i] = oldAllele[i];
		}
		return new BinaryProblemIndividual(newAllele, getFitness());
	}
}
