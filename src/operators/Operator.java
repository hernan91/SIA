package operators;

import java.util.ArrayList;

import generics.BinaryRepresentationIndividual;

public abstract class Operator {
	public abstract ArrayList<BinaryRepresentationIndividual> operate(ArrayList<BinaryRepresentationIndividual> individuals);
}
