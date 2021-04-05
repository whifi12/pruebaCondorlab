package com.example.domain

import com.example.domain.db.AppDatabase
import com.example.domain.firebase.FeatureFlagging
import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.repository.LeaguesRepository
import com.example.domain.service.LeagueServices
import com.example.utilities.util.Constants.SPAIN
import com.example.utilities.util.Constants.SPORT
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LeaguesRepositoryTest : BaseTest() {


    @MockK
    lateinit var leagueServices: LeagueServices

    @MockK
    lateinit var localDataSource : AppDatabase

    @MockK
    lateinit var featureFlagging: FeatureFlagging

    private lateinit var leaguesRepository: LeaguesRepository

    @InjectMockKs
    lateinit var league: League


    @Before
    fun setUp(){
        MockKAnnotations.init(this, relaxUnitFun = true)
        leaguesRepository = spyk(LeaguesRepository(leagueServices,featureFlagging,localDataSource))
    }

    @Test
    fun testGetTeamsSuccessfulService() = testCoroutineRule.runBlockingTest {
        val response: Response<League> = Response.success(league)
        val teamRequest = TeamRequest(SPORT, SPAIN)
        coEvery { leagueServices.getTeams(SPORT,SPAIN) } returns response
        val result = leaguesRepository.getTeams(teamRequest)
        assertEquals(response, result)
    }


}