package com.project.demorecord;


import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EnterNulltest {

    @Before
    public void start() {
        Context context = InstrumentationRegistry.getTargetContext();

        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        for (String fileName : sharedPreferencesFileNames) {
            InstrumentationRegistry.getTargetContext().getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
        }

        mActivityTestRule.launchActivity(new Intent());

    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void nameagenulltest() {

        onView(allOf(withId(R.id.editTExtName),isDisplayed())).check(matches(withText("")));

        onView(allOf(withId(R.id.editTextAge),isDisplayed())).check(matches(withText("")));

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(android.R.id.message), withText("Please Enter user info"),isDisplayed()));

        SystemClock.sleep(1000);
    }

    @Test
    public void age20namenulltest() {

        onView(allOf(withId(R.id.editTExtName),isDisplayed())).check(matches(withText("")));

        onView(allOf(withId(R.id.editTextAge),isDisplayed())).perform(replaceText("20"), ViewActions.closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(android.R.id.message), withText("Please Enter user info"),isDisplayed()));

        SystemClock.sleep(1000);
    }

    @Test
    public void nameyingagenulltest() {

        onView(allOf(withId(R.id.editTextAge),isDisplayed())).check(matches(withText("")));

        onView(allOf(withId(R.id.editTExtName),isDisplayed())).perform(replaceText("Ying"), ViewActions.closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(android.R.id.message), withText("Please Enter user info"),isDisplayed()));

        SystemClock.sleep(1000);

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
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
