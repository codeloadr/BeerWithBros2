package com.graviton.beerwithbros2

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.graviton.beerwithbros2.workmanager.setupPeriodicBeerFeedSync
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltAndroidApp
class BWBApplication : Application(), Configuration.Provider {
    override fun onCreate() {
        super.onCreate()
        setupPeriodicBeerFeedSync(this)
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory)
            .setExecutor(Executors.newSingleThreadExecutor()).build()
}