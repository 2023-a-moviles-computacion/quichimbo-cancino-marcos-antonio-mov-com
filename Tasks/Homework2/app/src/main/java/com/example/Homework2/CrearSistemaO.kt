package com.example.Homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearSistemaO : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombreSistemaO = ""
    var descripcionSistemaO= ""
    var anioCreacionSistemaO= ""
    var creadorSistemaO = ""
    var numDistros = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sistemao)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
        var longitudListaEntreador = EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO().lastIndex
        EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO().forEachIndexed{ indice: Int, sistemaO : SistemaO ->
            Log.i("testExamen","${sistemaO.idSO} -> ${sistemaO.nombreSO}")
            if (indice == longitudListaEntreador){
                lastId = sistemaO.idSO
            }
        }

        nextId = lastId+1

        var txtInNombreSistemaO = findViewById<TextInputEditText>(R.id.txtIn_nombreSistemaO_editar2)
        var txtInDescripcionSistemaO = findViewById<TextInputEditText>(R.id.txtIn_descripcionSistemaO_editar)
        var txtInAnioCreacionSistemaO = findViewById<TextInputEditText>(R.id.txtIn_anioCreacionSistemaO_editar)
        var txtInCreadorSistemaO = findViewById<TextInputEditText>(R.id.txtIn_creadorSistemaO_editar)
        var txtInNumDistros = findViewById<TextInputEditText>(R.id.txtIn_numDistros_editar)

        var btnCrearSistemaO = findViewById<Button>(R.id.btn_guardar_cambios)
        btnCrearSistemaO.setOnClickListener {
            nombreSistemaO = txtInNombreSistemaO.text.toString()
            descripcionSistemaO = txtInDescripcionSistemaO.text.toString()
            anioCreacionSistemaO= txtInAnioCreacionSistemaO.text.toString()
            creadorSistemaO= txtInCreadorSistemaO.text.toString()
            numDistros= txtInNumDistros.text.toString()

            EquipoBaseDeDatos.TablaSistemaO!!.crearSistemaO(nextId, nombreSistemaO, descripcionSistemaO,anioCreacionSistemaO,creadorSistemaO,numDistros)
            val intentAddSucces = Intent(this, InicioSistemaO::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarSistemaO = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarSistemaO.setOnClickListener {
            val intentAddCancel = Intent(this, InicioSistemaO::class.java)
            startActivity(intentAddCancel)
        }
    }

}