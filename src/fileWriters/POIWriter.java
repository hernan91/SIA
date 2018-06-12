package fileWriters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import executor.SensorsProblemRunConfiguration;
import individuals.Individual;
import individuals.SensorsProblemIndividual;
import others.Location;

public class POIWriter {
	public static void writeSensorsData(String dir, String filename, ArrayList<SensorsProblemRunConfiguration> runConfigs) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		for (SensorsProblemRunConfiguration runConf : runConfigs) {
			XSSFSheet sheet = workbook.createSheet(runConf.getName());
			int r = 0;
			int column = 0;
			int ejec = 1;
			//Row row = sheet.createRow(r);
			//Cell cell = row.createCell(column);
//			cell.setCellValue("Fitness");
//			row.createCell(column+1).setCellValue("Allele");
//			r++;
			for (Individual ind : runConf.getBestIndividualsAfterRun().getIndividuals()) {
				SensorsProblemIndividual individual = (SensorsProblemIndividual) ind;
				Row titleRow = sheet.createRow(r);
				titleRow.createCell(column).setCellValue("Ejecución nro "+ejec);
				r++;
				Row headRow = sheet.createRow(r);
				headRow.createCell(column).setCellValue("Allele");
				headRow.createCell(column+1).setCellValue("Fitness");
				r++;
				Row dataRow = sheet.createRow(r);				
				dataRow.createCell(column).setCellValue(individual.getAlleleString());
				dataRow.createCell(column+1).setCellValue(formatter(individual.getFitness()));
				r++;
				headRow = sheet.createRow(r);
				headRow.createCell(column).setCellValue("x");
				headRow.createCell(column+1).setCellValue("y");
				r++;
				for(Location location : individual.getTransmissorsPositions()) {
					if(location!=null) {
						Row locationRow = sheet.createRow(r);
						locationRow.createCell(column).setCellValue(location.getPosX());
						locationRow.createCell(column+1).setCellValue(location.getPosY());
						r++;
					}
				}
				r++;
				ejec++;
			}
			r = 7;
			column = 7;
			StringTokenizer tokenizer = runConf.getInfoTokenizer();
			Row row;
			while (tokenizer.hasMoreTokens()) {
				if (sheet.getRow(r) == null)
					row = sheet.createRow(r);
				row = sheet.getRow(r);
				Cell cell = row.createCell(column);
				cell.setCellValue(tokenizer.nextToken());
				r++;
			}
		}
		try {
			File directory = new File(dir + "/runConf");
			directory.mkdirs();
			FileOutputStream outputStream = new FileOutputStream(
					directory.getAbsolutePath() + "/" + filename + ".xlsx");
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeRelevantData(String dir, String filename, ArrayList<SensorsProblemRunConfiguration> runConfigs) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		String[] columnHeaders = { "Configuracion", "Mejor fitness", "Peor fitness", "Media", "Desvio estandar"};
		int colNum = 0;
		Row row = sheet.createRow(0);
		for (String header : columnHeaders) {
			Cell cell = row.createCell(colNum++);
			cell.setCellValue(header);
		}
		int rowNum = 1;
		for (SensorsProblemRunConfiguration runConf : runConfigs) {
			row = sheet.createRow(rowNum++);
			colNum = 0;
			int f=0;
			for (String field : runConf.getRelevantInfo()) {
				Cell cell = row.createCell(colNum++);
				if(f>0) cell.setCellValue(Double.parseDouble(field));
				else cell.setCellValue(field);
				CellStyle style = workbook.createCellStyle();
				DataFormat format = workbook.createDataFormat();
				style.setDataFormat(format.getFormat("0.000"));
				cell.setCellStyle(style);
				f++;
			}
		}
		try {
			File directory = new File(dir);
			directory.mkdirs();
			FileOutputStream outputStream = new FileOutputStream(
					directory.getAbsolutePath() + "/" + filename + ".xlsx");
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String formatter(double num) {
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat formatter = new DecimalFormat("#.###", symbol);
		return formatter.format(num);
	}
}
