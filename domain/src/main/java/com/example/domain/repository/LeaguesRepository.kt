package com.example.domain.repository


import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.service.LeagueServices
import com.example.utilities.util.Result
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

open class LeaguesRepository @Inject constructor(
    private val leagueServices: LeagueServices
): ILeaguesRepositoy {


    override suspend fun getTeams(params: TeamRequest) : Response<League> {
        return leagueServices.getTeams(params.sport,params.country)
    }

    override suspend fun getEvents(id: String): Response<Events> {
        return leagueServices.getLastEvents(id)
    }

}