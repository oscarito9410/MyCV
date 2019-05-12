package com.booleansystems.domain.entities

import com.google.gson.annotations.SerializedName

open class Work(
    @SerializedName("company") val company: String?,
    @SerializedName("position") val position: String?,
    @SerializedName("urlImage") val urlImage: String?,
    @SerializedName("startDate") val startDate: String?,
    @SerializedName("endDate") val endDate: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("highlights") val highlights: List<String>?
)