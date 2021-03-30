package com.pruebacondorlabs.models

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.pruebacondorlabs.R
import com.pruebacondorlabs.adapter.TeamsRecyclerAdapter

class TeamTest {

    val recyclerView = R.id.recyclerViewTeams

    fun clickRecyclerViewTeams(position : Int) : ViewAction {
        return RecyclerViewActions.actionOnItemAtPosition<TeamsRecyclerAdapter.CustomViewHolder>(position, click())
    }


}