package com.example.domain.util

import com.example.domain.model.db.EventsDB
import com.example.domain.model.db.TeamsDB
import com.example.domain.model.response.Events
import com.example.domain.model.response.League
import com.example.domain.model.response.Match
import com.example.domain.model.response.Teams

class Mapper {
    companion object {

        fun convertTeamsDBtoLeague(leagueDB: List<TeamsDB>): League {
            val league = League()
            val teams = ArrayList<Teams>()
            for (teamsDB in leagueDB) {
                val team = Teams()
                team.idTeam = teamsDB.idTeam
                team.name = teamsDB.name
                team.stadium = teamsDB.stadium
                team.badge = teamsDB.badge
                team.jersey = teamsDB.jersey
                team.website = teamsDB.website
                team.facebook = teamsDB.facebook
                team.twitter = teamsDB.twitter
                team.instagram = teamsDB.instagram
                team.description = teamsDB.description
                team.year = teamsDB.year
                teams.add(team)
            }
            league.teams = teams
            return league
        }

        fun convertLeagueToTeamsDB(leagueDB: List<Teams>): List<TeamsDB> {
            val teams = ArrayList<TeamsDB>()
            for (team in leagueDB) {
                val teamsDB = TeamsDB()
                teamsDB.idTeam = team.idTeam
                teamsDB.name = team.name
                teamsDB.stadium = team.stadium
                teamsDB.badge = team.badge ?: ""
                teamsDB.jersey = team.jersey ?: ""
                teamsDB.website = team.website
                teamsDB.facebook = team.facebook
                teamsDB.twitter = team.twitter
                teamsDB.instagram = team.instagram
                teamsDB.description = team.description
                teamsDB.year = team.year
                teams.add(teamsDB)
            }
            return teams
        }

        fun convertEventsDBToEvents(eventsDB: List<EventsDB>): Events {
            val events = Events()
            val matches = ArrayList<Match>()
            for (event in eventsDB) {
                val match = Match()
                match.event = event.match
                matches.add(match)
            }
            events.events = matches
            return events
        }

        fun convertEventsToEventsDB(id : String,matches: List<Match>): List<EventsDB> {
            val events = ArrayList<EventsDB>()
            for (match in matches) {
                val event = EventsDB()
                event.idTeam = id
                event.match = match.event
                events.add(event)
            }
            return events
        }


    }


}