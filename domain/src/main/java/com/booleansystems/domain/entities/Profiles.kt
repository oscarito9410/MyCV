package com.booleansystems.domain.entities

import com.google.gson.annotations.SerializedName

open class Profiles(
    @SerializedName("network") val network: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("url") val url: String?
)