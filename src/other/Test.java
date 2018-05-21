package other;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import generics.Individual;
import generics.Population;
import operators.OnePointCrossoverOperator;

public class Test {
	public static void main(String args[]) {
		double asd = 1.111323122f;
		System.out.println(round(asd));
	}
	
	public static String round(double value) {
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(value);
	}
}
