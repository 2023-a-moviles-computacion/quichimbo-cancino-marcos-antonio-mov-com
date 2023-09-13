package com.example.Homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarDistro : AppCompatActivity() {

    var sistemaO_pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_distro)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        sistemaO_pos = intent.getIntExtra("posicionSistemaOeditar",1)

        val txtNombreDistro = findViewById<TextInputEditText>(R.id.txtIn_nombreDistro_editar)
        val txtArquitecturaDistro = findViewById<TextInputEditText>(R.id.txtIn_arquitecturaDistro_editar)
        val txtCoresDistro = findViewById<TextInputEditText>(R.id.txtIn_coresDistro_editar)
        val txtGestorFilesDistro = findViewById<TextInputEditText>(R.id.txtIn_gestorFilesDistro_editar)
        val txtReleaseDistro = findViewById<TextInputEditText>(R.id.txtIn_releaseDistro_editar)

        var idDistro = intent.getIntExtra("distro",1)


        EquipoBaseDeDatos.TablaSistemaO!!.listarDistro().forEachIndexed{ indice: Int, distribucion : Distribucion ->
            if (distribucion.idDistro == idDistro){
                txtNombreDistro.setText(distribucion.nombreDistro)
                txtArquitecturaDistro.setText(distribucion.arquitecturaDistro)
                txtCoresDistro.setText(distribucion.coresDistro.toString())
                txtGestorFilesDistro.setText(distribucion.gestorFilesDistro)
                txtReleaseDistro.setText(distribucion.releaseDistro.toString())
            }
        }

        val btnEditarDistro = findViewById<Button>(R.id.btn_editar_distro)
        btnEditarDistro.setOnClickListener {
            /*BBaseDeDatosMemoria.arregloSistemaODistro.forEachIndexed{ indice: Int, playlist_cancion: SistemaO_Distro ->
                if (idPlaylist_Cancion == playlist_cancion.idPlaylist_Cancion){
                    Log.i("editar","${txtNombreC.text.toString()}")
                    playlist_cancion.nombreP_C = (txtNombreC.text.toString())
                }
            }*/
            //BBaseDeDatosMemoria.arregloCancion.forEachIndexed{ indice: Int, cancion: Distribucion ->
              //  if(idPlaYlist==cancion.idCancion){
                    var nombreDistro=txtNombreDistro.text.toString()
                    var arquitecturaDistro=txtArquitecturaDistro.text.toString()
                    var coresDistro=txtCoresDistro.text.toString()
                    var gestorFilesDistro=txtGestorFilesDistro.text.toString()
                    var releaseDistro=txtReleaseDistro.text.toString()
            EquipoBaseDeDatos.TablaSistemaO!!.actualizarDistro(idDistro,nombreDistro,arquitecturaDistro,coresDistro,gestorFilesDistro,releaseDistro)
                //}

            //}

            respuesta()
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_distro_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionSistemaOeditar",sistemaO_pos)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}