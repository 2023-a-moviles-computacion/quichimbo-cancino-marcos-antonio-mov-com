package com.example.Homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class InicioSistemaO : AppCompatActivity() {

    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sistemao)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val listViewEntrenador = findViewById<ListView>(R.id.lv_sistemaO_lista)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO()
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewEntrenador)

        val btnAnadirEntrenador = findViewById<Button>(R.id.btn_crear_nueva_distro)
        btnAnadirEntrenador.setOnClickListener {
            val intentAddEntrenador = Intent(this, CrearSistemaO::class.java)
            startActivity(intentAddEntrenador)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("idItemSeleccionado",idItemSeleccionado)
            putParcelableArrayList("arregloEntrenador",EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO())
            putParcelableArrayList("arregloP-C",Registros.arregloSistemaODistro)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        idItemSeleccionado = savedInstanceState.getInt("idItemSeleccionado")

        Registros.arregloSistemaODistro = savedInstanceState.getParcelableArrayList<SistemaO_Distro>("arregloP-C")!!
        //BBaseDeDatosMemoria.arregloCancion = savedInstanceState.getParcelableArrayList<Distribucion>("arregloPokemon")!!
        //BBaseDeDatosMemoria.arregloPlaylist = savedInstanceState.getParcelableArrayList<SistemaO>("arregloEntrenador")!!
        //BBaseDeDatosMemoria.arregloSistemaODistro = savedInstanceState.getParcelableArrayList<SistemaO_Distro>("arregloEntrenadorXpokemon")!!
        if (idItemSeleccionado == null){
            idItemSeleccionado = 0
        }
        val listViewEntrenador = findViewById<ListView>(R.id.lv_sistemaO_lista)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO()
            //BBaseDeDatosMemoria.arregloPlaylist
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadConParametros(EditarSistemaO::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                EquipoBaseDeDatos.TablaSistemaO!!.eliminarSistemaO(idItemSeleccionado)
                val listViewSistemaO = findViewById<ListView>(R.id.lv_sistemaO_lista)
                val adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO()
                )
                listViewSistemaO.adapter = adaptador
                adaptador.notifyDataSetChanged()
                //eliminarEntrenador(idItemSeleccionado)
                return true
            }
            R.id.mi_distros -> {
                Log.i("context-menu", "Pokemons: ${idItemSeleccionado}")
                abrirActividadConParametros(InicioDistro::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarEntrenador = Intent(this, clase)
        intentEditarEntrenador.putExtra("posicionEditar", idItemSeleccionado)
        startActivity(intentEditarEntrenador)
    }

}