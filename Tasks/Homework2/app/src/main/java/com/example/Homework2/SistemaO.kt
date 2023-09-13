package com.example.Homework2

import android.os.Parcel
import android.os.Parcelable

class SistemaO (

    var idSO: Int,
    var nombreSO: String?,
    var descripcionSO: String?,
    var releaseYearSO: Int?,
    var ownerSO: String?,
    var numDistribuciones: Int?
    ): Parcelable {

    override fun toString(): String {
        return "${nombreSO}"
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
        parcel.writeInt(idSO)
        parcel.writeString(nombreSO)
        parcel.writeString(descripcionSO)
        parcel.writeInt(releaseYearSO!!)
        parcel.writeString(ownerSO)
        parcel.writeInt(numDistribuciones!!)
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