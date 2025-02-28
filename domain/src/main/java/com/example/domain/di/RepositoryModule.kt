package com.example.domain.di

import com.example.domain.db.AppDatabase
import com.example.domain.firebase.FeatureFlagging
import com.example.domain.repository.LeaguesRepository
import com.example.domain.service.LeagueServices
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providerLeagueRepositories(leagueServices: LeagueServices,featureFlagging: FeatureFlagging,
                                   appDatabase: AppDatabase) : LeaguesRepository {
        return LeaguesRepository(leagueServices,featureFlagging,appDatabase)
    }

}