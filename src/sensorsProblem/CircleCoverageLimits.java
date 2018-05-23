package sensorsProblem;

import generics.Location;

public class CircleCoverageLimits extends CoverageLimits{
	public CircleCoverageLimits(Location transmissorLocation, SensorFieldData conf) {
		super(transmissorLocation, conf);
		limSupX = Math.min( transmissorLocation.getPosX()+conf.getTransmissorRangeRatio()+1, conf.getGridSizeX() );
		limSupY = Math.min( transmissorLocation.getPosY()+conf.getTransmissorRangeRatio()+1, conf.getGridSizeY() );
	}
}
