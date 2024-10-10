package com.graviton.beerwithbros2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.graviton.beerwithbros2.api.BwbApi
import com.graviton.beerwithbros2.model.Beer
import com.graviton.beerwithbros2.room.BeerDAO
import com.graviton.beerwithbros2.room.BeerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val beerApi: BwbApi,
    private val beerDao: BeerDAO,
) {
    /**
     * Get all beers from the database.
     */
    fun getBeers(): LiveData<List<Beer>> = beerDao.getAllBeers().map { beers ->
        beers.map { beer ->
            Beer(
                beer.id,
                beer.name,
                beer.tagline,
                beer.description,
            )
        }
    }

    /**
     * Fetch beers from the API and insert them into the database.
     */
    suspend fun fetchBeers() {
        val response = beerApi.fetchBeers()
        if (response.isSuccessful) {
            response.body()?.let { beerData ->
                beerDao.insertBeers(beerData.beers.map { beer ->
                    BeerEntity(
                        beer.id ?: 0,
                        beer.name ?: "",
                        beer.tagline ?: "",
                        beer.description ?: "",
                    )
                })
            }
        }
    }
}