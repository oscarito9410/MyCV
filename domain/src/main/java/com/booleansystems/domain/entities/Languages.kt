package com.booleansystems.domain.entities

import com.google.gson.annotations.SerializedName

open class Languages (
	@SerializedName("language") val language : String?,
	@SerializedName("fluency") val fluency : String?
)