package com.kadamab.winews

import android.widget.ImageView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.runner.RunWith
import org.junit.Rule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.kadamab.winews.adapter.NewsListAdapter
import com.kadamab.winews.model.Rows
import com.kadamab.winews.utility.FakeNewsData
import org.junit.Assert
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {

    val TEST_ROW = 0;
    val TEST_NEWS_TITLE = FakeNewsData.news[TEST_ROW].title
    val TEST_NEWS_DESCRIPTION = FakeNewsData.news[TEST_ROW].description
    val TEST_NEWS_IMAGE = FakeNewsData.news[TEST_ROW].imageHref



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
                RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.DataViewHolder>(
                    TEST_ROW,
                    click()
                )
            )
            onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        }
    }

    /*
       * Test if data fetched from api and recyclerview is visible
       *
       */
    @Test
    fun testNewsDataTitleSet() {
        if (getRVcount() > 0) {
            onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.DataViewHolder>(
                    TEST_ROW,
                    click()
                )
            )
            onView(withId(R.id.tvTitle)).check(matches(withText(TEST_NEWS_TITLE)))
        }
    }

    @Test
    fun testNewsDataDescriptionSet() {
        if (getRVcount() > 0) {
            onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.DataViewHolder>(
                    TEST_ROW,
                    click()
                )
            )
            onView(withId(R.id.tvDescr)).check(matches(withText(TEST_NEWS_DESCRIPTION)))
        }
    }

    @Test
    fun testNewsDataImageSet() {
        if (getRVcount() > 0) {
            onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.DataViewHolder>(
                    TEST_ROW,
                    click()
                )
            )
            onView(withId(R.id.imageViewNews)).check(matches(withText(TEST_NEWS_IMAGE)))
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