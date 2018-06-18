package mutationOperators;

import java.util.Random;

import individuals.Individual;
import individuals.SensorsProblemIndividual;
import operatorsModels.MutationOperator;
import others.Location;
import problemData.ProblemData;
import problemData.SensorsProblemData;

public class SingleVortexNeighborhoodMutationOperator extends MutationOperator {

	public SingleVortexNeighborhoodMutationOperator(ProblemData problemData, float mutationProbability) {
		super(problemData, mutationProbability);
	}

	@Override
	public void mutate(Individual ind) {
		int currentGen = getProblemData().getCurrentGeneration();
		SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
		Random rand = new Random();
		int maxGen = getProblemData().getMaxGen();
		int coverageRatio = ((SensorsProblemData)(getProblemData())).getSensorsFieldProblemData().getTransmissorRangeRatio();
		int choosenPos = rand.nextInt(individual.getAlleleLength()-1);
		Location loc = individual.getTransmissorsPositions()[choosenPos];
		while(loc==null) {
			choosenPos = rand.nextInt(individual.getAlleleLength()-1);
			loc = individual.getTransmissorsPositions()[choosenPos];
		}
		float randomAngle = rand.nextFloat()*360;
		double optimalDistance = Math.sqrt(3)*coverageRatio * (1-((float)currentGen/maxGen));
		int x = (int) (loc.getPosX() +  optimalDistance*Math.cos(randomAngle));
		int y = (int) (loc.getPosY() +  optimalDistance*Math.sin(randomAngle));
		//Hacer bien lo del current gen
		loc.setPosX(x);
		loc.setPosY(y);
	}
}
