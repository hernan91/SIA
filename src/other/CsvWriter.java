package other;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import executor.RunConfiguration;
import generics.Individual;
import generics.Location;

public class CsvWriter {

	public static void writeLocations(String directory, ArrayList<Location> locationsArray) {
		FileWriter fileWriter = null;
		File dir = new File(directory);
		String filename = "locations.csv";
		try {
			dir.mkdirs();
			fileWriter = new FileWriter(new File(dir.getAbsolutePath(), filename));
			fileWriter.append("x,y\n");
			for (Location location : locationsArray) {
				fileWriter.append(String.valueOf(location.getPosX()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(location.getPosY()));
				fileWriter.append("\n");

			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
	
	public static void writeSolution(String directory, String filename, Individual individual) {
		FileWriter fileWriter = null;
		File dir = new File(directory);
		try {
			dir.mkdirs();
			fileWriter = new FileWriter(new File(dir.getAbsolutePath(), filename+".csv"));
			for(int num : individual.getAllele()) fileWriter.write(String.valueOf(num)+"\n");
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
	
	public static void writeGrid(String fileName, int[][] coverageGrid) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append("x,y\n");
			for(int i=0; i<coverageGrid.length; i++) {
				for(int j=0; j<coverageGrid[0].length; j++) {
					if(coverageGrid[i][j]==1)
						fileWriter.append(i+","+j+"\n");
				}
			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
	
	public static void writeRunConfigurationInfo(String directory, String filename, RunConfiguration runConf) {
		FileWriter fileWriter = null;
		File dir = new File(directory+"/runConf");
		try {
			dir.mkdirs();
			fileWriter = new FileWriter(new File(dir.getAbsolutePath(), filename));
			fileWriter.write("Fitness del mejor individuo de cada generacion\n");
			for(Individual ind : runConf.getBestIndividualsAfterRun().getIndividuals()) {
				fileWriter.append(ind.getFitness()+"\n");
			}
			fileWriter.append("\n\n");
			fileWriter.append(runConf.getInfo());
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
}
