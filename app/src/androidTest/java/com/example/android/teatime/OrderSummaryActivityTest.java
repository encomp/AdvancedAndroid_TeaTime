package com.example.android.teatime;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {

  private static final String emailMessage =
      "I just ordered a delicious tea from TeaTime. Next time you are craving a tea, check them "
          + "out!";

  @Rule
  public IntentsTestRule<OrderSummaryActivity> activityRule =
      new IntentsTestRule<>(OrderSummaryActivity.class);;

  // intents so all external intents will be blocked
  @Before
  public void stubAllExternalIntents() {
    intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
  }

  // button matches the intent sent by the application
  @Test
  public void clickSendEmailButton_SendsEmail() {
    onView(withId(R.id.send_email_button)).perform(click());

    intended(
        allOf(
            hasAction(Intent.ACTION_SENDTO),
            hasData(Uri.parse("mailto:")),
            hasExtra(Intent.EXTRA_TEXT, emailMessage)));
  }
}
