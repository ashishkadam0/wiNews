package com.kadamab.winews

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.runner.RunWith
import org.junit.Rule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kadamab.winews.adapter.MainAdapter
import org.junit.Assert
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {

    @Rule
    var activityActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}

    /*
    * Test if data fetched from api and recyclerview is visible
    *
    */
    @Test
    fun testNewsDataSuccess() {
        if (getRVcount() > 0) {
            onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<MainAdapter.DataViewHolder>(
                    0,
                    click()
                )
            )
        }
    }

    /*
   * Test if data fetched from api fails and recyclerview is not visible, here count less than 0 means api error occurs
   *
   */
    @Test
    fun testNewsDataFailure() {
        Assert.assertTrue(getRVcount() < 1)
    }

    private fun getRVcount(): Int {
        val recyclerView =
            activityActivityTestRule.getActivity().findViewById(R.id.recyclerView) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}