package operatorsModels;

import others.Location;
import problemData.ProblemData;

public abstract class TranslocationOperator extends Operator{
	float threshold;
	
	public TranslocationOperator(ProblemData problemData, float threshold) {
		super(problemData);
		this.threshold = threshold;
	}
	
	protected abstract Location translocate(Location location1, Location location2);

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}
	
}
