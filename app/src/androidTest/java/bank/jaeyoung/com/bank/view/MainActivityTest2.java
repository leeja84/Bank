package bank.jaeyoung.com.bank.view;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import bank.jaeyoung.com.bank.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

	@Rule
	public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

	@Test
	public void mainActivityTest2() {
		ViewInteraction textView = onView(
			allOf(withId(R.id.tv_balance), withText("Balance :100.20"),
				childAtPosition(
					childAtPosition(
						IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
						0),
					1),
				isDisplayed()));
		textView.check(matches(withText("Balance :100.20")));

		ViewInteraction recyclerView = onView(
			allOf(withId(R.id.rv_detail_transaction),
				childAtPosition(
					withClassName(is("android.widget.LinearLayout")),
					2)));
		recyclerView.perform(actionOnItemAtPosition(1, click()));

		ViewInteraction recyclerView2 = onView(
			allOf(withId(R.id.rv_detail_transaction),
				childAtPosition(
					withClassName(is("android.widget.LinearLayout")),
					2)));
		recyclerView2.perform(actionOnItemAtPosition(1, click()));

		ViewInteraction appCompatButton = onView(
			allOf(withId(R.id.btn_ok), withText("OK"),
				childAtPosition(
					childAtPosition(
						withClassName(is("android.widget.ScrollView")),
						0),
					9)));
		appCompatButton.perform(scrollTo(), click());

		pressBack();

		ViewInteraction textView2 = onView(
			allOf(withId(R.id.tv_date), withText("2018-05-13T18:19:00Z"),
				childAtPosition(
					childAtPosition(
						withId(R.id.rv_detail_transaction),
						3),
					0),
				isDisplayed()));
		textView2.check(matches(isDisplayed()));
	}

	private static Matcher<View> childAtPosition(
		final Matcher<View> parentMatcher, final int position) {

		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("Child at position " + position + " in parent ");
				parentMatcher.describeTo(description);
			}

			@Override
			public boolean matchesSafely(View view) {
				ViewParent parent = view.getParent();
				return parent instanceof ViewGroup && parentMatcher.matches(parent)
					&& view.equals(((ViewGroup)parent).getChildAt(position));
			}
		};
	}
}
