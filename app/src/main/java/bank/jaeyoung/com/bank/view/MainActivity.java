package bank.jaeyoung.com.bank.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import bank.jaeyoung.com.bank.DetailDialog;
import bank.jaeyoung.com.bank.MainContract;
import bank.jaeyoung.com.bank.R;
import bank.jaeyoung.com.bank.data.DetailTransaction;
import bank.jaeyoung.com.bank.data.TransactionData;
import bank.jaeyoung.com.bank.model.MainModel;
import bank.jaeyoung.com.bank.presenter.MainPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.List;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {
	@BindView(R.id.progressbar) ProgressBar progressBar;
	@BindView(R.id.tv_account) TextView tvAccount;
	@BindView(R.id.tv_balance) TextView tvBalance;
	@BindView(R.id.rv_detail_transaction) RecyclerView recyclerView;

	private DetailTrasactionAdapter adapter;
	private DetailDialog detailDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initMVP();
	}

	private void initMVP() {
		MainPresenter mainPresenter = new MainPresenter(this);
		MainModel mainModel = new MainModel(mainPresenter);
		mainPresenter.setModel(mainModel, R.raw.transactions);
	}

	// initialize views(progress, textviews, recyclerview)
	private void initView() {
		ButterKnife.bind(this);

		adapter = new DetailTrasactionAdapter();
		recyclerView.setAdapter(adapter);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(linearLayoutManager);

		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
			linearLayoutManager.getOrientation());
		recyclerView.addItemDecoration(dividerItemDecoration);

		adapter.setItemClick(new DetailTrasactionAdapter.ItemClick() {
			@Override public void onClick(View view, int position, List<DetailTransaction> newTransactions) {
				DetailTransaction transaction = newTransactions.get(position);
				showDialog(transaction);
			}

			//open DetailTransaction Dialog
			private void showDialog(DetailTransaction transaction) {
				detailDialog = new DetailDialog(MainActivity.this, transaction.getId(),
					transaction.getAmount(), transaction.getDescription(),
					transaction.getOtherAccount(), transaction.getDate(),
					transaction.getBeforeBalance(), transaction.getAfterBalance()
				);
				detailDialog.show();
			}

		});
	}

	@Override public Context getContext() {
		return this;
	}

	@Override public void showProgress() {
		//if you want to see progressBar. you should put bigData in Resource.
		//already checked it.
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override public void hideProgress() {
		progressBar.setVisibility(View.GONE);
	}

	@Override public void refreshData(TransactionData transactionData) {
		tvAccount.setText(getString(R.string.account) + transactionData.getAccount());
		tvBalance.setText(getString(R.string.balance) + transactionData.getBalance());
		adapter.setDatas(transactionData);
	}

	@Override protected void onDestroy() {
		super.onDestroy();
		if (detailDialog != null && detailDialog.isShowing()) {
			detailDialog.dismiss();
		}
	}
}
