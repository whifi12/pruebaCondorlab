package com.pruebacondorlabs.repositories


import com.pruebacondorlabs.bussinessLogic.ILeaguesBL
import com.pruebacondorlabs.models.Events
import com.pruebacondorlabs.models.League
import com.pruebacondorlabs.rest.LeagueServices
import io.reactivex.Observable
import javax.inject.Inject

open class LeaguesRepository @Inject constructor(
    private val leagueServices: LeagueServices): ILeaguesBL {


    override fun getTeams(sport : String, country : String) : Observable<League> {
        return leagueServices.getTeams(sport,country)
    }

    override fun getEvents(id: String): Observable<Events> {
        return leagueServices.getLastEvents(id)
    }

}