package com.example.domain.di

import com.example.domain.service.LeagueServices
import com.example.utilities.util.Constants
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideGithubService(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Provides
    @Singleton
    fun providerLeaguesApi(retrofit: Retrofit): LeagueServices {
        return retrofit.create(LeagueServices::class.java)
    }

}