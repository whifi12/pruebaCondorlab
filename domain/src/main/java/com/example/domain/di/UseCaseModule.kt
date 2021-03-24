package com.example.domain.di

import com.example.domain.repository.LeaguesRepository
import com.example.domain.usecase.GetTeamsUseCase
import com.example.domain.usecase.IGetTeamUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providerGetTeamsUseCase(leaguesRepository: LeaguesRepository) : IGetTeamUseCase {
        return GetTeamsUseCase(leaguesRepository)
    }
}