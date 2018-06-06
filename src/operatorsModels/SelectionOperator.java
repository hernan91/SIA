package operatorsModels;

import java.util.ArrayList;

import individuals.Individual;
import problemData.ProblemData;

public abstract class SelectionOperator extends Operator{
	public SelectionOperator(ProblemData problemData) {
		super(problemData);
	}

	public abstract ArrayList<Individual> operate(ArrayList<Individual> oldGenereation); 
}
