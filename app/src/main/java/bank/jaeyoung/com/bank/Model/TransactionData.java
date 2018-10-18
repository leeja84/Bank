package bank.jaeyoung.com.bank.Model;

import java.util.List;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class TransactionData {
	private String account;
	private String balance;
	private List<DetailTransaction> detailTransactions;

	public TransactionData(String account, String balance, List<DetailTransaction> detailTransactions) {
		this.account = account;
		this.balance = balance;
		this.detailTransactions = detailTransactions;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public List<DetailTransaction> getDetailTransactions() {
		return detailTransactions;
	}

	public void setDetailTransactions(List<DetailTransaction> detailTransactions) {
		this.detailTransactions = detailTransactions;
	}
}
