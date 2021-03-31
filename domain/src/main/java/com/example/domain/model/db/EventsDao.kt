package com.example.domain.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventsDao {

    @Query("SELECT * FROM EventsDB  WHERE idTeam = :id ")
    fun getEvents(id : String): List<EventsDB>

    @Insert
    fun insertTeamsByEvents(teams: List<EventsDB>)
}