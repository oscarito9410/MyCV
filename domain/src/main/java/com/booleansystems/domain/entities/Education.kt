package com.booleansystems.domain.entities

import com.google.gson.annotations.SerializedName

open class Education(
    @SerializedName("institution") val institution: String?,
    @SerializedName("area") val area: String?,
    @SerializedName("studyType") val studyType: String?,
    @SerializedName("startDate") val startDate: String?,
    @SerializedName("endDate") val endDate: String?,
    @SerializedName("gpa") val gpa: Double?,
    @SerializedName("courses") val courses: List<String>?
)