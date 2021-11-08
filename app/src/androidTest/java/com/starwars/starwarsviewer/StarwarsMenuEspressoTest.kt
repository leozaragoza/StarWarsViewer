package com.starwars.starwarsviewer

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.starwars.starwarsviewer.ui.activity.StarWarsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class StarwarsMenuEspressoTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<StarWarsActivity>
            = ActivityScenarioRule(StarWarsActivity::class.java)

    @Before
    fun setup() {
    }

    @Test
    fun menu_buttons_are_enabled() {
        onView(withId(R.id.planets_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.people_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.planets_btn)).check(matches(isEnabled()))
        onView(withId(R.id.people_btn)).check(matches(isEnabled()))
    }

    @Test
    fun menu_planet_btn_navigate_to_planets() {
        onView(withId(R.id.planets_btn)).perform(click())
        onView(withId(R.id.toolbar_title)).check(matches(isDisplayed()))
    }

    @Test
    fun menu_people_btn_does_not_navigate() {
        onView(withId(R.id.people_btn)).perform(click())
        onView(withId(R.id.planets_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.people_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.planets_btn)).check(matches(isEnabled()))
        onView(withId(R.id.people_btn)).check(matches(isEnabled()))
    }
}