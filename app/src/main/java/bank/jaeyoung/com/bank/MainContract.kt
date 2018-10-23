package bank.jaeyoung.com.bank

import android.content.Context
import android.widget.Toast
import bank.jaeyoung.com.bank.data.TransactionData

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
interface MainContract {
    interface View {
        val context: Context

        fun showProgress()

        fun hideProgress()

        fun refreshData(transactionData: TransactionData)

        fun showToast(toast: Toast)
    }

    interface Presenter {
        val context: Context
    }

    interface Model {
        fun loadData(rawFile: Int): TransactionData?
    }

}
