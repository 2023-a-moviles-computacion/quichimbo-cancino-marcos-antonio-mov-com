package com.example.ExamenIIB

import android.os.Parcel
import android.os.Parcelable

class Distro (

    var idDistro: String?,
    var idSis_Distro:String?,
    var nombreDistro:String?,
    var arqui: String?,
    var cores: Int?,
    var gestor: String?,
    var anioRelease:String?

    ): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idSis_Distro)
        parcel.writeString(idDistro)
        parcel.writeString(nombreDistro)
        parcel.writeString(arqui)
        parcel.writeInt(cores!!)
        parcel.writeString(gestor)
        parcel.writeString(anioRelease)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${nombreDistro}"
    }

    companion object CREATOR : Parcelable.Creator<Distro> {
        override fun createFromParcel(parcel: Parcel): Distro {
            return Distro(parcel)
        }

        override fun newArray(size: Int): Array<Distro?> {
            return arrayOfNulls(size)
        }
    }


}