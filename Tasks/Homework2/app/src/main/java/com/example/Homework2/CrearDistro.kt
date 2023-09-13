package com.example.Homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class CrearDistro : AppCompatActivity() {

    var nextIdDistro = 0
    var lastIDDistro = 0

    var nextIdSistemaO_Distro=0
    var lastIDSistemaO_Distro=0
    var idDistroSeleccionada = 0
    var sistemaOPos = 0
    var idSistemaOT = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_distro)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        sistemaOPos = intent.getIntExtra("posicionSistemaO",-1)
        Log.i("posSistemaO","${sistemaOPos}")

        EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO().forEachIndexed{ indice: Int, sistemaO : SistemaO ->
            if (indice == sistemaOPos){
                idSistemaOT = sistemaO.idSO
            }
        }

        var longLDistro = EquipoBaseDeDatos.TablaSistemaO!!.listarDistro().lastIndex

        EquipoBaseDeDatos.TablaSistemaO!!.listarDistro().forEachIndexed{ indice: Int, distribucion : Distribucion ->
            Log.i("testExamen","${distribucion.idDistro} -> ${distribucion.nombreDistro}")
            if (indice == longLDistro){
                lastIDDistro = distribucion.idDistro
            }
        }

        nextIdDistro = lastIDDistro+1

        var long_SistemaO_Distro = Registros.arregloSistemaODistro.lastIndex
        Registros.arregloSistemaODistro.forEachIndexed{ indice: Int, e_j:SistemaO_Distro ->
            if(indice==long_SistemaO_Distro){
                lastIDSistemaO_Distro=e_j.idSistemaO_Distro
            }

        }
        nextIdSistemaO_Distro=lastIDSistemaO_Distro+1

        var txtNombreDistro = findViewById<TextInputEditText>(R.id.txtIn_nombreDistro_crear)
        var txtArquitecturaDistro = findViewById<TextInputEditText>(R.id.txtIn_arquitecturaDistro_crear)
        var txtCoreDistro = findViewById<TextInputEditText>(R.id.txtIn_coreDistro_crear)
        var txtGestorFilesDistro= findViewById<TextInputEditText>(R.id.txtIn_gestorFilesDistro_crear)
        var txtReleaseDistro = findViewById<TextInputEditText>(R.id.txtIn_releaseDistro_crear)

        var btnAddDistro= findViewById<Button>(R.id.btn_crear_distro)
        btnAddDistro.setOnClickListener {
            var nombreDistro = txtNombreDistro.text.toString()
            var arquitecturaDistro= txtArquitecturaDistro.text.toString()
            var coreDistro= txtCoreDistro.text.toString()
            var gestorFilesDistro= txtGestorFilesDistro.text.toString()
            var releaseDistro= txtReleaseDistro.text.toString()
            Registros.arregloSistemaODistro.add(
                SistemaO_Distro(nextIdSistemaO_Distro,idSistemaOT, nextIdDistro)
            )
            EquipoBaseDeDatos.TablaSistemaO!!.crearDistro(nextIdDistro,nombreDistro,arquitecturaDistro,coreDistro,gestorFilesDistro,releaseDistro)
            respuesta()
        }

        var btnCancelarDistro = findViewById<Button>(R.id.btn_cancelar_distro_crear)
        btnCancelarDistro.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionSistemaO",sistemaOPos)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}