package com.booleansystems.mycv.ui.home.mapper

import android.os.Parcel
import android.os.Parcelable
import com.booleansystems.domain.entities.Languages as DomainLanguages

/**
Created by oscar on 11/05/19
operez@na-at.com.mx
 */
/**
 * Map entities from our Domain layer to presentation layer data classes
 */
data class Languages(
    val language: String?,
    val fluency: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(language)
        parcel.writeString(fluency)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Languages> {
        override fun createFromParcel(parcel: Parcel): Languages {
            return Languages(parcel)
        }

        override fun newArray(size: Int): Array<Languages?> {
            return arrayOfNulls(size)
        }
    }
}

fun DomainLanguages.toPresentationModel(): Languages = Languages(language, fluency)