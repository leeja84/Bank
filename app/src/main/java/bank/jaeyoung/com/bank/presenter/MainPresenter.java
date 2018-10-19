package bank.jaeyoung.com.bank.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import bank.jaeyoung.com.bank.MainContract;
import bank.jaeyoung.com.bank.R;
import bank.jaeyoung.com.bank.data.TransactionData;

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
public class MainPresenter implements MainContract.Presenter {
	private MainContract.View view;
	private MainContract.Model model;

	public MainPresenter(MainContract.View view) {
		this.view = view;
	}

	public void setModel(MainContract.Model model, int rawFile) {
		this.model = model;
		loadData(rawFile);
	}

	//load transaction from json by async for Large Data.
	private void loadData(final int rawFile) {
		try {
			new AsyncTask<Void, Void, TransactionData>() {
				@Override protected void onPreExecute() {
					super.onPreExecute();
					view.showProgress();
				}

				@Override
				protected TransactionData doInBackground(Void... voids) {
					return model.loadData(rawFile);
				}

				@Override
				protected void onPostExecute(TransactionData result) {
					try {
						view.hideProgress();
						if (result == null) {
							view.showToast(makeToast(getContext().getString(R.string.file_error)));
						} else {
							view.refreshData(result);
						}
					} catch (NullPointerException e) {
						Log.e("MainPresenter","onPostExecute - NullPointerException");
						e.printStackTrace();
					}
				}
			}.execute();
		} catch (Exception e) {
			Log.e("MainPresenter","loadData - Exception");
			e.printStackTrace();
		}
	}

	private Toast makeToast(String message){
		return Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
	}

	@Override public Context getContext() {
		return view.getContext();
	}
}