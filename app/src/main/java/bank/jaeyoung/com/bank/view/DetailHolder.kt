package bank.jaeyoung.com.bank.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import bank.jaeyoung.com.bank.R

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
class DetailHolder(view: View) : RecyclerView.ViewHolder(view) {
    lateinit var tvAmount: TextView
    lateinit var tvDescription: TextView
    lateinit var tvDate: TextView

    init {
        initView()
    }

    private fun initView() {
        tvAmount = itemView.findViewById(R.id.tv_amount)
        tvDescription = itemView.findViewById(R.id.tv_description)
        tvDate = itemView.findViewById(R.id.tv_date)
    }
}