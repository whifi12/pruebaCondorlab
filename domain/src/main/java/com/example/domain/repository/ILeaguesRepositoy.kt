package com.example.domain.repository


import com.example.domain.model.request.EventRequest
import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.model.response.Teams
import com.example.utilities.util.Result
import io.reactivex.Observable
import retrofit2.Response


interface ILeaguesRepositoy {

    fun saveDataTeams(teams: List<Teams>)

    fun getTeams() : League

    fun getEventsDB(id :String) : Events

    fun saveEvents(eventRequest: EventRequest)

    suspend fun getTeams(params: TeamRequest) : Response<League>

    suspend fun getEvents(id : String) : Response<Events>

    fun getConfig() : Boolean

}