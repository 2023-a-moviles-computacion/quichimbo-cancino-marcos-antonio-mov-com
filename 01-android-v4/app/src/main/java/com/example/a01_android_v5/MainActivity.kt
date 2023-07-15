package com.example.a01_android_v5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ESCUCHAR LOS EVENTOS CICLO VIDA LIST VIEW
        //MANDAR A LA ACTIVIDAD PERTINENT

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener{
                irActividad(AACicloVida::class.java)
            }
        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }
    }git
    fun irActividad(
        clase: Class <*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}