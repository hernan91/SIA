package other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import executor.RunConfiguration;
import generics.Individual;

public class POIWriter {
	public static void writeData(String dir, String filename, ArrayList<RunConfiguration> runConfigs) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		for (RunConfiguration runConf : runConfigs) {
			String crossoverProbability = String.valueOf(runConf.getCrossoverProbability());
			String crossoverOperatorShorterName = shortenCrossoverOperator(runConf.getCrossoverOperatorName());
			String genNumber = String.valueOf(runConf.getMaxGen());
			XSSFSheet sheet = workbook.createSheet(crossoverProbability + "-" + crossoverOperatorShorterName + "-" + genNumber);
			int r = 0;
			int column = 0;
			Row row = sheet.createRow(r);
			Cell cell = row.createCell(column);
			cell.setCellValue("Fitness");
			r++;
			for (Individual individual : runConf.getBestIndividualsAfterRun().getIndividuals()) {
				row = sheet.createRow(r);
				cell = row.createCell(column);
				DecimalFormatSymbols symbol = new DecimalFormatSymbols();
				symbol.setDecimalSeparator(',');
				DecimalFormat formatter = new DecimalFormat("#.###", symbol);
				String fit = formatter.format(individual.getFitness());
				cell.setCellValue(fit);
				r++;
			}
			r = 7;
			column = 7;
			StringTokenizer tokenizer = runConf.getInfoTokenizer();
			while (tokenizer.hasMoreTokens()) {
				if (sheet.getRow(r) == null)
					row = sheet.createRow(r);
				row = sheet.getRow(r);
				cell = row.createCell(column);
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

	public static void writeRelevantData(String dir, String filename, ArrayList<RunConfiguration> runConfigs) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		String[] columnHeaders = { "Configuracion", "Mejor fitness", "Peor fitness", "Media", "Desvio estandar" };
		int colNum = 0;
		Row row = sheet.createRow(0);
		for (String header : columnHeaders) {
			Cell cell = row.createCell(colNum++);
			cell.setCellValue(header);
		}
		int rowNum = 1;
		for (RunConfiguration runConf : runConfigs) {
			row = sheet.createRow(rowNum++);
			colNum = 0;
			for (String field : runConf.getRelevantInfo()) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(field);
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

	public static String shortenCrossoverOperator(String crossoverOperatorName) {
		switch (crossoverOperatorName) {
		case "OnePointCrossoverOperator":
			return "1PC";
		case "TwoPointCrossoverOperator":
			return "2PC";
		case "ThreePointCrossoverOperator":
			return "3PC";
		}
		return crossoverOperatorName;
	}
}
