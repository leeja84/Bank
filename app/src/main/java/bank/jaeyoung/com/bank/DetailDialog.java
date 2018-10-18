package bank.jaeyoung.com.bank;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by jaeyoung lee on 2018. 10. 18..
 */
public class DetailDialog extends Dialog {
	public static final String PREFIX = "-";

	private TextView tvId;
	private TextView tvAmount;
	private TextView tvDescription;
	private TextView tvOtherAccount;
	private TextView tvDate;
	private TextView tvBeforeBalance;
	private TextView tvAfterBalance;
	private Button btnOk;

	private String id;
	private String amount;
	private String description;
	private String otherAccount;
	private String date;
	private String beforeBalance;
	private String afterBalance;

	private View.OnClickListener onClickListener;

	public DetailDialog(Context context, String id, String amount, String description, String otherAccount, String date,
		String beforeBalance, String afterBalance, View.OnClickListener singleListener) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.otherAccount = otherAccount;
		this.date = date;
		this.beforeBalance = beforeBalance;
		this.afterBalance = afterBalance;
		this.onClickListener = singleListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
		lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		lpWindow.dimAmount = 0.8f;
		getWindow().setAttributes(lpWindow);

		setContentView(R.layout.dialog_main);

		initView();
		setData();
	}

	private void setData() {
		tvId.setText(getContext().getString(R.string.id) + id);
		tvDescription.setText(getContext().getString(R.string.description) + description);
		tvOtherAccount.setText(getContext().getString(R.string.other_account) + otherAccount);
		tvDate.setText(getContext().getString(R.string.date) + date);
		tvBeforeBalance.setText(getContext().getString(R.string.before_balance) + beforeBalance);
		tvAfterBalance.setText(getContext().getString(R.string.after_balance) + afterBalance);

		tvAmount.setText(getContext().getString(R.string.amount) + amount);

		if (NumberUtils.isParsable(amount)) {
			if (amount.startsWith(PREFIX)) {
				tvAmount.setTextColor(Color.BLUE);
			} else {
				tvAmount.setTextColor(Color.RED);
			}

		}

		if (onClickListener != null) {
			btnOk.setOnClickListener(onClickListener);
		}
	}



	private void initView() {
		tvId = findViewById(R.id.tv_dialog_id);
		tvAmount = findViewById(R.id.tv_dialog_amount);
		tvDescription = findViewById(R.id.tv_dialog_description);
		tvOtherAccount = findViewById(R.id.tv_dialog_other_account);
		tvDate = findViewById(R.id.tv_dialog_date);
		tvBeforeBalance = findViewById(R.id.tv_dialog_before);
		tvAfterBalance = findViewById(R.id.tv_dialog_after);

		btnOk = findViewById(R.id.btn_ok);
	}
}