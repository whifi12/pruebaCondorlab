package com.pruebacondorlabs


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.pruebacondorlabs.view.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashActivityTestUI {

    @get:Rule
    var activityRule: ActivityTestRule<SplashActivity>
            = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun nameAppTest() {

        onView(withId(R.id.text))
            .check(matches(withText("Futbol Total!")))

    }


}