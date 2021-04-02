package com.example.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.db.EventsDB
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetEventsDBUseCase
import com.example.domain.usecase.GetEventsUseCase
import com.example.domain.usecase.SaveEventsUseCase
import com.example.team.presenter.DetailPresenter
import com.example.team.view.IDetailActivity
import com.example.utilities.util.ValidateInternet
import com.github.testcoroutinesrule.TestCoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DetailPresenterTest {

    @get:Rule
    var instantRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @MockK
    lateinit var getEventsUseCase: GetEventsUseCase

    @MockK
    lateinit var getEventsDB: GetEventsDBUseCase

    @MockK
    lateinit var saveEventsUseCase: SaveEventsUseCase

    @MockK
    lateinit var validateInternet: ValidateInternet

    @InjectMockKs
    lateinit var events: Events

    @MockK
    lateinit var iDetailActivity: IDetailActivity

    @InjectMockKs
    lateinit var teams: Teams

    @MockK
    lateinit var detailPresenter: DetailPresenter

    private lateinit var event : ArrayList<Match>

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        event = ArrayList()
        detailPresenter = spyk(DetailPresenter(getEventsUseCase,
            getEventsDB,saveEventsUseCase,validateInternet))
        detailPresenter.injectView(iDetailActivity)
    }

    @Test
    fun testLoadDataTeamsIsNotNull() = testCoroutineRule.runBlockingTest{
        coEvery { getEventsDB.execute(any()) } returns Events()
        coEvery { validateInternet.isConnected() } returns false
        detailPresenter.loadData(teams)
        verify { iDetailActivity.loadData(teams) }
        coVerify { detailPresenter.validateDataSource(any())}
    }

    @Test
    fun testLoadDataTeamsIsNull() = testCoroutineRule.runBlockingTest{
        val response: Response<Events> = Response.success(events)
        coEvery { getEventsUseCase.execute(any()) } returns response
        detailPresenter.loadData(null)
        verify(exactly = 0) { iDetailActivity.loadData(teams) }
        coVerify(exactly = 0) { detailPresenter.validateDataSource(any()) }
    }

    @Test
    fun testGetLoadLastEventsInternetIsConnected() = testCoroutineRule.runBlockingTest{
        events.events = event
        val response: Response<Events> = Response.success(events)
        coEvery { validateInternet.isConnected() } returns true
        coEvery { getEventsUseCase.execute(any()) } returns response
        coEvery { saveEventsUseCase.execute(any()) } returns Any()
        detailPresenter.getLastEvents()
        verify { iDetailActivity.showProgressDIalog(R.string.wait) }
        coVerify { detailPresenter.response(response)}
    }

    @Test
    fun testGetLoadLastEventsInternetIsNotConnected() = testCoroutineRule.runBlockingTest{
        events.events = event
        val response: Response<Events> = Response.success(events)
        coEvery { validateInternet.isConnected() } returns false
        detailPresenter.getLastEvents()
        verify(exactly = 0) { iDetailActivity.showProgressDIalog(R.string.wait) }
        coVerify(exactly = 0) { detailPresenter.response(response)}
    }



    @Test
    fun testResponseIsSuccessful(){
        events.events = event
        val response: Response<Events> = Response.success(events)
        coEvery { saveEventsUseCase.execute(any()) } returns Any()
        detailPresenter.response(response)
        verify {iDetailActivity.dismissProgressDialog()}
        verify {detailPresenter.loadDataEvents(events)}
    }

    @Test
    fun testResponseIsNotSuccessful(){
        events.events = event
        val errorResponse =
            "{\n" +
                    "  \"type\": \"error\",\n" +
                    "  \"message\": \"What you were looking for isn't here.\"\n}"
        val response : Response<Events> = Response.error(
            400, errorResponse.toResponseBody("application/json".toMediaTypeOrNull()))
        detailPresenter.response(response)
        verify {iDetailActivity.dismissProgressDialog()}
        verify {iDetailActivity.loadError(any())}
    }

    @Test
    fun testLoadDataEventsIsNotNull(){
        events.events = event
        coEvery { saveEventsUseCase.execute(any()) } returns Any()
        detailPresenter.loadDataEvents(events)
        verify {iDetailActivity.loadEvents(event)}
    }

    @Test
    fun testLoadDataEventsIsNull(){
        detailPresenter.loadDataEvents(null)
        verify(exactly = 0) {iDetailActivity.loadEvents(event)}
    }





}