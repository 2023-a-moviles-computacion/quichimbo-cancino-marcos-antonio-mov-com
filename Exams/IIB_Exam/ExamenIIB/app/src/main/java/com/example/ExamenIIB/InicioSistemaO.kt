package com.example.ExamenIIB

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

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class InicioSistemaO : AppCompatActivity() {
    val db = Firebase.firestore
    val sistemasO = db.collection("SistemasO")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<SistemaO>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sistema)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        listarPlaylists()
        val btnAnadirPlaylist = findViewById<Button>(R.id.btn_crear_nueva_playlist)
        btnAnadirPlaylist.setOnClickListener {
            val intentAddPlaylist = Intent(this, CrearSistemaO::class.java)
            startActivity(intentAddPlaylist)
        }
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
        var sistemaOSeleccionado:SistemaO = adaptador!!.getItem(idItemSeleccionado)!!

        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${sistemaOSeleccionado.idSisteO}")
                val abrirEditarSistemaO = Intent(this, EditarSistemaO::class.java)
                abrirEditarSistemaO.putExtra("PosPlaylist",sistemaOSeleccionado)
                startActivity(abrirEditarSistemaO)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                sistemasO.document("${sistemaOSeleccionado.idSisteO}").delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-SistemaO", "Exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-SistemaO","Fallido")
                    }
                listarPlaylists()
                return true
            }

            R.id.mi_canciones -> {
                Log.i("context-menu", "Canciones: ${idItemSeleccionado}")
                val abrirInicioCanciones = Intent(this, InicioDistro::class.java)
                abrirInicioCanciones.putExtra("PosPlaylist",sistemaOSeleccionado)
                startActivity(abrirInicioCanciones)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listarPlaylists(){
        val lv_playlists = findViewById<ListView>(R.id.lv_playlists_lista)
        sistemasO.get().addOnSuccessListener{ result ->
            var sistemaOLista = arrayListOf<SistemaO>()
            for (document in result) {
                sistemaOLista.add(
                    SistemaO(
                        document.id.toString(),
                        document.get("nombrePlaylist").toString(),
                        document.get("descripcionPlaylist").toString(),
                        document.get("anioCreacion").toString().toInt(),
                        document.get("autorPlaylist").toString(),
                        document.get("numCanciones").toString().toInt(),
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                sistemaOLista
            )
            lv_playlists.adapter = adaptador
            adaptador!!.notifyDataSetChanged()
            registerForContextMenu(lv_playlists)

        }.addOnFailureListener{
            Log.i("Error", "Creacion de lista de playlists fallida")
        }
    }

}