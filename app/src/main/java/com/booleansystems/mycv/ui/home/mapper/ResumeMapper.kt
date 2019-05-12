package com.booleansystems.mycv.ui.home.mapper

import android.os.Parcel
import android.os.Parcelable
import com.booleansystems.domain.entities.Resume as DomainResume

/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */
/**
 * Map entities from our Domain layer to presentation layer data classes
 */
data class Resume(
    val basics: Basics?,
    val work: List<Work>?,
    val skills: List<Skills>?,
    val languages: List<Languages>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Basics::class.java.classLoader)!!,
        parcel.createTypedArrayList(Work)!!,
        parcel.createTypedArrayList(Skills)!!,
        parcel.createTypedArrayList(Languages)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(basics, flags)
        parcel.writeTypedList(work)
        parcel.writeTypedList(skills)
        parcel.writeTypedList(languages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Resume> {
        override fun createFromParcel(parcel: Parcel): Resume {
            return Resume(parcel)
        }

        override fun newArray(size: Int): Array<Resume?> {
            return arrayOfNulls(size)
        }
    }
}

fun DomainResume.toPresentationModel() = Resume(basics!!.toPresentationModel(),
    work!!.map { it.toPresentationModel() }, skills!!.map { it.toPresentationModel() },
    languages!!.map { it.toPresentationModel() })