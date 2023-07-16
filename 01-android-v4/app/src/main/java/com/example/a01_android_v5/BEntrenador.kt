package com.example.a01_android_v5

class BEntrenador(
    var id: Int,
    var nombre: String?,
    var description: String?,
){
    override fun toString(): String {
        return "${nombre}-${description}"
    }
}
//hasta mim: 13