package com.aranirahan.myfootballapi.view.myActivity


import android.support.design.internal.BottomNavigationItemView
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.aranirahan.myfootballapi.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by root on 3/1/18.
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        onView(withId(pastMatch)).check(matches(isDisplayed()))
        onView(withId(nextMatch)).check(matches(isDisplayed()))
        onView(withId(favorites)).check(matches(isDisplayed()))
        Thread.sleep(4000)
        onView(withId(rv_list_team))
                .check(matches(isDisplayed()))
        onView(withId(rv_list_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12))
        onView(withId(rv_list_team)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }
}