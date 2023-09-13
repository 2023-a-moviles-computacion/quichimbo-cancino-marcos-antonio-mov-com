package com.example.Homework2

import android.os.Parcel
import android.os.Parcelable

class Distribucion (

    var idDistro: Int,
    var nombreDistro:String?,
    var arquitecturaDistro: String?,
    var coresDistro: Int?,
    var gestorFilesDistro: String?,
    var releaseDistro:Int?

    ): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idDistro)
        parcel.writeString(nombreDistro)
        parcel.writeString(arquitecturaDistro)
        parcel.writeInt(coresDistro!!)
        parcel.writeString(gestorFilesDistro)
        parcel.writeInt(releaseDistro!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${nombreDistro}"
    }

    companion object CREATOR : Parcelable.Creator<Distribucion> {
        override fun createFromParcel(parcel: Parcel): Distribucion {
            return Distribucion(parcel)
        }

        override fun newArray(size: Int): Array<Distribucion?> {
            return arrayOfNulls(size)
        }
    }


}