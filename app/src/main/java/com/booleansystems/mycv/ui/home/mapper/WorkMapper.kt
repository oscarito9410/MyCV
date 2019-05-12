package com.booleansystems.mycv.ui.home.mapper

import android.os.Parcel
import android.os.Parcelable
import com.booleansystems.domain.entities.Work as DomainWork

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
/**
 * Map entities from our Domain layer to presentation layer data classes
 */
data class Work(
    val company: String?,
    val position: String?,
    val urlImage: String?,
    val startDate: String?,
    val endDate: String?,
    val summary: String?,
    val highlights: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(company)
        parcel.writeString(position)
        parcel.writeString(urlImage)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeString(summary)
        parcel.writeStringList(highlights)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Work> {
        override fun createFromParcel(parcel: Parcel): Work {
            return Work(parcel)
        }

        override fun newArray(size: Int): Array<Work?> {
            return arrayOfNulls(size)
        }
    }
}

fun DomainWork.toPresentationModel(): Work = Work(
    company, position, urlImage, startDate, endDate, summary, highlights
)