package com.example.domain.repository


import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import io.reactivex.Observable

interface ILeaguesRepositoy {

    fun getTeams(params: TeamRequest) : Observable<League>

    fun getEvents(id : String) : Observable<Events>
}