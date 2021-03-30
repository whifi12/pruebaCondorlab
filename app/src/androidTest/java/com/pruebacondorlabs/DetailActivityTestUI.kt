package com.pruebacondorlabs

import android.content.Intent
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.team.view.DetailActivity
import com.example.utilities.util.Constants.TEAMS
import com.pruebacondorlabs.models.DetailTest
import com.pruebacondorlabs.utils.BaseAndroidTest
import org.junit.Rule
import org.junit.Test

class DetailActivityTestUI : BaseAndroidTest() {

    val detailTest = DetailTest()

    @get:Rule
    val mActivityTestRule: ActivityTestRule<DetailActivity> =
        object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, DetailActivity::class.java).apply {
                    putExtra(TEAMS, detailTest.loadDataTeams())
                }
            }
        }

    @Test
    fun checkLoadData(){
        Thread.sleep(1000)
        val teams = detailTest.loadDataTeams()
        checkText(R.id.name,text = teams.name)
        checkText(R.id.description,text = teams.description)
        checkText(R.id.year,text = teams.year)
        checkText(R.id.web,text = teams.website)
        checkText(R.id.facebook,text = teams.facebook)
        checkText(R.id.twitter,text = teams.twitter)
        checkText(R.id.instagram,text = teams.instagram)
    }

    @Test
    fun checkSizeLastEvents() {
        Thread.sleep(3000)
        sizeDataRecycler(detailTest.recyclerView, 5)
    }

}