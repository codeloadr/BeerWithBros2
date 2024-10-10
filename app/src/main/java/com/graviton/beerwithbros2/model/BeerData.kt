package com.graviton.beerwithbros2.model

import com.google.gson.annotations.SerializedName

data class BeerData(
    @SerializedName("code"  ) var code  : Int?            = null,
    @SerializedName("error" ) var error : Boolean?        = null,
    @SerializedName("data"  ) var beers  : ArrayList<Beer> = arrayListOf()
)
