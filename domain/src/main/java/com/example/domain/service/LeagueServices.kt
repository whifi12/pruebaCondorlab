package com.example.domain.service


import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueServices {

    @GET("search_all_teams.php")
    suspend fun getTeams(@Query("s") soccer: String, @Query("c") country: String): Response<League>

    @GET("eventslast.php")
    suspend fun getLastEvents(@Query("id") id: String): Response<Events>

}