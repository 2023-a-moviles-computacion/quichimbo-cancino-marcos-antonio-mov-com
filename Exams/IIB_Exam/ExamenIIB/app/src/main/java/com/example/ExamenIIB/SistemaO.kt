package com.example.ExamenIIB

import android.os.Parcel
import android.os.Parcelable

class SistemaO (

    var idSisteO: String?,
    var nombreSistemaO: String?,
    var descripcionS: String?,
    var anioCreadoS: Int?,
    var fabricanteS: String?,
    var numDistros: Int?
    ): Parcelable {

    override fun toString(): String {
        return "${nombreSistemaO}"
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idSisteO)
        parcel.writeString(nombreSistemaO)
        parcel.writeString(descripcionS)
        parcel.writeInt(anioCreadoS!!)
        parcel.writeString(fabricanteS)
        parcel.writeInt(numDistros!!)
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