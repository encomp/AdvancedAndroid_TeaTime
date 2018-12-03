package com.example.android.teatime;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

/**
 * This test demos a user clicking on a GridView item in MenuActivity which opens up the
 * corresponding OrderActivity.
 *
 * <p>This test does not utilize Idling Resources yet. If idling is set in the MenuActivity, then
 * this test will fail. See the IdlingResourcesTest for an identical test that takes into account
 * Idling Resources.
 */
@RunWith(AndroidJUnit4.class)
public class MenuActivityScreenTest {

  @Rule
  public ActivityTestRule<MenuActivity> mActivityTestRule =
      new ActivityTestRule<>(MenuActivity.class);

  @Test
  public void clickGridViewItem_OpensOrderActivity() {
    onData(anything()).inAdapterView(withId(R.id.tea_grid_view)).atPosition(1).perform(click());

    onView(withId(R.id.tea_name_text_view)).check(matches(withText("Green Tea")));
  }
}
