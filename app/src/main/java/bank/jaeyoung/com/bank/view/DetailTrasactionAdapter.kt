package bank.jaeyoung.com.bank.view

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bank.jaeyoung.com.bank.R
import bank.jaeyoung.com.bank.data.DetailTransaction
import bank.jaeyoung.com.bank.data.TransactionData
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.math.NumberUtils

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
class DetailTrasactionAdapter : RecyclerView.Adapter<DetailHolder>() {
    private var detailTransactionList: List<DetailTransaction>? = null

    private var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View, position: Int, transactions: List<DetailTransaction>?)
    }

    fun setItemClick(itemClick: ItemClick) {
        this.itemClick = itemClick
    }

    // setData and notify at adapter.
    fun setDatas(transactionData: TransactionData) {
        this.detailTransactionList = transactionData.detailTransactions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return DetailHolder(view)
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        val detailTransaction = detailTransactionList!![position]

        holder.tvDescription.text = detailTransaction.description
        holder.tvDate.text = detailTransaction.date

        if (NumberUtils.isParsable(detailTransaction.amount)) {
            holder.tvAmount.text = detailTransaction.amount

            StringUtils.startsWith(detailTransaction.amount!!, PREFIX)

            if (StringUtils.startsWith(detailTransaction.amount!!, PREFIX)) {
                holder.tvAmount.setTextColor(Color.BLUE)
            } else {
                holder.tvAmount.setTextColor(Color.RED)
            }
        } else {
            holder.tvAmount.text = ZERO
            holder.tvAmount.setTextColor(Color.BLACK)
        }

        holder.itemView.setOnClickListener { v ->
            if (itemClick != null) {
                itemClick!!.onClick(v, position, detailTransactionList)
            }
        }

    }

    override fun getItemCount(): Int {
        return if (null != detailTransactionList) detailTransactionList!!.size else 0
    }

    companion object {
        const val PREFIX = "-"
        const val ZERO = "0"
    }
}
