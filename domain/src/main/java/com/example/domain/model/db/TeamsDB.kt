package com.example.domain.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class TeamsDB {

    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name="idTeam")
    var idTeam : String = ""

    @ColumnInfo(name="name")
    var name : String = ""

    @ColumnInfo(name="stadium")
    var stadium : String = ""

    @ColumnInfo(name="badge")
    var badge : String = ""

    @ColumnInfo(name="jersey")
    var jersey : String? = null

    @ColumnInfo(name="website")
    var website : String = ""

    @ColumnInfo(name="facebook")
    var facebook : String = ""

    @ColumnInfo(name="twitter")
    var twitter : String = ""

    @ColumnInfo(name="instagram")
    var instagram : String = ""

    @ColumnInfo(name="description")
    var description : String = ""

    @ColumnInfo(name="year")
    var year : String = ""


}