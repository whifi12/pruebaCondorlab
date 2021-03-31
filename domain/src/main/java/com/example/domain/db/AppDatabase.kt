package com.example.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.model.db.EventsDB
import com.example.domain.model.db.EventsDao
import com.example.domain.model.db.LeagueDao
import com.example.domain.model.db.TeamsDB

@Database(entities = [TeamsDB::class,EventsDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao
    abstract fun eventsDao(): EventsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "DB_NAME")
                .allowMainThreadQueries()
                .build()
    }

}