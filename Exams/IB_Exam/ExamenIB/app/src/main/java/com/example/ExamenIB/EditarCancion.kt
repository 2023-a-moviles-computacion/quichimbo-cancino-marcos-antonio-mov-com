package com.example.ExamenIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarCancion : AppCompatActivity() {

    var playlistPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cancion)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val idPlaylist_Cancion = intent.getIntExtra("cancion",1)
        playlistPos = intent.getIntExtra("posicionPlaylisteditar",1)

        val txtNombreC = findViewById<TextInputEditText>(R.id.txtIn_nombreC_editar)
        val txtartistaC = findViewById<TextInputEditText>(R.id.txtIn_artistaC_editar)
        val txtduracionC = findViewById<TextInputEditText>(R.id.txtIn_duracionC_editar)
        val txtgeneroC = findViewById<TextInputEditText>(R.id.txtIn_generoC_editar)
        val txtanioreleaseC = findViewById<TextInputEditText>(R.id.txtIn_anioC_editar)


        var idPlaYlist: Int = 0

        BBaseDeDatosMemoria.arregloSistemaODistribucion.forEachIndexed{ indice: Int, sistemaODistribucion : SistemaO_Distribucion ->
            if (idPlaylist_Cancion == sistemaODistribucion.idS_D){
                txtNombreC.setText(sistemaODistribucion.nombreS_D)
                idPlaYlist = sistemaODistribucion.idDistribucion
            }
        }

        BBaseDeDatosMemoria.arregloDistribucion.forEachIndexed{ indice: Int, distribucion : Distribucion ->
            if (idPlaYlist == distribucion.idDistribucion){
                txtNombreC.setText(distribucion.nombreDistribucion)
                txtartistaC.setText(distribucion.arquitecturaDistribucion)
                txtduracionC.setText(distribucion.versionDistribucion.toString())
                txtgeneroC.setText(distribucion.licenciaDistribucion)
                txtanioreleaseC.setText(distribucion.fechaLanzamientoDistribucion.toString())
            }
        }

        val btnEditarCancion = findViewById<Button>(R.id.btn_editar_cancion)
        btnEditarCancion.setOnClickListener {
            BBaseDeDatosMemoria.arregloSistemaODistribucion.forEachIndexed{ indice: Int, sistemaODistribucion: SistemaO_Distribucion ->
                if (idPlaylist_Cancion == sistemaODistribucion.idS_D){
                    Log.i("editar","${txtNombreC.text.toString()}")
                    sistemaODistribucion.nombreS_D = (txtNombreC.text.toString())
                }
            }
            BBaseDeDatosMemoria.arregloDistribucion.forEachIndexed{ indice: Int, distribucion: Distribucion ->
                if(idPlaYlist==distribucion.idDistribucion){
                    distribucion.nombreDistribucion=txtNombreC.text.toString()
                    distribucion.arquitecturaDistribucion=txtartistaC.text.toString()
                    distribucion.versionDistribucion=txtduracionC.text.toString().toInt()
                    distribucion.licenciaDistribucion=txtgeneroC.text.toString()
                    distribucion.fechaLanzamientoDistribucion=txtanioreleaseC.text.toString().toInt()
                }

            }

            respuesta()
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_cancion_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionPlaylisteditar",playlistPos)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}