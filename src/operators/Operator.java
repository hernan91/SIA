package operators;

import generics.Population;
import java.util.Iterator;
import generics.Individual;

public abstract class Operator {
	public abstract Iterator<Individual> execute(Population population);
}
