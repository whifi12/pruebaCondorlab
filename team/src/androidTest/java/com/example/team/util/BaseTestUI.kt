package com.example.team.util


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

open class BaseTestUI {

    open fun checkText(id: Int, text: String) {
        onView(withId(id))
            .check(matches(ViewMatchers.withText(text)))
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