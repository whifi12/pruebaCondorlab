package com.example.domain

import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.League
import com.example.domain.model.response.Teams
import com.example.domain.repository.LeaguesRepository
import com.example.domain.usecase.GetTeamsUseCase
import com.example.utilities.util.Constants
import junit.framework.Assert.*
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetTeamsUseCaseTest :BaseTest() {

    lateinit var getTeamsUseCase: GetTeamsUseCase

    private lateinit var teamRequest: TeamRequest

    @MockK
    lateinit var leaguesRepository: LeaguesRepository


    private lateinit var teams: ArrayList<Teams>

    @InjectMockKs
    lateinit var league: League


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        teams = ArrayList()
        getTeamsUseCase = spyk(GetTeamsUseCase(leaguesRepository,Dispatchers.Main))
    }

    @Test
     fun testLoadTeamsIsSuccessfulService() = testCoroutineRule.runBlockingTest{
        teamRequest = TeamRequest(Constants.SPORT, Constants.SPAIN)
        league.teams = teams
        val response: Response<League> = Response.success(league)
        coEvery { leaguesRepository.getTeams(any()) } returns response
        val result = getTeamsUseCase.execute(teamRequest)
        assertEquals(response,result)
    }

    @Test
    fun testLoadTeamsIsNotSuccessfulService() = testCoroutineRule.runBlockingTest{
        teamRequest = TeamRequest(Constants.SPORT, Constants.SPAIN)
        val errorResponse =
            "{\n" +
                    "  \"type\": \"error\",\n" +
                    "  \"message\": \"What you were looking for isn't here.\"\n}"
        val response : Response<League> = Response.error(
            400, errorResponse.toResponseBody("application/json".toMediaTypeOrNull()))
        coEvery { leaguesRepository.getTeams(any()) } returns response
        val result = getTeamsUseCase.execute(teamRequest)
        assertEquals(response,result)
    }

}