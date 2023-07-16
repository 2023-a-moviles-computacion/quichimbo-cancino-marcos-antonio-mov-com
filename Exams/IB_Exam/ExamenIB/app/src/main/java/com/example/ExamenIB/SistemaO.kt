package com.example.ExamenIB

import android.os.Parcel
import android.os.Parcelable

class SistemaO (

    val idSistemaO: Int,
    var nombreSistemaO: String?,
    var descripcionSistemaO: String?,
    var lanzamientoSistemaO: Int?,
    var fileManagerSistemaO: String?,
    var numeroVersiones: Int?
    ): Parcelable {

    override fun toString(): String {
        return "${nombreSistemaO}"
    }

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
        parcel.writeInt(idSistemaO)
        parcel.writeString(nombreSistemaO)
        parcel.writeString(descripcionSistemaO)
        parcel.writeInt(lanzamientoSistemaO!!)
        parcel.writeString(fileManagerSistemaO)
        parcel.writeInt(numeroVersiones!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SistemaO> {
        override fun createFromParcel(parcel: Parcel): SistemaO {
            return SistemaO(parcel)
        }

        override fun newArray(size: Int): Array<SistemaO?> {
            return arrayOfNulls(size)
        }
    }

}