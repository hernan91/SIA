package operators;

import java.util.ArrayList;

import generics.Individual;
import generics.ProblemData;

public abstract class Operator {
	private ProblemData problemData;
	
	public Operator(ProblemData problemData) {
		this.problemData = problemData;
	}
	
	public abstract ArrayList<Individual> operate(ArrayList<Individual> individuals);

	public ProblemData getProblemData() {
		return problemData;
	}

	public void setProblemData(ProblemData problemData) {
		this.problemData = problemData;
	}
}
