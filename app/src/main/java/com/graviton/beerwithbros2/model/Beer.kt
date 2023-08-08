package com.graviton.beerwithbros2.model

import com.google.gson.annotations.SerializedName

data class Beer(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("first_brewed") var firstBrewed: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("image_url") var imageUrl: String? = null,
)
