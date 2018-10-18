package bank.jaeyoung.com.bank.data;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class DetailTransaction {
	private String id;
	private String amount;
	private String description;
	private String otherAccount;
	private String date;
	private String beforeBalance;
	private String afterBalance;

	public DetailTransaction(String id, String amount, String description, String otherAccount, String date,
		String beforeBalance, String afterBalance) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.otherAccount = otherAccount;
		this.date = date;
		this.beforeBalance = beforeBalance;
		this.afterBalance = afterBalance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(String beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public String getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(String afterBalance) {
		this.afterBalance = afterBalance;
	}
}
