package bank.jaeyoung.com.bank;

import android.content.Context;
import android.content.res.Resources;
import bank.jaeyoung.com.bank.Model.DetailTransaction;
import bank.jaeyoung.com.bank.Model.TransactionData;
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
public class JsonUtils {

	public static TransactionData parseJson(int resource, Context context){
		Resources resources = context.getResources();
		InputStream inputStream = resources.openRawResource(resource);

		Scanner scanner = new Scanner(inputStream);

		StringBuilder stringBuilder = new StringBuilder();

		while (scanner.hasNextLine()) {
			stringBuilder.append(scanner.nextLine());
		}

		return parseInnerJson(stringBuilder.toString());
	}

	private static TransactionData parseInnerJson(String inputString) {
		String account;
		String balance;
		List<DetailTransaction> detailTransactions;
		TransactionData transactionData = null;

		try {
			JSONObject userTransaction = new JSONObject(inputString);
			account = userTransaction.getString("account");
			balance = userTransaction.getString("balance");
			JSONArray transactionArray = userTransaction.getJSONArray("transactions");
			detailTransactions = readTransactionArray(transactionArray);

			transactionData = new TransactionData(account, balance, detailTransactions);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return transactionData;

	}

	private static List<DetailTransaction> readTransactionArray(JSONArray transactionArray) {
		String id;
		String account;
		String description;
		String otherAccount;
		String date;
		List<DetailTransaction> detailTransactionList = new ArrayList<>();

		for (int i = 0; i < transactionArray.length(); i++) {
			try {
				JSONObject jsonObject = transactionArray.getJSONObject(i);
				id = jsonObject.getString("id");
				account = jsonObject.getString("amount");
				description = jsonObject.getString("description");
				otherAccount = jsonObject.getString("otherAccount");
				date = jsonObject.getString("date");

				DetailTransaction detailTransaction = new DetailTransaction(id, account, description, otherAccount, date);
				detailTransactionList.add(detailTransaction);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return detailTransactionList;
	}
}
