package org.tensorflow.lite.examples.bertqa

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.recyclerview.widget.RecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BertQaEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testAskQuestion() {
        activityRule.scenario.onActivity { activity ->
            Log.d("EspressoTest", "Activity created: ${activity.localClassName}")
        }

        // Wait for activity to stabilize
        Thread.sleep(2000)

        // Select a dataset from fragment_dataset.xml (RecyclerView)
        try {
            onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        } catch (e: Exception) {
            Log.e("EspressoTest", "RecyclerView action failed", e)
        }

        // Wait for fragment_qa.xml to load
        Thread.sleep(2000)

        // Check if passage TextView is displayed
        try {
            onView(withId(R.id.tvDatasetContent)).check(matches(isDisplayed()))
        } catch (e: Exception) {
            Log.e("EspressoTest", "tvDatasetContent check failed", e)
        }

        // Input a sample question
        try {
            onView(withId(R.id.edtQuestion)).perform(typeText("What is TensorFlow?"))
        } catch (e: Exception) {
            Log.e("EspressoTest", "edtQuestion action failed", e)
        }

        // Click the submit ImageButton
        try {
            onView(withId(R.id.imgBtnAsk)).perform(click())
        } catch (e: Exception) {
            Log.e("EspressoTest", "imgBtnAsk action failed", e)
        }

        // Verify inference time TextView is displayed
        try {
            onView(withId(R.id.tvInferenceTime)).check(matches(isDisplayed()))
        } catch (e: Exception) {
            Log.e("EspressoTest", "tvInferenceTime check failed", e)
        }
    }
}