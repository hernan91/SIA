package operators;

import java.util.ArrayList;

import generics.BinaryRepresentationIndividual;
import generics.Individual;

public abstract class Operator {
	public abstract ArrayList<Individual> operate(ArrayList<Individual> individuals);
}
