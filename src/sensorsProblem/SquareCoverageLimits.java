package sensorsProblem;

import generics.Location;

//EL radio siempre va a ser de r+chirolas (chirolas=el punto del centro/2)
public class SquareCoverageLimits extends CoverageLimits{
	public SquareCoverageLimits(Location transmissorLocation, SensorFieldData conf) {
		super(transmissorLocation, conf);
		this.setLimSupX(Math.min( transmissorLocation.getPosX()+conf.getTransmissorRangeRatio(), conf.getGridSizeX() ));
		this.setLimSupY(Math.min( transmissorLocation.getPosY()+conf.getTransmissorRangeRatio(), conf.getGridSizeY() ));
	}
}
