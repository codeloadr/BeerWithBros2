package com.graviton.beerwithbros2.api

import com.graviton.beerwithbros2.model.BeerData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BwbApi {
    companion object {
        const val BASE_URL = "https://beer9.p.rapidapi.com/"
    }

    @GET("/")
    @Headers("X-RapidAPI-Key: ace8a1fb3amshebdaf1d48d92438p1ea700jsn1fcf7acbfd58")
    suspend fun fetchBeers(): Response<BeerData>
}