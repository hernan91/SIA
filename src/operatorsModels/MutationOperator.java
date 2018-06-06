package operatorsModels;

import individuals.Individual;
import problemData.ProblemData;

public abstract class MutationOperator extends Operator{
	public MutationOperator(ProblemData problemData) {
		super(problemData);
	}

	public abstract Individual operate(Individual individual);
}
