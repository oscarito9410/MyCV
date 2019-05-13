package com.booleansystems.domain.entities

import com.google.gson.annotations.SerializedName

open class Basics(

    @SerializedName("name") val name: String?,
    @SerializedName("label") val label: String?,
    @SerializedName("picture") val picture: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("profiles") val profiles: List<Profiles>?
)