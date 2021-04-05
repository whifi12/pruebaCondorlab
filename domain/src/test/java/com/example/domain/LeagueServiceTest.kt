package com.example.domain

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactProviderRuleMk2
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.RequestResponsePact
import com.example.domain.Services.GETEVENTS
import com.example.domain.Services.GETTEAMS
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.service.LeagueServices
import com.example.utilities.util.Constants
import com.example.utilities.util.Constants.SPAIN
import com.example.utilities.util.Constants.SPORT
import com.google.gson.Gson
import groovy.util.GroovyTestCase.assertEquals
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.hasItems
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LeagueServiceTest :BaseTest() {


    lateinit var retrofit : Retrofit

    lateinit var client : LeagueServices



    @Rule
    @JvmField
    val provider = PactProviderRuleMk2("our_provider", "localhost", 9292, this)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        val okHttpClient =  OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClient.addInterceptor(interceptor).build()


         retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
        client = retrofit.create(LeagueServices::class.java)
    }

    @Pact(consumer = "consumer-android")
    fun createPact(builder: PactDslWithProvider): RequestResponsePact {

        return builder
            .given("default")
            .uponReceiving("Fetching application bootstrap information")
            .path("search_all_teams.php")
            .method("GET")
            .query("s").query("c")
            .willRespondWith()
            .status(200)
            .body(GETTEAMS)
            .toPact()
    }

    @Test
    fun `test get teams data `()  = runBlocking{
        val response = client.getTeams(SPORT, SPAIN)
        val expect = Gson().fromJson(GETTEAMS, League::class.java)
        val result = response.body()
        assertEquals(result?.teams?.get(0)?.idTeam, expect.teams[0].idTeam)
    }

    @Test
    fun `test get last event`()  = runBlocking{
        val response = client.getLastEvents("138277")
        val expect = Gson().fromJson(GETEVENTS, Events::class.java)
        val result = response.body()
        assertEquals(result?.events?.size,expect.events.size)
    }


}