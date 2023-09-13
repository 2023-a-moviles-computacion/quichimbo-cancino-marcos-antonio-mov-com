package com.example.Homework2

import android.os.Parcel
import android.os.Parcelable

class SistemaO_Distro (

    val idSistemaO_Distro: Int,
    //var nombreP_C: String?,
    val idSistemaO: Int,
    val idDistro: Int

    ): Parcelable  {

    //override fun toString(): String {
      //  return "${nombreP_C}"
    //}

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        //parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idSistemaO_Distro)
        //parcel.writeString(nombreP_C)
        parcel.writeInt(idSistemaO)
        parcel.writeInt(idDistro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SistemaO_Distro> {
        override fun createFromParcel(parcel: Parcel): SistemaO_Distro {
            return SistemaO_Distro(parcel)
        }

        override fun newArray(size: Int): Array<SistemaO_Distro?> {
            return arrayOfNulls(size)
        }
    }

}