package bank.jaeyoung.com.bank

import java.text.DecimalFormat

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
object Calculator {
    fun calculatePreviousBalance(balance: String, amount: String): String {
        val df = DecimalFormat("#.00")
        return df.format(java.lang.Double.parseDouble(balance) - java.lang.Double.parseDouble(amount))
    }
}
