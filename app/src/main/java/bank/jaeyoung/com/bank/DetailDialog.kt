package bank.jaeyoung.com.bank

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.math.NumberUtils

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
class DetailDialog(context: Context, private val id: String, private val amount: String, private val description: String, private val otherAccount: String, private val date: String,
                   private val beforeBalance: String, private val afterBalance: String, private val onClickListener: View.OnClickListener?) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    private var tvId: TextView? = null
    private var tvAmount: TextView? = null
    private var tvDescription: TextView? = null
    private var tvOtherAccount: TextView? = null
    private var tvDate: TextView? = null
    private var tvBeforeBalance: TextView? = null
    private var tvAfterBalance: TextView? = null
    private var btnOk: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow

        setContentView(R.layout.dialog_main)

        initView()
        setData()
    }

    private fun setData() {
        tvId!!.text = context.getString(R.string.id) + id
        tvDescription!!.text = context.getString(R.string.description) + description
        tvOtherAccount!!.text = context.getString(R.string.other_account) + otherAccount
        tvDate!!.text = context.getString(R.string.date) + date
        tvBeforeBalance!!.text = context.getString(R.string.before_balance) + beforeBalance
        tvAfterBalance!!.text = context.getString(R.string.after_balance) + afterBalance

        tvAmount!!.text = context.getString(R.string.amount) + amount

        if (NumberUtils.isParsable(amount)) {
            if (StringUtils.startsWith(amount!!, DetailDialog.PREFIX)) {
                tvAmount!!.setTextColor(Color.BLUE)
            } else {
                tvAmount!!.setTextColor(Color.RED)
            }

        }

        if (onClickListener != null) {
            btnOk!!.setOnClickListener(onClickListener)
        }
    }


    private fun initView() {
        tvId = findViewById(R.id.tv_dialog_id)
        tvAmount = findViewById(R.id.tv_dialog_amount)
        tvDescription = findViewById(R.id.tv_dialog_description)
        tvOtherAccount = findViewById(R.id.tv_dialog_other_account)
        tvDate = findViewById(R.id.tv_dialog_date)
        tvBeforeBalance = findViewById(R.id.tv_dialog_before)
        tvAfterBalance = findViewById(R.id.tv_dialog_after)

        btnOk = findViewById(R.id.btn_ok)
    }

    companion object {
        const val PREFIX = "-"
    }
}