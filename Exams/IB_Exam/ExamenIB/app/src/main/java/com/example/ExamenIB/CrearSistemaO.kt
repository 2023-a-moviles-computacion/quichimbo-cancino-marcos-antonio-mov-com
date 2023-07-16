package com.example.ExamenIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearSistemaO : AppCompatActivity() {

    var siguienteId = 0
    var anteriorId = 0
    var nombreSistemaO = ""
    var descripcionSistemaO= ""
    var fechaLanzamientoSistemaO= 0
    var fileManagerSistemaO = ""
    var numeroVersionesSistemaO = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sistema_o)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")
        var longitudListaEntreador = BBaseDeDatosMemoria.arregloSistemaO.lastIndex
        BBaseDeDatosMemoria.arregloSistemaO.forEachIndexed{ indice: Int, sistemaO : SistemaO ->
            Log.i("testExamen","${sistemaO.idSistemaO} -> ${sistemaO.nombreSistemaO}")
            if (indice == longitudListaEntreador){
                anteriorId = sistemaO.idSistemaO
            }
        }

        siguienteId = anteriorId+1

        var txtInNombreSistemaO = findViewById<TextInputEditText>(R.id.txtIn_nombreSistemaO_editar2)
        var txtInDescripcionSistemaO = findViewById<TextInputEditText>(R.id.txtIn_descripcionSistemaO_editar)
        var txtInFechaLanzSistemaO = findViewById<TextInputEditText>(R.id.txtIn_fechaLanzSistemaO_editar)
        var txtInFileManagerSistemaO = findViewById<TextInputEditText>(R.id.txtIn_fileManagerSistemaO_editar)
        var txtInNumeroVersionesSistemaO = findViewById<TextInputEditText>(R.id.txtIn_numeroVersionesSistemaO_editar)

        var btnCrearSistemaO = findViewById<Button>(R.id.btn_guardar_cambios)
        btnCrearSistemaO.setOnClickListener {
            nombreSistemaO = txtInNombreSistemaO.text.toString()
            descripcionSistemaO = txtInDescripcionSistemaO.text.toString()
            fechaLanzamientoSistemaO= txtInFechaLanzSistemaO.text.toString().toInt()
            fileManagerSistemaO= txtInFileManagerSistemaO.text.toString()
            numeroVersionesSistemaO= txtInNumeroVersionesSistemaO.text.toString().toInt()

            BBaseDeDatosMemoria.arregloSistemaO.add(
                SistemaO(siguienteId, nombreSistemaO, descripcionSistemaO,fechaLanzamientoSistemaO,fileManagerSistemaO,numeroVersionesSistemaO)
            )
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