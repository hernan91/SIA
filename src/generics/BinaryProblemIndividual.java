package generics;

public class BinaryProblemIndividual extends BinaryRepresentationIndividual{

	public BinaryProblemIndividual(int[] allele) {//objfunc va por e tema del fitness inicializado en 0, no deberia
		super(allele);
	}

	@Override
	public Individual copy() {
		int[] oldAllele = this.getAllele();
		int[] newAllele = new int[oldAllele.length];
		for(int i=0; i<newAllele.length; i++) {
			newAllele[i] = oldAllele[i];
		}
		return new BinaryProblemIndividual(newAllele, this.getFitness());
	}
}
