package com.example.ExamenIB

import android.os.Parcel
import android.os.Parcelable

class Distribucion (

    val idDistribucion: Int,
    var nombreDistribucion:String?,
    var arquitecturaDistribucion: String?,
    var versionDistribucion: Int?,
    var licenciaDistribucion: String?,
    var fechaLanzamientoDistribucion:Int?

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
        parcel.writeInt(idDistribucion)
        parcel.writeString(nombreDistribucion)
        parcel.writeString(arquitecturaDistribucion)
        parcel.writeInt(versionDistribucion!!)
        parcel.writeString(licenciaDistribucion)
        parcel.writeInt(fechaLanzamientoDistribucion!!)
    }

    override fun describeContents(): Int {
        return 0
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