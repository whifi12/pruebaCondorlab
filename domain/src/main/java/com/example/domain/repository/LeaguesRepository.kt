package com.example.domain.repository


import com.example.domain.db.AppDatabase
import com.example.domain.firebase.FeatureFlagging
import com.example.domain.model.request.EventRequest
import com.example.domain.model.request.TeamRequest
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.model.response.Teams
import com.example.domain.service.LeagueServices
import com.example.domain.util.Mapper
import retrofit2.Response
import javax.inject.Inject

open class LeaguesRepository @Inject constructor(
    private val leagueServices: LeagueServices,
    private val featureFlagging: FeatureFlagging,
    private val localDataSource: AppDatabase
): ILeaguesRepositoy {

    override fun saveDataTeams(teams: List<Teams>) {
        val data = Mapper.convertLeagueToTeamsDB(teams)
        localDataSource.leagueDao().insertTeamsByLeague(teams = data)
    }

    override fun getTeams(): League {
        val data = localDataSource.leagueDao().getAllTeams()
        return Mapper.convertTeamsDBtoLeague(data)
    }


    override suspend fun getTeams(params: TeamRequest) : Response<League> {
        return leagueServices.getTeams(params.sport,params.country)
    }

    override fun getEventsDB(id: String): Events {
        val data = localDataSource.eventsDao().getEvents(id)
        return Mapper.convertEventsDBToEvents(data)
    }

    override fun saveEvents(eventRequest: EventRequest) {
        val data = Mapper.convertEventsToEventsDB(eventRequest.id,eventRequest.events)
        localDataSource.eventsDao().insertTeamsByEvents(data)
    }

    override suspend fun getEvents(id: String): Response<Events> {
        return leagueServices.getLastEvents(id)
    }

    override fun getConfig(): Boolean {
        return featureFlagging.showInAppDetail
    }

}