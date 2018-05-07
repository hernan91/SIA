package main;

public class SquareGridProblemData {
	private int transmissorRangeRatio;
	private int gridSizeX;
	private int gridSizeY;
	private int gridSize;
	
	public SquareGridProblemData(int sensorRatio, int gridSizeX, int gridSizeY) {
		super();
		this.transmissorRangeRatio = sensorRatio;
		this.gridSizeX = gridSizeX;
		this.gridSizeY = gridSizeY;
		this.gridSize = gridSizeX*gridSizeY;
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
		return gridSize;
	}
}
