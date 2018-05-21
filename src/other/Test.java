package other;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Test {
	public static void main(String args[]) {
		double asd = 1.111323122f;
		System.out.println(round(asd));
	}
	
	public static String round(double value) {
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator(',');
		DecimalFormat df = new DecimalFormat("#.####", symbol);
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(value);
	}
}
