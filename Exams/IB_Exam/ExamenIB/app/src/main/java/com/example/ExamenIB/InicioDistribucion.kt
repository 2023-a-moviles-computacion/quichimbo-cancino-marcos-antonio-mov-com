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

class InicioDistribucion : AppCompatActivity() {

    var idItemSeleccionado = 0
    var idSistemaO = 0
    var posicionSistemaO = 0
    var itemS = 0

    var listaDistribuciones = arrayListOf<String>()
    var idP_C = arrayListOf<Int>()

    var resultAnadirDistribucion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                posicionSistemaO = data?.getIntExtra("posicionSistemaO",0)!!
            }
        }

    }

    var resultEditarDistribucion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                posicionSistemaO = data?.getIntExtra("posicionSistemaO",0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_distribucion)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        listaDistribuciones = arrayListOf()
        idP_C = arrayListOf()

        posicionSistemaO = intent.getIntExtra("posicionEditar",1)

        val nombreSistemaODistro = findViewById<TextView>(R.id.tv_nombreSistemaO_D)

        BBaseDeDatosMemoria.arregloSistemaO.forEachIndexed{ indice: Int, sistemaO : SistemaO ->
            if (indice == posicionSistemaO){
                idSistemaO  = sistemaO.idSistemaO
                var label = "SistemaO: ${sistemaO.nombreSistemaO}"
                nombreSistemaODistro.setText(label)
            }
        }

        BBaseDeDatosMemoria.arregloSistemaODistribucion.forEachIndexed{ indice: Int, sistemaODistribucion : SistemaO_Distribucion ->
            if (idSistemaO == sistemaODistribucion.idSistemaO){
                listaDistribuciones.add(sistemaODistribucion.nombreS_D.toString())
                idP_C.add(sistemaODistribucion.idS_D)
            }
        }

        val listViewDistro = findViewById<ListView>(R.id.lv_distribuciones_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaDistribuciones
        )
        listViewDistro.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearDistro = findViewById<Button>(R.id.btn_crear_distro2)
        btnCrearDistro.setOnClickListener {
            abrirActividadAddCancion(CrearDistribucion::class.java)
        }

        val btnVolverDistro = findViewById<Button>(R.id.btn_volver_distro)
        btnVolverDistro.setOnClickListener {
            val intentAtrasDistro = Intent(this, InicioSistemaO::class.java)
            startActivity(intentAtrasDistro)
        }


        this.registerForContextMenu(listViewDistro)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_distro, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemS = id
        idItemSeleccionado = idP_C.elementAt(id)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarDistro -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarCancion(EditarDistribucion::class.java)
                return true
            }
            R.id.mi_eliminarDistro -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                eliminarDistro(idItemSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarCancion(
        clase: Class<*>
    ) {
        val intentEditarDistro = Intent(this, clase)
        intentEditarDistro.putExtra("distro", idItemSeleccionado)
        intentEditarDistro.putExtra("posicionSistemaOeditar",posicionSistemaO)
        resultEditarDistribucion.launch(intentEditarDistro)
    }

    fun abrirActividadAddCancion(
        clase: Class<*>
    ) {
        val intentAddNewDistro = Intent(this, clase)
        intentAddNewDistro.putExtra("posicionSistemaO",posicionSistemaO)
        Log.i("positionSend","${posicionSistemaO}")
        resultAnadirDistribucion.launch(intentAddNewDistro)
    }

    fun eliminarDistro(
        idDistroAEliminar: Int
    ){
        val listViewCancion = findViewById<ListView>(R.id.lv_distribuciones_lista)

        var aux_SistemaO_D = arrayListOf<SistemaO_Distribucion>()

        BBaseDeDatosMemoria.arregloSistemaODistribucion.forEach{ sistemaODistribucion:SistemaO_Distribucion ->
            if(idDistroAEliminar != sistemaODistribucion.idS_D){
                aux_SistemaO_D.add(sistemaODistribucion)
            }
        }

        BBaseDeDatosMemoria.arregloSistemaODistribucion = aux_SistemaO_D

        listaDistribuciones.removeAt(itemS)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaDistribuciones
        )
        listViewCancion .adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

}