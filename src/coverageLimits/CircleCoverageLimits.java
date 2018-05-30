package coverageLimits;

import others.Location;
import problemData.SensorsFieldData;

public class CircleCoverageLimits extends CoverageLimits{
	public CircleCoverageLimits(Location transmissorLocation, SensorsFieldData conf) {
		super(transmissorLocation, conf);
		this.setLimSupX(Math.min( transmissorLocation.getPosX()+conf.getTransmissorRangeRatio()+1, conf.getGridSizeX() ));
		this.setLimSupY(Math.min( transmissorLocation.getPosY()+conf.getTransmissorRangeRatio()+1, conf.getGridSizeY() ));
	}
}
