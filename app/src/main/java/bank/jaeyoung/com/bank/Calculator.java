package bank.jaeyoung.com.bank;

import java.text.DecimalFormat;

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
public class Calculator {
	public static String calculatePreviousBalance(String balance, String amount) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(Double.parseDouble(balance) - Double.parseDouble(amount));
	}
}
