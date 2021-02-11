package com.pruebacondorlabs.rest


import com.pruebacondorlabs.models.League
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueServices {

    @GET("search_all_teams.php")
    fun getTeams(@Query("s") soccer: String, @Query("c") country: String): Observable<League>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String): Observable<League>

}