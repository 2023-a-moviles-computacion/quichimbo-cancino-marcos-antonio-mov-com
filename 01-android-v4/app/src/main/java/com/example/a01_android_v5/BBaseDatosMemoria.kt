package com.example.a01_android_v5

class BBaseDatosMemoria {
    //datos quemados en memoria
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Adrian","a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Vicente","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Carolina","c@c.com")
                )
        }
    }
}