package mutationOperators;

import java.util.Random;

import individuals.Individual;
import individuals.SensorsProblemIndividual;
import operatorsModels.MutationOperator;
import others.Location;
import problemData.ProblemData;
import problemData.SensorsProblemData;

public class SingleVortexNeighborhoodMutationOperator2 extends MutationOperator {
	
	public SingleVortexNeighborhoodMutationOperator2(ProblemData problemData, float mutationProbability) {
		super(problemData, mutationProbability);
	}

	@Override
	public void mutate(Individual ind) {
		int currentGen = getProblemData().getCurrentGeneration();
		SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
		Random rand = new Random();
		int maxGen = getProblemData().getMaxGen();
		int coverageRatio = ((SensorsProblemData)(getProblemData())).getSensorsFieldProblemData().getTransmissorRangeRatio();
		int[] allele = individual.getAllele();
		Location[] transmissorsPositions = individual.getTransmissorsPositions();
		for(int actualPos=0; actualPos<allele.length; actualPos++) {
			float p = rand.nextFloat();
			if(p <= getMutationProbability()) continue;
			if(allele[actualPos]==0) {
				allele[actualPos] = 1;
				individual.moveSensorToRandomLocation(actualPos);
			}
			else {
				float moveOrRemoveProbability = rand.nextFloat();
				if(moveOrRemoveProbability>0.5) {
					Location loc = individual.getTransmissorsPositions()[actualPos];
					float randomAngle = rand.nextFloat()*360;
					double optimalDistance = Math.sqrt(3)*coverageRatio * (1-((float)currentGen/maxGen));
					int x = (int) (loc.getPosX() +  optimalDistance*Math.cos(randomAngle));
					int y = (int) (loc.getPosY() +  optimalDistance*Math.sin(randomAngle));
					loc.setPosX(x);
					loc.setPosY(y);
				}
				else {
					allele[actualPos] = 0;
					transmissorsPositions[actualPos] = null;
				}
			}			
		}		
	}
}
