package com.booleansystems.mycv.ui.home.mapper

import android.os.Parcel
import android.os.Parcelable
import com.booleansystems.domain.entities.Skills as DomainSkills

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
/**
 * Map entities from our Domain layer to presentation layer data classes
 */
data class Skills(
    val name: String?,
    val level: String?, val keywordswords: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(level)
        parcel.writeStringList(keywordswords)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Skills> {
        override fun createFromParcel(parcel: Parcel): Skills {
            return Skills(parcel)
        }

        override fun newArray(size: Int): Array<Skills?> {
            return arrayOfNulls(size)
        }
    }
}

fun DomainSkills.toPresentationModel(): Skills = Skills(name, level, keywords)

