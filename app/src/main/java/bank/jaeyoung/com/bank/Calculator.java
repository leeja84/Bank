package bank.jaeyoung.com.bank;

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
public class Calculator {
	public static String calculatePreviousBalance(String balance, String amount) {
		return String.valueOf(Double.parseDouble(balance) - Double.parseDouble(amount));
	}
}
