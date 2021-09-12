package com.example.freelancenepal.entity

import android.os.Parcel
import android.os.Parcelable

class Gig (
    val _id: String? = null,
    val petname: String? = null,
    val petage: String? = null,
    val petpiece: String? = null,
    val petdesc: String? = null,
    val petprice: Int? = null,
    val photo: String? = null
        ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(petname)
        parcel.writeString(petage)
        parcel.writeString(petpiece)
        parcel.writeString(petdesc)
        parcel.writeValue(petprice)
        parcel.writeString(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Gig> {
        override fun createFromParcel(parcel: Parcel): Gig {
            return Gig(parcel)
        }

        override fun newArray(size: Int): Array<Gig?> {
            return arrayOfNulls(size)
        }
    }
}
