package other;

import java.util.ArrayList;

import generics.BinaryRepresentationIndividual;
import generics.Location;
import sensorsProblem.SensorsFieldData;
import sensorsProblem.CircularRatioObjectiveFunction;
import sensorsProblem.SensorsProblemObjectiveFunction;
import sensorsProblem.SquareRatioObjectiveFunction;

public class DrawCoverageGridTest {

	public static void main(String[] args) {
		String draw = "cuadrado";
		int[] allele = {1};
		//int[] allele = {1,1,1,1,1,1,1,1,1};
		BinaryRepresentationIndividual ind = new BinaryRepresentationIndividual(allele);
		ArrayList<Location> transmissorsPositions = new ArrayList<Location>();
		transmissorsPositions.add(new Location(30,30));
		SensorsProblemObjectiveFunction obj;
		int[][] coverageGrid;
		
		if(draw=="circulo") {
			obj = new CircularRatioObjectiveFunction(new SensorsFieldData(20, 60, 60),transmissorsPositions,2f);
			coverageGrid = obj.getCoverageGrid(ind);
			System.out.println(obj.getFitness(ind));
			CsvWriter.writeGrid("circularGrid.csv", coverageGrid);
			CsvWriter.writeSolution("", "circularGridSolution.csv", ind); //////SACARRRRRRRRR ""
		}
		else if(draw=="cuadrado") {
			obj = new SquareRatioObjectiveFunction(new SensorsFieldData(10, 60, 60),transmissorsPositions,2f);
			coverageGrid = obj.getCoverageGrid(ind);
			System.out.println(obj.getFitness(ind));
			CsvWriter.writeGrid("circularGrid.csv", coverageGrid);
			CsvWriter.writeSolution("", "squareGridSolution.csv", ind);
		}
		else{
			System.out.println("Fail");
			System.exit(0);
		}
	}
}