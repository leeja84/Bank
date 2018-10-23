package bank.jaeyoung.com.bank;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import bank.jaeyoung.com.bank.data.TransactionData;
import bank.jaeyoung.com.bank.presenter.MainPresenter;
import org.junit.Test;

import java.util.Collections;

/**
 * Created by jaeyoung lee on 2018. 10. 22..
 */
public class MainModelTest {
	public MainPresenter getMainPresenter() {
		return presenter;
	}

	TransactionData transactionData;
	MainPresenter presenter = getMainPresenter();

	@Test
	public void loadData() {

		Context appContext = InstrumentationRegistry.getTargetContext();

		Resources resources = presenter.getContext().getResources();
		transactionData = JsonLoader.INSTANCE.getLoadTransation(resources, R.raw.transactions);


		//do daily trasaction descending sorting
		DescendingSort descendingSort = new DescendingSort();
		Collections.sort(transactionData.getDetailTransactions(), descendingSort);
	}
}