package com.example.ExamenIB

import android.os.Parcel
import android.os.Parcelable

class SistemaO_Distribucion (

    val idS_D: Int,
    var nombreS_D: String?,
    val idSistemaO: Int,
    val idDistribucion: Int

    ): Parcelable  {

    override fun toString(): String {
        return "${nombreS_D}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idS_D)
        parcel.writeString(nombreS_D)
        parcel.writeInt(idSistemaO)
        parcel.writeInt(idDistribucion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SistemaO_Distribucion> {
        override fun createFromParcel(parcel: Parcel): SistemaO_Distribucion {
            return SistemaO_Distribucion(parcel)
        }

        override fun newArray(size: Int): Array<SistemaO_Distribucion?> {
            return arrayOfNulls(size)
        }
    }

}