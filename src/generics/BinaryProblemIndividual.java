package generics;

public class BinaryProblemIndividual extends BinaryRepresentationIndividual{

	public BinaryProblemIndividual(int[] allele, ObjectiveFunction objFunc) {
		super(allele);
		this.setFitness(objFunc.getFitness(this));
	}

	@Override
	public Individual copy(ObjectiveFunction) {
		int[] oldAllele = this.getAllele();
		int[] newAllele = new int[oldAllele.length];
		for(int i=0; i<newAllele.length; i++) {
			newAllele[i] = oldAllele[i];
		}
		return new BinaryProblemIndividual(newAllele, this.getFitness());
	}
}
