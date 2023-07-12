package com.example.movilescomputacion2023a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {// va a estar casi toda la logica del negocio
    //dentro de android una actividad es una pantalla
    // el primer despliegue fue del main activity
    //ahora estamos creando una nueva actividad para abrirla desde un boton
    //brinda ciertos metodos y atributos
    // startActivity, setContentView - son metodos de la clase
    // en kotlin no es necesario usar la palbra this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //estoy usando esta clase y esta interface
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent) //funciones - metodos dentro de la clase , gracias al AppCompatActivity()
    }
}