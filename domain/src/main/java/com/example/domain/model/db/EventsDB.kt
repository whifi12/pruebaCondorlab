package com.example.domain.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class EventsDB {

    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name="idTeam")
    var idTeam : String = ""

    @ColumnInfo(name="match")
    var match : String = ""
}