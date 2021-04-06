package com.pruebacondorlabs


import com.example.domain.model.response.Teams
import com.pruebacondorlabs.viewModel.ItemTeamViewModel
import io.mockk.MockKAnnotations
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ItemTeamViewModelTest : BaseTest() {

    lateinit var itemTeamViewModel: ItemTeamViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        itemTeamViewModel = spyk(ItemTeamViewModel())
    }

    @Test
    fun validateLoadData(){
        val teams = Teams()
        teams.name = "name"
        teams.stadium = "stadium"
        teams.badge = "badge"
        itemTeamViewModel.teams(teams)
        assertEquals(itemTeamViewModel.name.value,teams.name)
        assertEquals(itemTeamViewModel.stadium.value,teams.stadium)
        assertEquals(itemTeamViewModel.imageUrl.value,teams.badge)
    }
}