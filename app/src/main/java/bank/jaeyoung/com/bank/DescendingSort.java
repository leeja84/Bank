package bank.jaeyoung.com.bank;


import bank.jaeyoung.com.bank.data.DetailTransaction;

import java.util.Comparator;

/**
 * Created by jaeyoung lee on 2018. 10. 17..
 */
public class DescendingSort implements Comparator<DetailTransaction> {
	@Override public int compare(DetailTransaction o1, DetailTransaction o2) {
		return o2.getDate().compareTo(o1.getDate());
	}
}
