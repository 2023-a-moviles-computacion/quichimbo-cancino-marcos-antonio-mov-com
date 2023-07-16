package com.example.ExamenIB

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

class InicioCancion : AppCompatActivity() {

    var idItemSeleccionado = 0
    var idPlaylisT = 0
    var playlistPos = 0
    var itemS = 0

    var listaCanciones = arrayListOf<String>()
    var idP_C = arrayListOf<Int>()

    var resultAnadirCancion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                playlistPos = data?.getIntExtra("posicionPlaylist",0)!!
            }
        }

    }

    var resultEditarCancion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                playlistPos = data?.getIntExtra("posicionPlaylist",0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_cancion)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        listaCanciones = arrayListOf()
        idP_C = arrayListOf()

        playlistPos = intent.getIntExtra("posicionEditar",1)

        val nombrePlaylist_cancion = findViewById<TextView>(R.id.tv_nombreP_C)

        BBaseDeDatosMemoria.arregloSistemaO.forEachIndexed{ indice: Int, sistemaO : SistemaO ->
            if (indice == playlistPos){
                idPlaylisT  = sistemaO.idSistemaO
                var label = "SistemaO: ${sistemaO.nombreSistemaO}"
                nombrePlaylist_cancion.setText(label)
            }
        }

        BBaseDeDatosMemoria.arregloSistemaODistribucion.forEachIndexed{ indice: Int, sistemaODistribucion : SistemaO_Distribucion ->
            if (idPlaylisT == sistemaODistribucion.idSistemaO){
                listaCanciones.add(sistemaODistribucion.nombreS_D.toString())
                idP_C.add(sistemaODistribucion.idS_D)
            }
        }

        val listViewCancion = findViewById<ListView>(R.id.lv_canciones_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaCanciones
        )
        listViewCancion.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearCancion = findViewById<Button>(R.id.btn_crear_cancion2)
        btnCrearCancion.setOnClickListener {
            abrirActividadAddCancion(CrearDistribucion::class.java)
        }

        val btnVolverCancion = findViewById<Button>(R.id.btn_volver_cancion)
        btnVolverCancion.setOnClickListener {
            val intentAtrasCancion = Intent(this, InicioSistemaO::class.java)
            startActivity(intentAtrasCancion)
        }


        this.registerForContextMenu(listViewCancion)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cancion, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemS = id
        idItemSeleccionado = idP_C.elementAt(id)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarCancion -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarCancion(EditarCancion::class.java)
                return true
            }
            R.id.mi_eliminarCancion -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                eliminarCancion(idItemSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarCancion(
        clase: Class<*>
    ) {
        val intentEditarCancion = Intent(this, clase)
        intentEditarCancion.putExtra("cancion", idItemSeleccionado)
        intentEditarCancion.putExtra("posicionPlaylisteditar",playlistPos)
        resultEditarCancion.launch(intentEditarCancion)
    }

    fun abrirActividadAddCancion(
        clase: Class<*>
    ) {
        val intentAddNewCancion = Intent(this, clase)
        intentAddNewCancion.putExtra("posicionPlaylist",playlistPos)
        Log.i("positionSend","${playlistPos}")
        resultAnadirCancion.launch(intentAddNewCancion)
    }

    fun eliminarCancion(
        idCancionAeliminar: Int
    ){
        val listViewCancion = findViewById<ListView>(R.id.lv_canciones_lista)

        var aux_P_C = arrayListOf<SistemaO_Distribucion>()

        BBaseDeDatosMemoria.arregloSistemaODistribucion.forEach{ sistemaODistribucion:SistemaO_Distribucion ->
            if(idCancionAeliminar != sistemaODistribucion.idS_D){
                aux_P_C.add(sistemaODistribucion)
            }
        }

        BBaseDeDatosMemoria.arregloSistemaODistribucion = aux_P_C

        listaCanciones.removeAt(itemS)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaCanciones
        )
        listViewCancion .adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

}