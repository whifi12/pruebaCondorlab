package com.pruebacondorlabs



import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.League
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetTeamsUseCase
import com.example.utilities.util.Constants.SPAIN
import com.example.utilities.util.Constants.SPORT
import com.pruebacondorlabs.viewModel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import okhttp3.MediaType.Companion.toMediaType
import io.mockk.spyk
import io.mockk.verify
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest : BaseTest() {


    lateinit var mainViewModel: MainViewModel


    @MockK
    lateinit var getTeamsUseCase: GetTeamsUseCase


    private lateinit var teams: ArrayList<Teams>

    private lateinit var teamRequest: TeamRequest



    @InjectMockKs
    lateinit var league: League

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        teams = ArrayList()
        mainViewModel = spyk(MainViewModel(getTeamsUseCase))
    }

    @Test
    fun testLoadTeamsIsSuccessfulService(){
        teamRequest = TeamRequest(SPORT, SPAIN)
        league.teams = teams
        val response: Response<League> = Response.success(league)
        coEvery { getTeamsUseCase.execute(any()) } returns response
        mainViewModel.getTeams(SPAIN)
        assertFalse(mainViewModel.progress().value!!)
        verify { mainViewModel.responseTeams(any()) }
    }

    @Test
    fun testResponseTeamsIsSuccessful() {
        league.teams = teams
        val response: Response<League> = Response.success(league)
        mainViewModel.responseTeams(response)
        verify { mainViewModel.loadData(league) }
    }

    @Test
    fun testResponseTeamsIsNotSuccessful() {
        league.teams = teams
        val errorResponse =
            "{\n" +
                    "  \"type\": \"error\",\n" +
                    "  \"message\": \"What you were looking for isn't here.\"\n}"
        val response : Response<League> = Response.error(
            400, errorResponse.toResponseBody("application/json".toMediaTypeOrNull()))
        mainViewModel.responseTeams(response)
        assertNotNull(mainViewModel.error().value)
        verify(exactly = 0) { mainViewModel.loadData(league) }
    }

    @Test
    fun testLoadServiceIsSuccessful() {
        league.teams = teams
        mainViewModel.loadData(league)
        assertEquals(mainViewModel.teams().value, teams)
    }

    @Test
    fun testLoadServiceIsNotSuccessful() {
        mainViewModel.loadData(null)
        assertNull(mainViewModel.teams().value)
    }


}