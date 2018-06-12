package operators;

import java.util.ArrayList;
import java.util.HashSet;

import individuals.Individual;
import individuals.SensorsProblemIndividual;
import operatorsModels.ReplacementOperator;
import others.Population;
import problemData.ProblemData;

public class SensorsProblemSimpleReplacementWithoutRepeatedInd extends ReplacementOperator{
	public SensorsProblemSimpleReplacementWithoutRepeatedInd(ProblemData problemData) {
		super(problemData);
	}

	public Population operate(Population oldGeneration, Population newGeneration) {
		Population replacement = new Population(oldGeneration.copy().getIndividuals(), getProblemData());
		replacement.getIndividuals().addAll(newGeneration.copy().getIndividuals());
		HashSet<SensorsProblemIndividual> hashSet = new HashSet(replacement.getIndividuals());
		replacement.setIndividuals(new ArrayList<Individual>());
		replacement.getIndividuals().addAll(hashSet);
		int popIndividualsNum = oldGeneration.getNumberOfIndividuals();
		getProblemData().getObjFunc().sortPopulationByFitness(replacement.getIndividuals());
		getProblemData().getObjFunc().sortPopulationByFitness(oldGeneration.getIndividuals());
		getProblemData().getObjFunc().sortPopulationByFitness(newGeneration.getIndividuals());
		while(replacement.getNumberOfIndividuals() != popIndividualsNum){
			if(replacement.getNumberOfIndividuals() > popIndividualsNum) {
				replacement.getIndividuals().remove(replacement.getNumberOfIndividuals()-1);
			}
			else {
				replacement.getIndividuals().add(newGeneration.getIndividuals().remove(0));
			}
		}
		getProblemData().getObjFunc().sortPopulationByFitness(replacement.getIndividuals());
		return replacement;
	}
}
