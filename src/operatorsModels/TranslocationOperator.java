package operatorsModels;

import others.Location;
import others.Population;
import problemData.ProblemData;

public abstract class TranslocationOperator extends Operator{
	float threshold;
	
	public TranslocationOperator(ProblemData problemData, float threshold) {
		super(problemData);
		this.threshold = threshold;
	}
	
	public abstract Population operate(Population population);
	
	protected abstract Location translocate(Location location1, Location location2);

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}
	
}
