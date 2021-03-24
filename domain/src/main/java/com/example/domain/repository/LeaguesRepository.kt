package com.example.domain.repository


import com.example.domain.model.Events
import com.example.domain.model.League
import com.example.domain.service.LeagueServices
import io.reactivex.Observable
import javax.inject.Inject

open class LeaguesRepository @Inject constructor(
    private val leagueServices: LeagueServices
): ILeaguesRepositoy {


    override fun getTeams(sport : String, country : String) : Observable<League> {
        return leagueServices.getTeams(sport,country)
    }

    override fun getEvents(id: String): Observable<Events> {
        return leagueServices.getLastEvents(id)
    }

}