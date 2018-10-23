package bank.jaeyoung.com.bank


import bank.jaeyoung.com.bank.data.DetailTransaction
import java.util.*

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
class DescendingSort : Comparator<DetailTransaction> {
    override fun compare(o1: DetailTransaction, o2: DetailTransaction): Int {
        return o2.date!!.compareTo(o1.date!!)
    }
}
