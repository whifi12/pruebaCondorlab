package com.example.domain.service


import com.example.domain.model.Events
import com.example.domain.model.League
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueServices {

    @GET("search_all_teams.php")
    fun getTeams(@Query("s") soccer: String, @Query("c") country: String): Observable<League>

    @GET("eventslast.php")
    fun getLastEvents(@Query("id") id: String): Observable<Events>

}