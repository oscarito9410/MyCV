package com.booleansystems.domain.entities

import com.google.gson.annotations.SerializedName

open class Resume(
    @SerializedName("basics") val basics: Basics?,
    @SerializedName("work") val work: List<Work>?,
    @SerializedName("skills") val skills: List<Skills>?,
    @SerializedName("languages") val languages: List<Languages>?
)