package com.example.ExamenIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarDistribucion : AppCompatActivity() {

    var sistemaOListPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_distribucion)
    }

    override fun onStart() {//idPlaylist_Cancion
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val idSistemaO_Distribucion = intent.getIntExtra("distribucion",1)
        sistemaOListPos = intent.getIntExtra("posicionSistemaOeditar",1)

        val txtNombreDistro = findViewById<TextInputEditText>(R.id.txtIn_nombreDistro_editar)
        val txtArquitecturaDistro = findViewById<TextInputEditText>(R.id.txtIn_arquitecturaDistro_editar)
        val txtVersionDistro = findViewById<TextInputEditText>(R.id.txtIn_versionDistro_editar)
        val txtLicenciaDistro = findViewById<TextInputEditText>(R.id.txtIn_licenciaDistro_editar)
        val txtFechaLanzDistro = findViewById<TextInputEditText>(R.id.txtIn_fechaLanzDistro_editar)


        var idSistemaO: Int = 0

        BBaseDeDatosMemoria.arregloSistemaODistribucion.forEachIndexed{ indice: Int, sistemaODistribucion : SistemaO_Distribucion ->
            if (idSistemaO_Distribucion == sistemaODistribucion.idS_D){
                txtNombreDistro.setText(sistemaODistribucion.nombreS_D)
                idSistemaO = sistemaODistribucion.idDistribucion
            }
        }

        BBaseDeDatosMemoria.arregloDistribucion.forEachIndexed{ indice: Int, distribucion : Distribucion ->
            if (idSistemaO == distribucion.idDistribucion){
                txtNombreDistro.setText(distribucion.nombreDistribucion)
                txtArquitecturaDistro.setText(distribucion.arquitecturaDistribucion)
                txtVersionDistro.setText(distribucion.versionDistribucion.toString())
                txtLicenciaDistro.setText(distribucion.licenciaDistribucion)
                txtFechaLanzDistro.setText(distribucion.fechaLanzamientoDistribucion.toString())
            }
        }

        val btnEditarDistribucion = findViewById<Button>(R.id.btn_editar_distribucion)
        btnEditarDistribucion.setOnClickListener {
            BBaseDeDatosMemoria.arregloSistemaODistribucion.forEachIndexed{ indice: Int, sistemaODistribucion: SistemaO_Distribucion ->
                if (idSistemaO_Distribucion == sistemaODistribucion.idS_D){
                    Log.i("editar","${txtNombreDistro.text.toString()}")
                    sistemaODistribucion.nombreS_D = (txtNombreDistro.text.toString())
                }
            }
            BBaseDeDatosMemoria.arregloDistribucion.forEachIndexed{ indice: Int, distribucion: Distribucion ->
                if(idSistemaO==distribucion.idDistribucion){
                    distribucion.nombreDistribucion=txtNombreDistro.text.toString()
                    distribucion.arquitecturaDistribucion=txtArquitecturaDistro.text.toString()
                    distribucion.versionDistribucion=txtVersionDistro.text.toString().toInt()
                    distribucion.licenciaDistribucion=txtLicenciaDistro.text.toString()
                    distribucion.fechaLanzamientoDistribucion=txtFechaLanzDistro.text.toString().toInt()
                }

            }

            respuesta()
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_distribucion_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionSistemaOeditar",sistemaOListPos)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}