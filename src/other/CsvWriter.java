package other;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sensorsProblem.Location;

public class CsvWriter {

	public static void writeCsvFile(String fileName, ArrayList<Location> locationsArray) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			fileWriter.append("x,y\n");
			for (Location location : locationsArray) {
				fileWriter.append(String.valueOf(location.getPosX()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(location.getPosX()));
				fileWriter.append("\n");

			}
			System.out.println("Escrito correctamente");
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
