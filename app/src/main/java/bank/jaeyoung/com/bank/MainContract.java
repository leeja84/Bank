package bank.jaeyoung.com.bank;

import android.content.Context;
import android.widget.Toast;
import bank.jaeyoung.com.bank.data.TransactionData;

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
public interface MainContract {
	interface View {
		Context getContext();

		void showProgress();

		void hideProgress();

		void refreshData(TransactionData transactionData);

		void showToast(Toast toast);
	}

	interface Presenter {
		Context getContext();
	}

	interface Model {
		TransactionData loadData(int rawFile);
	}

}
