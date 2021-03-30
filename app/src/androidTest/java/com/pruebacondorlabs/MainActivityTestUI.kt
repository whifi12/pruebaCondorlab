package com.pruebacondorlabs


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.utilities.util.Constants.ITEM_BELOW_THE_FOLD
import com.pruebacondorlabs.models.TeamTest
import com.pruebacondorlabs.utils.BaseAndroidTest
import com.pruebacondorlabs.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTestUI : BaseAndroidTest(){

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    val team = TeamTest()


    @Test
    fun checkRedirectDetailActivityTest() {
        // First, scroll to the position that needs to be matched and click on it.
        Thread.sleep(4000)
        clickItemRecycler(id = team.recyclerView,viewActions = team.clickRecyclerViewTeams(ITEM_BELOW_THE_FOLD))
        // Match the text in an item below the fold and check that it's displayed
        Thread.sleep(3000)
        checkText(R.id.year,"1945")
    }

    @Test
    fun checkSizeTeams(){
        Thread.sleep(4000)
        sizeDataRecycler(team.recyclerView,50)
    }







}