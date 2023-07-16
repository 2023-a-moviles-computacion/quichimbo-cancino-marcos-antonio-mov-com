package com.example.ExamenIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearDistribucion : AppCompatActivity() {

    var siguienteId = 0
    var anteriorId = 0
    var idDistribucionSeleccionada = 0
    var posicionSistemaO = 0
    var idSistemaOT = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_distribucion)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        posicionSistemaO = intent.getIntExtra("posicionSistemaO",-1)
        Log.i("posicionSistemaO","${posicionSistemaO}")

        BBaseDeDatosMemoria.arregloSistemaO.forEachIndexed{ indice: Int, sistemaO : SistemaO ->

            if (indice == posicionSistemaO){
                idSistemaOT = sistemaO.idSistemaO
            }
        }

        var longDistro = BBaseDeDatosMemoria.arregloSistemaODistribucion.lastIndex

        BBaseDeDatosMemoria.arregloSistemaODistribucion.forEachIndexed{ indice: Int, sistemaODistribucion : SistemaO_Distribucion ->

            if (indice == longDistro){
                anteriorId = sistemaODistribucion.idS_D
            }
        }

        siguienteId = anteriorId+1

        var txtNombreDistro = findViewById<TextInputEditText>(R.id.txtIn_nombreDistro_crear)
        var txtArquitecturaDistro = findViewById<TextInputEditText>(R.id.txtIn_arquitecturaDistro_crear)
        var txtVersionDistro = findViewById<TextInputEditText>(R.id.txtIn_VersionDistro_crear)
        var txtLicenciaDistro= findViewById<TextInputEditText>(R.id.txtIn_LicenciaDistro_crear)
        var txtFechaLanzamientoDistro = findViewById<TextInputEditText>(R.id.txtIn_fechaLanzamientoDistro_crear)

        var btnAddDistro= findViewById<Button>(R.id.btn_crear_distro)
        btnAddDistro.setOnClickListener {
            var nombreDistro = txtNombreDistro.text.toString()
            var arquitecturaDistro= txtArquitecturaDistro.text.toString()
            var versionDistro= txtVersionDistro.text.toString().toInt()
            var licenciaDistro= txtLicenciaDistro.text.toString()
            var fechaLanzDistro= txtFechaLanzamientoDistro.text.toString().toInt()
            BBaseDeDatosMemoria.arregloSistemaODistribucion.add(
                SistemaO_Distribucion(siguienteId,nombreDistro,idSistemaOT,idDistribucionSeleccionada)
            )
            BBaseDeDatosMemoria.arregloDistribucion.add(
                Distribucion(idDistribucionSeleccionada,nombreDistro,arquitecturaDistro,versionDistro,licenciaDistro,fechaLanzDistro)
            )
            respuesta()
        }

        var btnCancelarDistribucion = findViewById<Button>(R.id.btn_cancelar_distribucion_crear)
        btnCancelarDistribucion.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionSistemaO",posicionSistemaO)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}