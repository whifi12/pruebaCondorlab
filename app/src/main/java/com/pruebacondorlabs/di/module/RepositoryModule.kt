package com.pruebacondorlabs.di.module

import com.pruebacondorlabs.repositories.LeaguesRepository
import com.pruebacondorlabs.rest.LeagueServices
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
class RepositoryModule {

    @Provides
    fun providerLeagueRepositories(leagueServices: LeagueServices) : LeaguesRepository {
        return LeaguesRepository(leagueServices)
    }

}