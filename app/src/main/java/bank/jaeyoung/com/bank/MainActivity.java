package bank.jaeyoung.com.bank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import bank.jaeyoung.com.bank.Model.TransactionData;

import java.util.Collections;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

	}

	private void init() {
		// 1. load data
		TransactionData transactionData = JsonUtils.parseJson(R.raw.transactions, getApplicationContext());

		if(transactionData == null){
			return;
		}

		// 2. descending sort by date.
		DescendingSort descendingSort = new DescendingSort();
		Collections.sort(transactionData.getDetailTransactions(), descendingSort);

		TextView tvAccount = findViewById(R.id.tv_account);
		tvAccount.setText(transactionData.getAccount());

		TextView tvBalance = findViewById(R.id.tv_balance);
		tvBalance.setText(getString(R.string.balance) +transactionData.getBalance());


		DetailTrasactionAdapter adapter = new DetailTrasactionAdapter(
			transactionData.getDetailTransactions(), transactionData.getBalance());             // 1,2번은 데이터와 어답터 연결 과정.

		RecyclerView recyclerView = findViewById(R.id.rv_detail_transaction);
		recyclerView.setAdapter(adapter);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(linearLayoutManager);


		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
			linearLayoutManager.getOrientation());
		recyclerView.addItemDecoration(dividerItemDecoration);
	}
}
