package bank.jaeyoung.com.bank

import android.content.res.Resources
import android.util.Log
import bank.jaeyoung.com.bank.data.DetailTransaction
import bank.jaeyoung.com.bank.data.TransactionData
import org.apache.commons.lang3.StringUtils
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
object JsonLoader {
    private val ACCOUNT = "account"
    private val BALANCE = "balance"
    private val TRANSACTIONS = "transactions"
    private val ID = "id"
    private val AMOUNT = "amount"
    private val DESCRIPTION = "description"
    private val OTHER_ACCOUNT = "otherAccount"
    private val DATE = "date"

    //using Scanner, StringBuilder for getting resource
    fun getLoadTransation(resources: Resources, resource: Int): TransactionData? {
        if (resource <= 0) {
            return null
        }
        val inputStream = resources.openRawResource(resource)

        val scanner = Scanner(inputStream)

        val stringBuilder = StringBuilder()

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine())
        }
        return parseJson(stringBuilder.toString())
    }

    private fun parseJson(inputString: String): TransactionData? {
        val account: String
        val balance: String
        val detailTransactions: List<DetailTransaction>
        var transactionData: TransactionData? = null

        try {
            val userTransaction = JSONObject(inputString)
            account = userTransaction.getString(ACCOUNT)
            balance = userTransaction.getString(BALANCE)
            val transactionArray = userTransaction.getJSONArray(TRANSACTIONS)
            detailTransactions = parseInnerJsonArray(transactionArray)

            transactionData = TransactionData(account, balance, detailTransactions)
        } catch (e: JSONException) {
            Log.e("JsonLoader", "parseInnerJson - JSONException")
            e.printStackTrace()
        }

        return transactionData

    }

    //parse inner jsonArray
    private fun parseInnerJsonArray(transactionArray: JSONArray): List<DetailTransaction> {
        var id: String
        var account: String
        var description: String
        var otherAccount: String
        var date: String
        val detailTransactionList = ArrayList<DetailTransaction>()

        for (i in 0 until transactionArray.length()) {
            try {
                val jsonObject = transactionArray.getJSONObject(i)
                id = jsonObject.getString(ID)
                account = jsonObject.getString(AMOUNT)
                description = jsonObject.getString(DESCRIPTION)
                otherAccount = jsonObject.getString(OTHER_ACCOUNT)
                date = jsonObject.getString(DATE)

                val detailTransaction = DetailTransaction(id, account, description, otherAccount,
                        date,
                        StringUtils.EMPTY, StringUtils.EMPTY)
                detailTransactionList.add(detailTransaction)

            } catch (e: JSONException) {
                Log.e("JsonLoader", "readTransactionArray - JSONException")
                e.printStackTrace()
            }

        }
        return detailTransactionList
    }
}
