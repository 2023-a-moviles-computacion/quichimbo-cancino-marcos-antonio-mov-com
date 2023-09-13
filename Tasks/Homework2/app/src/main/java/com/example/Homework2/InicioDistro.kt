package com.example.Homework2

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

class InicioDistro : AppCompatActivity() {

    var idItemSeleccionado = 0
    var idSistemaO = 0
    var sistemaOPos = 0
    var itemS = 0


    var resultAnadirDistro = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                val data = result.data
                sistemaOPos = data?.getIntExtra("posicionSistemaO", 0)!!
            }
        }

    }

    var resultEditarDistro = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null) {
                val data = result.data
                sistemaOPos = data?.getIntExtra("posicionSistemaO", 0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_distro)
    }

    fun listViewDistros(): ArrayList<Distribucion> {
        var listaIDDistros = arrayListOf<Int>()

        Registros.arregloSistemaODistro.forEachIndexed { indice: Int, sistemaOistro: SistemaO_Distro ->
            if (sistemaOistro.idSistemaO == idSistemaO) {
                listaIDDistros.add(sistemaOistro.idDistro)
            }
        }
        var listaDistros = arrayListOf<Distribucion>()
        EquipoBaseDeDatos.TablaSistemaO!!.listarDistro()
            .forEachIndexed { indice: Int, distribucion: Distribucion ->
                for (i in listaIDDistros) {
                    if (i == distribucion.idDistro) {
                        listaDistros.add(distribucion)
                    }
                }
            }
        return listaDistros
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        sistemaOPos = intent.getIntExtra("posicionEditar", 1)

        EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO()
            .forEachIndexed { indice: Int, sistemaO: SistemaO ->
                if (indice == sistemaOPos) {
                    val txtNOmbreSistemaO = findViewById<TextView>(R.id.tv_nombreSistemaO)
                    txtNOmbreSistemaO.setText("SistemaO: " + sistemaO.nombreSO)
                    idSistemaO = sistemaO.idSO
                }
            }


        val listViewDistro = findViewById<ListView>(R.id.lv_distros_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewDistros()
        )
        listViewDistro.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnCrearDistro = findViewById<Button>(R.id.btn_crear_distro2)
        btnCrearDistro.setOnClickListener {
            abrirActividadAddCancion(CrearDistro::class.java)
        }

        val btnVolverDistro = findViewById<Button>(R.id.btn_volver_distro)
        btnVolverDistro.setOnClickListener {
            val intentAtrasDistro = Intent(this, InicioSistemaO::class.java)
            startActivity(intentAtrasDistro)
        }


        this.registerForContextMenu(listViewDistro)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val listViewCancion = findViewById<ListView>(R.id.lv_distros_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listViewDistros()
        )
        listViewCancion.adapter = adaptador
        adaptador.notifyDataSetChanged()

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
        val idR = listViewDistros()[id].idDistro
        idItemSeleccionado = idR//idP_C.elementAt(id)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarDistro -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarDistro(EditarDistro::class.java)
                return true
            }
            R.id.mi_eliminarDistro -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                EquipoBaseDeDatos.TablaSistemaO!!.eliminarDistros(idItemSeleccionado)
                val listViewDistro = findViewById<ListView>(R.id.lv_distros_lista)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listViewDistros()
                )
                listViewDistro.adapter = adaptador
                adaptador.notifyDataSetChanged()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarDistro(
        clase: Class<*>
    ) {
        val intentEditarDistro = Intent(this, clase)
        intentEditarDistro.putExtra("distro", idItemSeleccionado)
        intentEditarDistro.putExtra("posicionSistemaOeditar", sistemaOPos)
        resultEditarDistro.launch(intentEditarDistro)
    }

    fun abrirActividadAddCancion(
        clase: Class<*>
    ) {
        val intentAddNewDistro = Intent(this, clase)
        intentAddNewDistro.putExtra("posicionSistemaO", sistemaOPos)
        Log.i("positionSend", "${sistemaOPos}")
        resultAnadirDistro.launch(intentAddNewDistro)
    }
}