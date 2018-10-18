package bank.jaeyoung.com.bank.model;

import android.content.res.Resources;
import bank.jaeyoung.com.bank.Calculator;
import bank.jaeyoung.com.bank.DescendingSort;
import bank.jaeyoung.com.bank.JsonLoader;
import bank.jaeyoung.com.bank.MainContract;
import bank.jaeyoung.com.bank.data.DetailTransaction;
import bank.jaeyoung.com.bank.data.TransactionData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
public class MainModel implements MainContract.Model {
	private MainContract.Presenter presenter;
	TransactionData transactionData;

	public MainModel(MainContract.Presenter presenter) {
		this.presenter = presenter;
	}

	@Override public TransactionData loadData(int rawFile) {
		Resources resources = presenter.getContext().getResources();
		transactionData = JsonLoader.getLoadTransation(resources, rawFile);

		//do daily trasaction descending sorting
		DescendingSort descendingSort = new DescendingSort();
		Collections.sort(transactionData.getDetailTransactions(), descendingSort);

		transactionData = getNewTransactions(transactionData, transactionData.getAccount(),
			transactionData.getBalance());

		return transactionData;
	}

	//for Before & after Balance
	private TransactionData getNewTransactions(TransactionData transactionData, String account, String balance) {
		List<DetailTransaction> detailTransaction = transactionData.getDetailTransactions();
		List<DetailTransaction> tempTransactionList = new ArrayList<>();
		String afterBalance = balance;
		String beforeBalance;

		for (int i = 0; i < detailTransaction.size(); i++) {
			beforeBalance = Calculator.calculatePreviousBalance(afterBalance, detailTransaction.get(i).getAmount());
			DetailTransaction tempTransaction = new DetailTransaction(detailTransaction.get(i).getId(),
				detailTransaction.get(i).getAmount(),
				detailTransaction.get(i).getDescription(), detailTransaction.get(i).getOtherAccount(),
				detailTransaction.get(i).getDate(), beforeBalance,
				afterBalance);
			tempTransactionList.add(tempTransaction);
			afterBalance = beforeBalance;
		}
		TransactionData tempTransactionData = new TransactionData(account, balance, tempTransactionList);
		return tempTransactionData;
	}
}
