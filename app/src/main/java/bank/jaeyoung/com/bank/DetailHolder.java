package bank.jaeyoung.com.bank;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class DetailHolder extends RecyclerView.ViewHolder {
	public TextView tvId;
	public TextView tvAmount;
	public TextView tvDescription;
	public TextView tvOtherAccount;
	public TextView tvDate;
	public TextView tvBeforeBalance;
	public TextView tvAfterBalance;

	public DetailHolder(View view) {
		super(view);

		// 생성자가 생성될 때 xml에 있는 위젯을 소스코드와 연결.
		tvId = itemView.findViewById(R.id.tv_id);
		tvAmount = itemView.findViewById(R.id.tv_amount);
		tvDescription = itemView.findViewById(R.id.tv_description);
		tvOtherAccount = itemView.findViewById(R.id.tv_other_account);
		tvDate = itemView.findViewById(R.id.tv_date);
		tvBeforeBalance = itemView.findViewById(R.id.tv_before_balance);
		tvAfterBalance = itemView.findViewById(R.id.tv_after_balance);
	}
}