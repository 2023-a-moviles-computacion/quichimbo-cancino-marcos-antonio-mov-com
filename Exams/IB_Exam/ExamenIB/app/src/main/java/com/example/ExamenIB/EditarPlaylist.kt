package com.example.ExamenIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarPlaylist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_editar_playlist)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionPlaylist = intent.getIntExtra("posicionEditar",1)

        val editarNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreSistemaO_editar2)
        val editarDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar2)
        val editarAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar2)
        val editarAutorPlaylist = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar2)
        val editarNumC = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar2)

        BBaseDeDatosMemoria.arregloSistemaO.forEachIndexed{ indice: Int, sistemaO : SistemaO ->

            if (indice == posicionPlaylist){
                editarNombreP.setText(sistemaO.nombreSistemaO.toString())
                editarDescripcion.setText(sistemaO.descripcionSistemaO.toString())
                editarAnioCreacion.setText(sistemaO.lanzamientoSistemaO.toString())
                editarAutorPlaylist.setText(sistemaO.fileManagerSistemaO.toString())
                editarNumC.setText(sistemaO.numeroVersiones.toString())
            }
        }

        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios2)
        btnGuardarCambios.setOnClickListener {
            BBaseDeDatosMemoria.arregloSistemaO.forEachIndexed{ indice: Int, sistemaO: SistemaO ->
                if (indice == posicionPlaylist){

                    sistemaO.nombreSistemaO = editarNombreP.text.toString()
                    sistemaO.descripcionSistemaO = editarDescripcion.text.toString()
                    sistemaO.lanzamientoSistemaO = editarAnioCreacion.text.toString().toInt()
                    sistemaO.fileManagerSistemaO = editarAutorPlaylist.text.toString()
                    sistemaO.numeroVersiones = editarNumC.text.toString().toInt()
                }
            }
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