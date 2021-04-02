package com.pruebacondorlabs



import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.League
import com.example.domain.model.response.Teams
import com.example.domain.usecase.*
import com.example.utilities.util.Constants.SPAIN
import com.example.utilities.util.Constants.SPORT
import com.example.utilities.util.ValidateInternet
import com.pruebacondorlabs.viewModel.MainViewModel
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.*
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
class MainViewModelTest : BaseTest() {


    lateinit var mainViewModel: MainViewModel


    @MockK
    lateinit var getTeamsUseCase: GetTeamsUseCase

    @MockK
    lateinit var getConfigUseCase: GetConfigUseCase

    @MockK
    lateinit var getTeamsDBUseCase: GetTeamsDBUseCase

    @MockK
    lateinit var saveTeamsUseCase: SaveTeamsUseCase

    @MockK
    lateinit var validateInternet: ValidateInternet

    private lateinit var teams: ArrayList<Teams>

    private lateinit var teamRequest: TeamRequest



    @InjectMockKs
    lateinit var league: League

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        teams = ArrayList()
        mainViewModel = spyk(MainViewModel(getTeamsUseCase,getConfigUseCase,
            getTeamsDBUseCase,saveTeamsUseCase,validateInternet))
    }

    @Test
    fun testLoadTeamsDBIsEmpty(){
        coEvery { validateInternet.isConnected() } returns false
        coEvery { getTeamsDBUseCase.execute(any()) } returns League()
        mainViewModel.loadTeams(SPAIN)
        verify { mainViewModel.validateInternetToGetTeams(SPAIN) }
    }

    @Test
    fun testLoadTeamsDBIsNotEmpty(){
        val league = League()
        val team = Teams()
        teams.add(team)
        league.teams = teams
        coEvery { saveTeamsUseCase.execute(any()) } returns Any()
        coEvery { getTeamsDBUseCase.execute(any()) } returns league
        mainViewModel.loadTeams(SPAIN)
        verify { mainViewModel.loadData(league) }
    }

    @Test
    fun testValidateInternetToGetTeamsIsConnected(){
        coEvery { validateInternet.isConnected() } returns true
        mainViewModel.validateInternetToGetTeams(SPAIN)
        verify { mainViewModel.getTeams(SPAIN) }
    }

    @Test
    fun testValidateInternetToGetTeamsIsNotConnected(){
        coEvery { validateInternet.isConnected() } returns false
        mainViewModel.validateInternetToGetTeams(SPAIN)
        verify(exactly = 0) { mainViewModel.getTeams(SPAIN) }
    }

    @Test
    fun testGetTeamsIsSuccessfulService(){
        teamRequest = TeamRequest(SPORT, SPAIN)
        league.teams = teams
        val response: Response<League> = Response.success(league)
        coEvery { getTeamsUseCase.execute(any()) } returns response
        coEvery { saveTeamsUseCase.execute(any()) } returns Any()
        mainViewModel.getTeams(SPAIN)
        assertFalse(mainViewModel.progress.value!!)
        verify { mainViewModel.responseTeams(any()) }
    }

    @Test
    fun testLoadConfig(){
        val config = true
        every { getConfigUseCase.execute(any())} returns config
        mainViewModel.loadConfig()
        assertEquals(mainViewModel.config.value,config)
    }

    @Test
    fun testResponseTeamsIsSuccessful() {
        league.teams = teams
        val response: Response<League> = Response.success(league)
        coEvery { saveTeamsUseCase.execute(any()) } returns Any()
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
        assertNotNull(mainViewModel.error.value)
        verify(exactly = 0) { mainViewModel.loadData(league) }
    }

    @Test
    fun testLoadServiceIsSuccessful() {
        league.teams = teams
        coEvery { saveTeamsUseCase.execute(any()) } returns Any()
        mainViewModel.loadData(league)
        assertEquals(mainViewModel.teams.value, teams)
    }

    @Test
    fun testLoadServiceIsNotSuccessful() {
        mainViewModel.loadData(null)
        assertNull(mainViewModel.teams.value)
    }


}