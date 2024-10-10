package com.graviton.beerwithbros2.model

import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("sku") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("brewery") var tagline: String? = null,
    @SerializedName("description") var description: String? = null,
)
