package com.pruebacondorlabs.bussinessLogic


import com.pruebacondorlabs.models.League
import io.reactivex.Observable

interface ILeaguesBL {

    fun getTeams(sport : String, country : String) : Observable<League>
}