package bank.jaeyoung.com.bank.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import bank.jaeyoung.com.bank.DetailDialog
import bank.jaeyoung.com.bank.MainContract
import bank.jaeyoung.com.bank.R
import bank.jaeyoung.com.bank.data.DetailTransaction
import bank.jaeyoung.com.bank.data.TransactionData
import bank.jaeyoung.com.bank.model.MainModel
import bank.jaeyoung.com.bank.presenter.MainPresenter

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
class MainActivity : AppCompatActivity(), MainContract.View {
    private var progressBar: ProgressBar? = null

    private var tvAccount: TextView? = null
    private var tvBalance: TextView? = null

    private var recyclerView: RecyclerView? = null
    private var adapter: DetailTrasactionAdapter? = null

    private var detailDialog: DetailDialog? = null

    private val clickListener = View.OnClickListener { detailDialog!!.dismiss() }

    override val context: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initMVP()
    }

    private fun initMVP() {
        val mainPresenter = MainPresenter(this)
        val mainModel = MainModel(mainPresenter)
        mainPresenter.setModel(mainModel, R.raw.transactions)
    }

    // initialize views(progress, textviews, recyclerview)
    private fun initView() {
        progressBar = findViewById(R.id.progressbar)

        tvAccount = findViewById(R.id.tv_account)
        tvBalance = findViewById(R.id.tv_balance)

        recyclerView = findViewById(R.id.rv_detail_transaction)

        adapter = DetailTrasactionAdapter()
        recyclerView!!.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(this,
                linearLayoutManager.orientation)
        recyclerView!!.addItemDecoration(dividerItemDecoration)

        adapter!!.setItemClick(object : DetailTrasactionAdapter.ItemClick {
            override fun onClick(view: View, position: Int, newTransactions: List<DetailTransaction>?) {
                val transaction = newTransactions!![position]
                showDialog(transaction)
            }



        })
    }

    //open DetailTransaction Dialog
    private fun showDialog(transaction: DetailTransaction) {
        detailDialog = DetailDialog(this@MainActivity, transaction.id!!,
                transaction.amount!!, transaction.description!!,
                transaction.otherAccount!!, transaction.date!!,
                transaction.beforeBalance!!, transaction.afterBalance!!,
                clickListener)
        detailDialog!!.show()
    }

    override fun showProgress() {
        //if you want to see progressBar. you should put bigData in Resource.
        //already checked it.
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar!!.visibility = View.GONE
    }

    override fun refreshData(transactionData: TransactionData) {
        tvAccount!!.text = getString(R.string.account) + transactionData.account!!
        tvBalance!!.text = getString(R.string.balance) + transactionData.balance!!
        adapter!!.setDatas(transactionData)
    }

    override fun showToast(toast: Toast) {
        toast.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (detailDialog != null && detailDialog!!.isShowing) {
            detailDialog!!.dismiss()
        }
    }
}
