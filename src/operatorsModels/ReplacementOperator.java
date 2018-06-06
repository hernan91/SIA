package operatorsModels;

import others.Population;
import problemData.ProblemData;

public abstract class ReplacementOperator extends Operator{
	public ReplacementOperator(ProblemData problemData) {
		super(problemData);
	}

	public abstract Population operate(Population oldGeneration, Population newGeneration);
}
