package com.example.domain.di


import com.example.domain.db.AppDatabase
import com.example.domain.firebase.FeatureFlagging
import com.example.domain.firebase.FirebaseFeatureFlagging
import com.example.utilities.base.BaseActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.Module
import dagger.Provides

@Module
class LibraryModule {

    @Provides
    fun provideRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setConfigSettingsAsync(FirebaseRemoteConfigSettings.Builder().build())
        val default = HashMap<String, Any>()
        remoteConfig.setDefaultsAsync(default)
        return remoteConfig
    }

    @Provides
    fun providerFirebaseFeatureFlagging(remoteConfig : FirebaseRemoteConfig): FeatureFlagging {
        return FirebaseFeatureFlagging(remoteConfig)
    }

   @Provides
   fun providerAppDatabase() = AppDatabase.getDatabase(BaseActivity.context)
}