package bank.jaeyoung.com.bank.model

import bank.jaeyoung.com.bank.Calculator
import bank.jaeyoung.com.bank.DescendingSort
import bank.jaeyoung.com.bank.JsonLoader
import bank.jaeyoung.com.bank.MainContract
import bank.jaeyoung.com.bank.data.DetailTransaction
import bank.jaeyoung.com.bank.data.TransactionData
import java.util.*

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
class MainModel(private val presenter: MainContract.Presenter) : MainContract.Model {
    internal var transactionData: TransactionData? = null

    override fun loadData(rawFile: Int): TransactionData? {
        val resources = presenter.context.resources
        transactionData = JsonLoader.getLoadTransation(resources, rawFile)

        if (transactionData == null) {
            return null
        }

        //do daily trasaction descending sorting
        val descendingSort = DescendingSort()
        Collections.sort(transactionData!!.detailTransactions!!, descendingSort)

        transactionData = getNewTransactions(transactionData!!, transactionData!!.account,
                transactionData!!.balance)

        return transactionData
    }

    //for Before & after Balance
    private fun getNewTransactions(transactionData: TransactionData, account: String?, balance: String?): TransactionData {
        val detailTransaction = transactionData.detailTransactions
        val tempTransactionList = ArrayList<DetailTransaction>()
        var afterBalance = balance
        var beforeBalance: String

        for (i in 0 until detailTransaction!!.size) {
            beforeBalance = Calculator.calculatePreviousBalance(afterBalance!!, detailTransaction[i].amount!!)
            val tempTransaction = DetailTransaction(detailTransaction[i].id,
                    detailTransaction[i].amount,
                    detailTransaction[i].description, detailTransaction[i].otherAccount,
                    detailTransaction[i].date, beforeBalance,
                    afterBalance)
            tempTransactionList.add(tempTransaction)
            afterBalance = beforeBalance
        }
        return TransactionData(account, balance, tempTransactionList)
    }
}
