package operatorsModels;

import problemData.ProblemData;

public abstract class Operator {
	private ProblemData problemData;
	
	public Operator(ProblemData problemData) {
		this.problemData = problemData;
	}

	public ProblemData getProblemData() {
		return problemData;
	}

	public void setProblemData(ProblemData problemData) {
		this.problemData = problemData;
	}
}