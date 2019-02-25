package bank.jaeyoung.com.bank;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import bank.jaeyoung.com.bank.view.MainActivity;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by jaeyoung lee on 2018. 12. 6..
 */
@RunWith(AndroidJUnit4.class)
public class SimepleUITest {
	public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule(MainActivity.class, true, true);

	@Test
	public void checkAccount() {
		Espresso.onView(withId(R.id.tv_account)).check(matches(withText("NL30MOYO0001234567")));
	}
}
