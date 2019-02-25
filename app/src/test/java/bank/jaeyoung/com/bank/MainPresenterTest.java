package bank.jaeyoung.com.bank;

import bank.jaeyoung.com.bank.data.TransactionData;
import bank.jaeyoung.com.bank.presenter.MainPresenter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by jaeyoung lee on 2018. 12. 6..
 */
public class MainPresenterTest {
	private MainContract.View mockView;
	private MainContract.Model mockModel;
	private MainPresenter presenter;

	@Before
	public void setup(){
		mockView = Mockito.mock(MainContract.View.class);
		mockModel = Mockito.mock(MainContract.Model.class);


		presenter = new MainPresenter(mockView);
	}

	@Test
	public void loadTransactionDataFromResource(){
		Assert.assertTrue(mockView != null);
		Assert.assertTrue(mockModel != null);
		Assert.assertTrue(presenter != null);

		TransactionData transactionData = Mockito.mock(TransactionData.class);
		Mockito.when(transactionData.getAccount()).thenReturn("NL30MOYO0001234567");
		Assert.assertTrue("NL30MOYO0001234567".equals(transactionData.getAccount()));

//		.thenReturn(TransactionData);
	}
}
