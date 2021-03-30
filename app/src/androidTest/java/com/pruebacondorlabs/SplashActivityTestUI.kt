package com.pruebacondorlabs


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.pruebacondorlabs.utils.BaseAndroidTest
import com.pruebacondorlabs.view.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashActivityTestUI : BaseAndroidTest() {

    @get:Rule
    var activityRule: ActivityTestRule<SplashActivity>
            = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun nameAppTest() {
        checkText(R.id.text,"Futbol Total!")
    }


}