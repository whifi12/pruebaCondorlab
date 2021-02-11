package com.pruebacondorlabs.di.module

import android.app.Application
import com.pruebacondorlabs.App
import com.pruebacondorlabs.rest.LeagueServices
import com.pruebacondorlabs.util.Constants.BASE_URL
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


@Module(includes = [ViewModelModule::class])
class AppModule() {

    @Singleton
    @Provides
    fun provideGithubService(): Retrofit {

         val okHttpClient =  OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);

        val interceptor = HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(interceptor).build();

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient.build())
            .build();
    }

    @Singleton
    @Provides
    fun providerApplication(app: App) : Application {
        return app;
    }

    @Provides
    @Singleton
    fun providerLeaguesApi(retrofit: Retrofit): LeagueServices {
        return retrofit.create(LeagueServices::class.java)
    }

}