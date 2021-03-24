package com.example.domain.di

import com.example.domain.repository.LeaguesRepository
import com.example.domain.service.LeagueServices
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providerLeagueRepositories(leagueServices: LeagueServices) : LeaguesRepository {
        return LeaguesRepository(leagueServices)
    }

}