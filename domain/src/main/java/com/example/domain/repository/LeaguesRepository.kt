package com.example.domain.repository


import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.service.LeagueServices
import io.reactivex.Observable
import javax.inject.Inject

open class LeaguesRepository @Inject constructor(
    private val leagueServices: LeagueServices
): ILeaguesRepositoy {


    override fun getTeams(params: TeamRequest) : Observable<League> {
        return leagueServices.getTeams(params.sport,params.country)
    }

    override fun getEvents(id: String): Observable<Events> {
        return leagueServices.getLastEvents(id)
    }

}