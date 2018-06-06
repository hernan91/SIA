package operatorsModels;

import individuals.Individual;
import problemData.ProblemData;

public abstract class CrossoverOperator extends Operator{
	public CrossoverOperator(ProblemData problemData) {
		super(problemData);
	}

	public abstract void operate(Individual individual1, Individual individual2);
}
