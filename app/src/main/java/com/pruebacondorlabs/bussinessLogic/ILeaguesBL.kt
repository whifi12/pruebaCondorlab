package com.pruebacondorlabs.bussinessLogic


import com.pruebacondorlabs.models.Events
import com.pruebacondorlabs.models.League
import io.reactivex.Observable

interface ILeaguesBL {

    fun getTeams(sport : String, country : String) : Observable<League>

    fun getEvents(id : String) : Observable<Events>
}