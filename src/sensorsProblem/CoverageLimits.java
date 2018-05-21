package sensorsProblem;

public abstract class CoverageLimits {
	private int limInfX;
	protected int limSupX;
	private int limInfY;
	protected int limSupY;
	
//	public CoverageLimits(Location transmissorLocation, SearchSpaceProblemData conf) {
//		limInfX = Math.max( transmissorLocation.getPosX()-conf.getTransmissorRangeRatio(), 0);
//		limSupX = Math.min( transmissorLocation.getPosX()+conf.getTransmissorRangeRatio(), conf.getGridSizeX() );
//		limInfY = Math.max( transmissorLocation.getPosY()-conf.getTransmissorRangeRatio(), 0);
//		limSupY = Math.min( transmissorLocation.getPosY()+conf.getTransmissorRangeRatio(), conf.getGridSizeY() );
//	}
	
	public CoverageLimits(Location transmissorLocation, DeploymentAreaData conf) {
		limInfX = Math.max( transmissorLocation.getPosX()-conf.getTransmissorRangeRatio(), 0);
		limInfY = Math.max( transmissorLocation.getPosY()-conf.getTransmissorRangeRatio(), 0);
	}


	public int getLimInfX() {
		return limInfX;
	}

	public int getLimSupX() {
		return limSupX;
	}

	public int getLimInfY() {
		return limInfY;
	}

	public int getLimSupY() {
		return limSupY;
	}
}
