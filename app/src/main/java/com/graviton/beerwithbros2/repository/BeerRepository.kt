package com.graviton.beerwithbros2.repository

import com.graviton.beerwithbros2.api.BwbApi
import com.graviton.beerwithbros2.model.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val beerApi: BwbApi
) {
    fun getBeers(): Flow<List<Beer>> {
        return flow<List<Beer>> { emit(beerApi.getBeers()) }.flowOn(Dispatchers.IO)
    }
}