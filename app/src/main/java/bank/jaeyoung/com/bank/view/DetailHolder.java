package bank.jaeyoung.com.bank.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import bank.jaeyoung.com.bank.R;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class DetailHolder extends RecyclerView.ViewHolder {
	public TextView tvAmount;
	public TextView tvDescription;
	public TextView tvDate;

	public DetailHolder(View view) {
		super(view);
		initView();
	}

	private void initView() {
		tvAmount = itemView.findViewById(R.id.tv_amount);
		tvDescription = itemView.findViewById(R.id.tv_description);
		tvDate = itemView.findViewById(R.id.tv_date);
	}
}