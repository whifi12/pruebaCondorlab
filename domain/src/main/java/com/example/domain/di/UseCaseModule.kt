package com.example.domain.di

import com.example.domain.repository.LeaguesRepository
import com.example.domain.usecase.*
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

    @Provides
    fun providerGetConfig(leaguesRepository: LeaguesRepository) : GetConfigUseCase {
        return GetConfigUseCase(leaguesRepository)
    }

    @Provides
    fun providerGetTeamsDBUseCase(leaguesRepository: LeaguesRepository) : GetTeamsDBUseCase {
        return GetTeamsDBUseCase(leaguesRepository)
    }

    @Provides
    fun providerSaveTeamsUseCase(leaguesRepository: LeaguesRepository) : SaveTeamsUseCase {
        return SaveTeamsUseCase(leaguesRepository)
    }

    @Provides
    fun providerGetEventsDBUseCase(leaguesRepository: LeaguesRepository): GetEventsDBUseCase {
        return GetEventsDBUseCase(leaguesRepository)
    }

    @Provides
    fun providerSaveEventsUseCase(leaguesRepository: LeaguesRepository): SaveEventsUseCase {
        return SaveEventsUseCase(leaguesRepository)
    }


}