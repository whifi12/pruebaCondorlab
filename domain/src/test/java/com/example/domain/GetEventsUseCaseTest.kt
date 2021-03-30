package com.example.domain

import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.repository.LeaguesRepository
import com.example.domain.usecase.GetEventsUseCase
import com.example.domain.usecase.GetTeamsUseCase
import com.example.utilities.util.Constants
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import junit.framework.Assert
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
class GetEventsUseCaseTest : BaseTest() {

    @MockK
    lateinit var leaguesRepository: LeaguesRepository

    private lateinit var getEventsUseCase: GetEventsUseCase

    @InjectMockKs
    private lateinit var events: Events

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getEventsUseCase = spyk(GetEventsUseCase(leaguesRepository, Dispatchers.Main))
    }

    @Test
    fun testLoadTeamsIsSuccessfulService() = testCoroutineRule.runBlockingTest{
        val id = "id"
        val response: Response<Events> = Response.success(events)
        coEvery { leaguesRepository.getEvents(any()) } returns response
        val result = getEventsUseCase.execute(id)
        Assert.assertEquals(response, result)
    }

    @Test
    fun testLoadTeamsIsNotSuccessfulService() = testCoroutineRule.runBlockingTest{
        val id = "id"
        val errorResponse =
            "{\n" +
                    "  \"type\": \"error\",\n" +
                    "  \"message\": \"What you were looking for isn't here.\"\n}"
        val response : Response<Events> = Response.error(
            400, errorResponse.toResponseBody("application/json".toMediaTypeOrNull()))
        coEvery { leaguesRepository.getEvents(id)} returns response
        val result = getEventsUseCase.execute(id)
        Assert.assertEquals(response, result)
    }




}