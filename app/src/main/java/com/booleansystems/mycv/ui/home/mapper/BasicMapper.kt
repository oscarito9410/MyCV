package com.booleansystems.mycv.ui.home.mapper

import android.os.Parcel
import android.os.Parcelable
import com.booleansystems.domain.entities.Basics as DomainBasics


/**

Created by oscar on 11/05/19
operez@na-at.com.mx
 */

/**
 * Map entities from our Domain layer to presentation layer data classes
 */
data class Basics(
    val name: String?, val label: String?, val picture: String?,
    val email: String?, val phone: String?, val website: String?,
    val summary: String?, val linkedIn: String?, val gitHub: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!, parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(label)
        parcel.writeString(picture)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(website)
        parcel.writeString(summary)
        parcel.writeString(linkedIn)
        parcel.writeString(gitHub)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Basics> {
        override fun createFromParcel(parcel: Parcel): Basics {
            return Basics(parcel)
        }

        override fun newArray(size: Int): Array<Basics?> {
            return arrayOfNulls(size)
        }
    }
}

fun DomainBasics.toPresentationModel(): Basics = Basics(
    name, label, picture, email, phone, website, summary, profiles!!.firstOrNull {
        it.network.equals("linkedin", true)
    }!!.url, profiles!!.firstOrNull { it.network.equals("github", true) }!!.url
)