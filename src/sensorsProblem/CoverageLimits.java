package sensorsProblem;

import generics.Location;

public abstract class CoverageLimits {
	private int limInfX;
	private int limSupX;
	private int limInfY;
	private int limSupY;
	
//	public CoverageLimits(Location transmissorLocation, SearchSpaceProblemData conf) {
//		limInfX = Math.max( transmissorLocation.getPosX()-conf.getTransmissorRangeRatio(), 0);
//		limSupX = Math.min( transmissorLocation.getPosX()+conf.getTransmissorRangeRatio(), conf.getGridSizeX() );
//		limInfY = Math.max( transmissorLocation.getPosY()-conf.getTransmissorRangeRatio(), 0);
//		limSupY = Math.min( transmissorLocation.getPosY()+conf.getTransmissorRangeRatio(), conf.getGridSizeY() );
//	}
	
	public CoverageLimits(Location transmissorLocation, SensorsFieldData conf) {
		limInfX = Math.max( transmissorLocation.getPosX()-conf.getTransmissorRangeRatio(), 0);
		limInfY = Math.max( transmissorLocation.getPosY()-conf.getTransmissorRangeRatio(), 0);
	}


	public void setLimInfX(int limInfX) {
		this.limInfX = limInfX;
	}


	public void setLimSupX(int limSupX) {
		this.limSupX = limSupX;
	}


	public void setLimInfY(int limInfY) {
		this.limInfY = limInfY;
	}


	public void setLimSupY(int limSupY) {
		this.limSupY = limSupY;
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
