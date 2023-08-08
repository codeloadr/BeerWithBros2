package com.graviton.beerwithbros2.api

import com.graviton.beerwithbros2.model.Beer
import retrofit2.http.GET

interface BwbApi {
    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }

    @GET("beers")
    suspend fun getBeers(): List<Beer>
}