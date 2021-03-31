package com.example.domain.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject

class FirebaseFeatureFlagging @Inject constructor(private val remoteConfig: FirebaseRemoteConfig) : FeatureFlagging {


    init {
        remoteConfig.fetch().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // RemoteConfig fetch must be activated
                remoteConfig.activate()
            } else {
                Log.d(TAG, "RemoteConfig fetch failed!")
            }
        }
    }

    override val showInAppDetail: Boolean
        get() = remoteConfig.getBoolean("detail")
}