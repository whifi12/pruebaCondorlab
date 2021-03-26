package com.example.domain.di

import com.example.domain.repository.LeaguesRepository
import com.example.domain.usecase.GetEventsUseCase
import com.example.domain.usecase.GetTeamsUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers

@Module
class UseCaseModule {

    @Provides
    fun providerGetTeamsUseCase(leaguesRepository: LeaguesRepository) : GetTeamsUseCase {
        return GetTeamsUseCase(leaguesRepository,Dispatchers.IO)
    }

    @Provides
    fun providerGetEventsUseCase(leaguesRepository: LeaguesRepository): GetEventsUseCase{
        return GetEventsUseCase(leaguesRepository,Dispatchers.IO)
    }
}