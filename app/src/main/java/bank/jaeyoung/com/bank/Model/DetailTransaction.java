package bank.jaeyoung.com.bank.Model;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class DetailTransaction {
	private String id;
	private String amount;
	private String description;
	private String otherAccount;
	private String date;

	public DetailTransaction(String id, String amount, String description, String otherAccount, String date) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.otherAccount = otherAccount;
		this.date = date;
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

	public void setAmount(String account) {
		this.amount = account;
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
}
