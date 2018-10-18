package bank.jaeyoung.com.bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class DateUtils {
	private static final String REGEX = "T";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static String[] splitDate(String date){
		return date.split(REGEX);
	}

	public static void changeDateFormat(String date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		Date parseDate = null;
		try {
			parseDate = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long milliseconds = parseDate.getTime();
	}
}
