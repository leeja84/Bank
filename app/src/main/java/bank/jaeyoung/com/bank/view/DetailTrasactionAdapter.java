package bank.jaeyoung.com.bank.view;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import bank.jaeyoung.com.bank.R;
import bank.jaeyoung.com.bank.data.DetailTransaction;
import bank.jaeyoung.com.bank.data.TransactionData;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class DetailTrasactionAdapter extends RecyclerView.Adapter<DetailHolder> {
	public static final String PREFIX = "-";
	public static final String ZERO = "0";
	private List<DetailTransaction> detailTransactionList;

	private ItemClick itemClick;

	public interface ItemClick {
		void onClick(View view, int position, List<DetailTransaction> transactions);
	}

	public void setItemClick(ItemClick itemClick) {
		this.itemClick = itemClick;
	}

	// setData and notify at adapter.
	public void setDatas(TransactionData transactionData) {
		this.detailTransactionList = transactionData.getDetailTransactions();
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
		DetailHolder detailHolder = new DetailHolder(view);
		return detailHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull DetailHolder holder, final int position) {
		final DetailTransaction detailTransaction = detailTransactionList.get(position);

		holder.tvDescription.setText(detailTransaction.getDescription());
		holder.tvDate.setText(detailTransaction.getDate());

		if (NumberUtils.isParsable(detailTransaction.getAmount())) {
			holder.tvAmount.setText(detailTransaction.getAmount());

			if (detailTransaction.getAmount().startsWith(PREFIX)) {
				holder.tvAmount.setTextColor(Color.BLUE);
			} else {
				holder.tvAmount.setTextColor(Color.RED);
			}
		}else{
			holder.tvAmount.setText(ZERO);
			holder.tvAmount.setTextColor(Color.BLACK);
		}

		holder.itemView.setOnClickListener(new View.OnClickListener() {

			@Override public void onClick(View v) {
				if (itemClick != null) {
					itemClick.onClick(v, position, detailTransactionList);
				}
			}
		});

	}

	@Override
	public int getItemCount() {
		return (null != detailTransactionList ? detailTransactionList.size() : 0);
	}
}
