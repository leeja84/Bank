package bank.jaeyoung.com.bank.presenter

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import bank.jaeyoung.com.bank.MainContract
import bank.jaeyoung.com.bank.R
import bank.jaeyoung.com.bank.data.TransactionData

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var model: MainContract.Model? = null

    override val context: Context
        get() = view.context

    fun setModel(model: MainContract.Model, rawFile: Int) {
        this.model = model
        loadData(rawFile)
    }

    //load transaction from json by async for Large Data.
    private fun loadData(rawFile: Int) {
        try {
            object : AsyncTask<Void, Void, TransactionData>() {
                override fun onPreExecute() {
                    super.onPreExecute()
                    view.showProgress()
                }

                override fun doInBackground(vararg voids: Void): TransactionData? {
                    return model!!.loadData(rawFile)
                }

                override fun onPostExecute(result: TransactionData?) {
                    try {
                        view.hideProgress()
                        if (result == null) {
                            view.showToast(makeToast(context.getString(R.string.file_error)))
                        } else {
                            view.refreshData(result)
                        }
                    } catch (e: NullPointerException) {
                        Log.e("MainPresenter", "onPostExecute - NullPointerException")
                        e.printStackTrace()
                    }

                }
            }.execute()
        } catch (e: Exception) {
            Log.e("MainPresenter", "loadData - Exception")
            e.printStackTrace()
        }

    }

    private fun makeToast(message: String): Toast {
        return Toast.makeText(context, message, Toast.LENGTH_LONG)
    }
}