package com.example.domain.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LeagueDao {

    @Query("SELECT * FROM TeamsDB ")
    fun getAllTeams(): List<TeamsDB>


    @Insert
    fun insertTeamsByLeague(teams: List<TeamsDB>)



}