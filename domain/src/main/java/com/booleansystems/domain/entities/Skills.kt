package com.booleansystems.domain.entities

import com.google.gson.annotations.SerializedName

open class Skills (

	@SerializedName("name") val name : String?,
	@SerializedName("level") val level : String?,
	@SerializedName("keywords") val keywords : List<String>?
)