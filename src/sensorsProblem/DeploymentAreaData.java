package sensorsProblem;

public class DeploymentAreaData {
	private int transmissorRangeRatio;
	private int gridSizeX;
	private int gridSizeY;
	
	public DeploymentAreaData(int sensorRatio, int gridSizeX, int gridSizeY) {
		super();
		this.transmissorRangeRatio = sensorRatio;
		this.gridSizeX = gridSizeX;
		this.gridSizeY = gridSizeY;
	}

	public int getTransmissorRangeRatio() {
		return transmissorRangeRatio;
	}

	public void setSensorRatio(int sensorRatio) {
		this.transmissorRangeRatio = sensorRatio;
	}

	public int getGridSizeX() {
		return gridSizeX;
	}

	public void setGridSizeX(int gridSizeX) {
		this.gridSizeX = gridSizeX;
	}

	public int getGridSizeY() {
		return gridSizeY;
	}

	public void setGridSizeY(int gridSizeY) {
		this.gridSizeY = gridSizeY;
	}
	
	public int getGridSize() {
		return gridSizeX*gridSizeY;
	}
}
