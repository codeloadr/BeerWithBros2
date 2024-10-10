package com.graviton.beerwithbros2.workmanager

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun setupPeriodicBeerFeedSync(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED) // Require network connectivity
        .setRequiresBatteryNotLow(true) // Require battery not low
        .setRequiresStorageNotLow(true) // Require storage not low
        .setRequiresCharging(false) // Optionally, require device to be charging
        .setRequiresDeviceIdle(false) // Optionally, require device to be idle
        .build()

    val periodicWorkRequest = PeriodicWorkRequestBuilder<BeerFeedSyncWorker>(
        15, TimeUnit.MINUTES // Sync every 15 minutes
    )
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "BeerFeedSync",
        ExistingPeriodicWorkPolicy.KEEP, // Keep existing work, don't overwrite
        periodicWorkRequest
    )
}