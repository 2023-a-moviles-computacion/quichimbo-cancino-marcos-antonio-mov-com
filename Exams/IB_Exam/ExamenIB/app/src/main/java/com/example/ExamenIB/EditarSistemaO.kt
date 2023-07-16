package com.example.ExamenIB

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
        setContentView(R.layout.activity_editar_sistema_o)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionSistemaO = intent.getIntExtra("posicionEditar",1)

        val editarNombreSistemaO = findViewById<TextInputEditText>(R.id.txtIn_nombreSistemaO_editar2)
        val editarDescripcionSistemaO = findViewById<TextInputEditText>(R.id.txtIn_descripcionSistemaO_editar2)
        val editarFechaLanzSistemaO = findViewById<TextInputEditText>(R.id.txtIn_fechaLanzSistemaO_editar2)
        val editarFileMangerSistemaO = findViewById<TextInputEditText>(R.id.txtIn_fileManagerSistemaO_editar2)
        val editarNumeroVersionesSistemaO = findViewById<TextInputEditText>(R.id.txtIn_numeroVersionesSistemaO_editar2)

        BBaseDeDatosMemoria.arregloSistemaO.forEachIndexed{ indice: Int, sistemaO : SistemaO ->

            if (indice == posicionSistemaO){
                editarNombreSistemaO.setText(sistemaO.nombreSistemaO.toString())
                editarDescripcionSistemaO.setText(sistemaO.descripcionSistemaO.toString())
                editarFechaLanzSistemaO.setText(sistemaO.lanzamientoSistemaO.toString())
                editarFileMangerSistemaO.setText(sistemaO.fileManagerSistemaO.toString())
                editarNumeroVersionesSistemaO.setText(sistemaO.numeroVersiones.toString())
            }
        }

        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios2)
        btnGuardarCambios.setOnClickListener {
            BBaseDeDatosMemoria.arregloSistemaO.forEachIndexed{ indice: Int, sistemaO: SistemaO ->
                if (indice == posicionSistemaO){

                    sistemaO.nombreSistemaO = editarNombreSistemaO.text.toString()
                    sistemaO.descripcionSistemaO = editarDescripcionSistemaO.text.toString()
                    sistemaO.lanzamientoSistemaO = editarFechaLanzSistemaO.text.toString().toInt()
                    sistemaO.fileManagerSistemaO = editarFileMangerSistemaO.text.toString()
                    sistemaO.numeroVersiones = editarNumeroVersionesSistemaO.text.toString().toInt()
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