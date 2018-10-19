package bank.jaeyoung.com.bank;

import android.content.res.Resources;
import android.util.Log;
import bank.jaeyoung.com.bank.data.DetailTransaction;
import bank.jaeyoung.com.bank.data.TransactionData;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class JsonLoader {
	private static final String ACCOUNT = "account";
	private static final String BALANCE = "balance";
	private static final String TRANSACTIONS = "transactions";
	private static final String ID = "id";
	private static final String AMOUNT = "amount";
	private static final String DESCRIPTION = "description";
	private static final String OTHER_ACCOUNT = "otherAccount";
	private static final String DATE = "date";

	//using Scanner, StringBuilder for getting resource
	public static TransactionData getLoadTransation(Resources resources, int resource) {
		if(resource <= 0){
			return null;
		}
		InputStream inputStream = resources.openRawResource(resource);

		Scanner scanner = new Scanner(inputStream);

		StringBuilder stringBuilder = new StringBuilder();

		while (scanner.hasNextLine()) {
			stringBuilder.append(scanner.nextLine());
		}
		return parseJson(stringBuilder.toString());
	}

	private static TransactionData parseJson(String inputString) {
		String account;
		String balance;
		List<DetailTransaction> detailTransactions;
		TransactionData transactionData = null;

		try {
			JSONObject userTransaction = new JSONObject(inputString);
			account = userTransaction.getString(ACCOUNT);
			balance = userTransaction.getString(BALANCE);
			JSONArray transactionArray = userTransaction.getJSONArray(TRANSACTIONS);
			detailTransactions = parseInnerJsonArray(transactionArray);

			transactionData = new TransactionData(account, balance, detailTransactions);
		} catch (JSONException e) {
			Log.e("JsonLoader", "parseInnerJson - JSONException");
			e.printStackTrace();
		}

		return transactionData;

	}

	//parse inner jsonArray
	private static List<DetailTransaction> parseInnerJsonArray(JSONArray transactionArray) {
		String id;
		String account;
		String description;
		String otherAccount;
		String date;
		List<DetailTransaction> detailTransactionList = new ArrayList<>();

		for (int i = 0; i < transactionArray.length(); i++) {
			try {
				JSONObject jsonObject = transactionArray.getJSONObject(i);
				id = jsonObject.getString(ID);
				account = jsonObject.getString(AMOUNT);
				description = jsonObject.getString(DESCRIPTION);
				otherAccount = jsonObject.getString(OTHER_ACCOUNT);
				date = jsonObject.getString(DATE);

				DetailTransaction detailTransaction = new DetailTransaction(id, account, description, otherAccount,
					date,
					StringUtils.EMPTY, StringUtils.EMPTY);
				detailTransactionList.add(detailTransaction);

			} catch (JSONException e) {
				Log.e("JsonLoader", "readTransactionArray - JSONException");
				e.printStackTrace();
			}
		}
		return detailTransactionList;
	}
}
