package com.example.domain.repository


import com.example.domain.model.Events
import com.example.domain.model.League
import io.reactivex.Observable

interface ILeaguesRepositoy {

    fun getTeams(sport : String, country : String) : Observable<League>

    fun getEvents(id : String) : Observable<Events>
}