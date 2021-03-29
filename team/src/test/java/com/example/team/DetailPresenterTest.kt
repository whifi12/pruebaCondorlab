package com.example.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.model.response.Events
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams
import com.example.domain.usecase.GetEventsUseCase
import com.example.team.presenter.DetailPresenter
import com.example.team.view.IDetailActivity
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
        detailPresenter = spyk(DetailPresenter(getEventsUseCase))
        detailPresenter.injectView(iDetailActivity)
    }

    @Test
    fun testLoadDataTeamsIsNotNull() = testCoroutineRule.runBlockingTest{
        val id = "id"
        teams.idTeam = id
        val response: Response<Events> = Response.success(events)
        coEvery { getEventsUseCase.execute(any()) } returns response
        detailPresenter.loadData(teams)
        verify { iDetailActivity.loadData(teams) }
        coVerify { detailPresenter.getLastEvents(id) }
    }

    @Test
    fun testLoadDataTeamsIsNull() = testCoroutineRule.runBlockingTest{
        val response: Response<Events> = Response.success(events)
        coEvery { getEventsUseCase.execute(any()) } returns response
        detailPresenter.loadData(null)
        verify(exactly = 0) { iDetailActivity.loadData(teams) }
        coVerify(exactly = 0) { detailPresenter.getLastEvents(any()) }
    }

    @Test
    fun testGetLoadLastEvents() = testCoroutineRule.runBlockingTest{
        val id = "id"
        events.events = event
        val response: Response<Events> = Response.success(events)
        coEvery { getEventsUseCase.execute(id) } returns response
        detailPresenter.getLastEvents(id)
        verify { iDetailActivity.showProgressDIalog(R.string.wait) }
        coVerify { detailPresenter.response(response)}
    }



    @Test
    fun testResponseIsSuccessful(){
        events.events = event
        val response: Response<Events> = Response.success(events)
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
        detailPresenter.loadDataEvents(events)
        verify {iDetailActivity.loadEvents(event)}
    }

    @Test
    fun testLoadDataEventsIsNull(){
        detailPresenter.loadDataEvents(null)
        verify(exactly = 0) {iDetailActivity.loadEvents(event)}
    }





}