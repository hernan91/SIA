package operators;

import java.util.ArrayList;
import java.util.Random;

import generics.BinaryRepresentationIndividual;

public class ThreePointCrossoverOperator extends Operator{
	@Override
	public ArrayList<BinaryRepresentationIndividual> operate(ArrayList<BinaryRepresentationIndividual> individuals) {
		Random rand = new Random();
		BinaryRepresentationIndividual individual1 = individuals.get(0);
		BinaryRepresentationIndividual individual2 = individuals.get(1);
		int[] allele1 = individual1.getAllele();
		int[] allele2 = individual2.getAllele();
		ArrayList<BinaryRepresentationIndividual> offspringIndividuals = new ArrayList<BinaryRepresentationIndividual>();
		int alleleLength = allele1.length;
		int cutPoint1 = rand.nextInt(alleleLength-3)+1;
		int cutPoint2 = new Random().nextInt(alleleLength-cutPoint1-2)+cutPoint1;
		int cutPoint3 = cutPoint2 + 1 +rand.nextInt(alleleLength-cutPoint2-2);
		for(int i=cutPoint1; i<alleleLength; i++) {
			int k = allele1[i];
			allele1[i] = allele2[i];
			allele2[i] = k;
			if(i==cutPoint2) i=cutPoint3-1;
		}
		offspringIndividuals.add(individual1);
		offspringIndividuals.add(individual2);
		return offspringIndividuals;
	}
}
