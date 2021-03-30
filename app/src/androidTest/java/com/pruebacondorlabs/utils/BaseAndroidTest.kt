package com.pruebacondorlabs.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText


open class BaseAndroidTest {

    open fun checkText(id: Int, text: String) {
        onView(withId(id))
            .check(ViewAssertions.matches(withText(text)))
    }


    open fun clickItemRecycler(id: Int, viewActions: ViewAction) {
        onView(withId(id))
            .perform(viewActions)
    }

    open fun sizeDataRecycler(id: Int,size: Int){
        onView(withId(id)).check(
            RecyclerViewItemCountAssertion(
                size
            )
        )
    }

}

