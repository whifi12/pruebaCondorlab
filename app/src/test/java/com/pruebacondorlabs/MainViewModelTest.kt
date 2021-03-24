package com.pruebacondorlabs

import android.provider.ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY
import com.pruebacondorlabs.models.League
import com.pruebacondorlabs.models.Teams
import com.pruebacondorlabs.repositories.LeaguesRepository
import com.example.utilities.util.Constants.SPORT
import com.pruebacondorlabs.viewModel.MainViewModel
import io.reactivex.Observable
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

class MainViewModelTest : BaseTest() {

    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var leaguesRepository: LeaguesRepository


    private lateinit var teams : ArrayList<Teams>

    @InjectMocks
    lateinit var league: League

    @Before
    override fun setUp() {
        super.setUp()
        teams = ArrayList()
        mainViewModel = spy(MainViewModel(leaguesRepository))
    }

    @Test
    fun testLoadTeamsIsSuccessfulService(){
        league.teams = teams
        `when`(leaguesRepository.getTeams(SPORT,COUNTRY)).thenReturn(Observable.just(league))
        mainViewModel.getTeams(COUNTRY)
        assertTrue(mainViewModel.progress().value!!);
        verify(mainViewModel).loadData(league)
    }

    @Test
    fun testLoadServiceIsSuccessful() {
        league.teams = teams
        mainViewModel.loadData(league)
        assertEquals(mainViewModel.teams().value,teams)
    }

    @Test
    fun testLoadServiceIsNotSuccessful() {
        mainViewModel.loadData(null)
        assertNull(mainViewModel.teams().value)
    }


}