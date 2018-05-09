package strategies;

import generics.ObjectiveFunction;
import generics.Population;

public class ReplacementStrategy {
	public ObjectiveFunction objFunc;
	
	public ReplacementStrategy(ObjectiveFunction objFunc) {
		this.objFunc = objFunc;
	}
	public Population elitistSteadyStateReplacement(Population pop1, Population pop2) {
		//System.out.println("Array pop1= "+pop1.getIndividuals());
		//System.out.println("Array pop2= "+pop2.getIndividuals());
		Population nextPop = pop1.clone();
		//System.out.println("Array nextPop= "+nextPop.getIndividuals());
		nextPop.getIndividuals().addAll(pop2.clone().getIndividuals());
		//System.out.println("Array nextPop= "+nextPop.getIndividuals());
		nextPop.sortByFitness(objFunc);
		//System.out.println("Array nextPop= "+nextPop.getIndividuals());
		//System.out.println("Media pop1= " + pop1.getPopulationFitnessMean());
		//System.out.println("Media pop2= " + pop2.getPopulationFitnessMean());
		//System.out.println("Media nextGen antes del remove= " + nextPop.getPopulationFitnessMean());
		nextPop.removeLastNIndividuals(pop1.getNumberOfIndividuals());
		//System.out.println("Array nextPop= "+nextPop.getIndividuals());
		//System.out.println("Media nextGen despues del remove= " + nextPop.getPopulationFitnessMean());
		return nextPop;
	}
}
