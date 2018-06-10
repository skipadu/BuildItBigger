package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ActivityTestPaid {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private CountingIdlingResource mCountingIdlingResource;

    @Before
    public void registerCountingIdlingResource() {
        mCountingIdlingResource = mActivityTestRule.getActivity().getCountingIdlingResource();
        IdlingRegistry.getInstance().register(mCountingIdlingResource);
    }

    @After
    public void unregisterCountingIdlingResource() {
        if (mCountingIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mCountingIdlingResource);
        }
    }

    /**
     * Tests that AsyncTask is loading the joke and it is shown
     */
    @Test
    public void showJoke() {
        onView(withId(R.id.tell_joke_button)).perform(click());
        onView(withId(R.id.tv_joke)).check(matches(isDisplayed()));
    }
}
