package com.example.Homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarSistemaO : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_editar_sistemao)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionSistemaO = intent.getIntExtra("posicionEditar",1)

        val editarNombreSistemaO = findViewById<TextInputEditText>(R.id.txtIn_nombreSistemaO_editar2)
        val editarDescripcionSistemaO = findViewById<TextInputEditText>(R.id.txtIn_descripcionSistemaO_editar2)
        val editarReleaseYearSistemaO = findViewById<TextInputEditText>(R.id.txtIn_releaseYearSistemaO_editar2)
        val editarOwnerSistemaO = findViewById<TextInputEditText>(R.id.txtIn_ownerSistemaO_editar2)
        val editarNumDistros = findViewById<TextInputEditText>(R.id.txtIn_numDistros_editar2)

        EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO().forEachIndexed{ indice: Int, sistemaO : SistemaO ->

            if (indice == posicionSistemaO){
                editarNombreSistemaO.setText(sistemaO.nombreSO.toString())
                editarDescripcionSistemaO.setText(sistemaO.descripcionSO.toString())
                editarReleaseYearSistemaO.setText(sistemaO.releaseYearSO.toString())
                editarOwnerSistemaO.setText(sistemaO.ownerSO.toString())
                editarNumDistros.setText(sistemaO.numDistribuciones.toString())
            }
        }

        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios2)
        btnGuardarCambios.setOnClickListener {
            //BBaseDeDatosMemoria.arregloPlaylist.forEachIndexed{ indice: Int, playlist: SistemaO ->
              //  if (indice == posicionPlaylist){

                    var nombreSistemaO = editarNombreSistemaO.text.toString()
                    var descripcionSistemaO = editarDescripcionSistemaO.text.toString()
                    var releaseYearSistemaO = editarReleaseYearSistemaO.text.toString().toInt()
                    var ownerSistemaO = editarOwnerSistemaO.text.toString()
                    var numDistros = editarNumDistros.text.toString().toInt()
                    EquipoBaseDeDatos.TablaSistemaO!!.actualizarSistemaO(posicionSistemaO,
                        nombreSistemaO,descripcionSistemaO,releaseYearSistemaO.toString(),ownerSistemaO,numDistros.toString())
               // }
            val intentEditSucces = Intent(this, InicioSistemaO::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_editar2)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, InicioSistemaO::class.java)
            startActivity(intentEditCancel)
        }

    }

}