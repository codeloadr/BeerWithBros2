package com.graviton.beerwithbros2.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.graviton.beerwithbros2.repository.BeerRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class BeerFeedSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val beerRepository: BeerRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            beerRepository.fetchBeers()
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}

