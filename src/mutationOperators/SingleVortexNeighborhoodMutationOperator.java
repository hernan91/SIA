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
		int[] allele = individual.getAllele();
		if(allele[choosenPos]==0) {
			allele[choosenPos] = 1;
			individual.moveSensorToRandomLocation(choosenPos);
		}
		else {
			float moveOrRemoveProbability = rand.nextFloat();
			if(moveOrRemoveProbability>0.5) {
				float randomAngle = rand.nextFloat()*360;
				
				int numCicles = maxGen/100;
				int cicleLimit = maxGen/numCicles;
				float prop = ((currentGen + cicleLimit) % cicleLimit) / (float)cicleLimit;
				//float prop = ((float)currentGen/maxGen);
				double optimalDistance = Math.sqrt(3)*coverageRatio * (1-prop);
				int x = (int) (loc.getPosX() +  optimalDistance*Math.cos(randomAngle));
				int y = (int) (loc.getPosY() +  optimalDistance*Math.sin(randomAngle));
				loc.setPosX(x);
				loc.setPosY(y);
			}
			else {
				allele[choosenPos] = 0;
				individual.getTransmissorsPositions()[choosenPos] = null;
			}
		}
	}
}
