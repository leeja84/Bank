package bank.jaeyoung.com.bank;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bank.jaeyoung.com.bank.Model.DetailTransaction;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class DetailTrasactionAdapter extends RecyclerView.Adapter<DetailHolder> {
	private String balance;
	private String beforeBalance;
	private List<DetailTransaction> detailTransactions;

	public DetailTrasactionAdapter(List<DetailTransaction> detailTransactions, String balance) {
		this.detailTransactions = detailTransactions;
		this.balance = balance;
	}

	@NonNull
	@Override
	public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
		return new DetailHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
		DetailTransaction detailTransaction = detailTransactions.get(position); // 가져온 데이터의 리스트

		holder.tvId.setText(detailTransaction.getId());
		holder.tvDescription.setText(detailTransaction.getDescription());
		holder.tvOtherAccount.setText(detailTransaction.getOtherAccount());
		holder.tvDate.setText(detailTransaction.getDate());

		if (NumberUtils.isParsable(detailTransaction.getAmount())) {
			if (detailTransaction.getAmount().startsWith("-")) {
				holder.tvAmount.setTextColor(Color.BLUE);
				holder.tvAmount.setText("Outcoming : "+detailTransaction.getAmount());
			} else {
				holder.tvAmount.setTextColor(Color.RED);
				holder.tvAmount.setText("Incoming : " + detailTransaction.getAmount());
			}
			holder.tvAfterBalance.setText("after : " + balance);
			beforeBalance = Calculator.calculatePreviousBalance(balance, detailTransaction.getAmount());
			balance = beforeBalance;
			holder.tvBeforeBalance.setText("before : "+ beforeBalance);
		}
	}

	@Override
	public int getItemCount() {
		return  (null != detailTransactions ? detailTransactions.size() : 0);
	}
}
