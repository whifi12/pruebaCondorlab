package com.example.domain.di

import com.example.domain.repository.LeaguesRepository
import com.example.domain.usecase.GetEventsUseCase
import com.example.domain.usecase.GetTeamsUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class UseCaseModule {

    @Provides
    fun providerGetTeamsUseCase(leaguesRepository: LeaguesRepository) : GetTeamsUseCase {
        return GetTeamsUseCase(Schedulers.io(), AndroidSchedulers.mainThread(),leaguesRepository)
    }

    @Provides
    fun providerGetEventsUseCase(leaguesRepository: LeaguesRepository): GetEventsUseCase{
        return GetEventsUseCase(Schedulers.io(),AndroidSchedulers.mainThread(),leaguesRepository)
    }
}