package com.pruebacondorlabs

import com.example.domain.model.response.Events
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import io.reactivex.Observable
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

class DetailViewModelTest : BaseTest() {

    /*lateinit var detailViewModel: DetailViewModel

    @Mock
    lateinit var leaguesRepository: com.example.domain.repository.LeaguesRepository


    private lateinit var event : ArrayList<Match>


    @InjectMocks
    lateinit var events: Events

    @InjectMocks
    lateinit var team: Teams

    val id = "id"
    val name = "name"
    val description = "description"
    val badge = "badge"
    val year = "year"


    @Before
    override fun setUp() {
        super.setUp()
        event = ArrayList()
        leaguesRepository = mock(com.example.domain.repository.LeaguesRepository::class.java)
        detailViewModel = spy(DetailViewModel(leaguesRepository))
    }

    @Test
    fun testLoadUsersIsSuccessfulL(){
        events.events = event
        team.idTeam = id
        `when`(leaguesRepository.getEvents(id)).thenReturn(Observable.just(events))
        detailViewModel.getLastEvents(id)
        assertTrue(detailViewModel.progress().value!!)
    }


    @Test
    fun testLoadEventsIsSuccessfulService(){
        events.events = event
        detailViewModel.loadDataEvents(events)
        assertEquals(detailViewModel.getEvents().value,event)
    }

    @Test
    fun testLoadDataIsNotNull() {
        events.events = event
        detailViewModel.loadDataEvents(null)
        assertNull(detailViewModel.getEvents().value)
    }

    @Test
    fun testLoadDataTeamIsNull() {
        detailViewModel.loadData(null)
        verify(detailViewModel, times(0)).getLastEvents(id)
        verify(detailViewModel, times(0)).validateData(team)
        verify(detailViewModel, times(0)).visibilityData()
    }

    @Test
    fun testLoadDataTeamIsNotNull() {
        loadInformationTeam()
        `when`(leaguesRepository.getEvents(id)).thenReturn(Observable.just(events))
        detailViewModel.loadData(team)
        verify(detailViewModel).getLastEvents(id)
        verify(detailViewModel).validateData(team)
        verify(detailViewModel).visibilityData()
    }

    private fun loadInformationTeam(){
        team.idTeam = id
        team.name = name
        team.description = description
        team.badge = badge
        team.year = year
    }
*/


}