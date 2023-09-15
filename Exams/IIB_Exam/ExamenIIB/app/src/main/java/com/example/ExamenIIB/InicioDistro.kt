package com.example.ExamenIIB

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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioDistro : AppCompatActivity() {

    var sistemaOSeleccionado=SistemaO("","","",0,"",0)
    val db = Firebase.firestore
    val sistema = db.collection("SistemasO")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Distro>?=null
    var distroSeleccionado:Distro? = Distro("","", "", "", 0, "", "")


    var resultAnadirCancion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                sistemaOSeleccionado = intent.getParcelableExtra<SistemaO>("PosPlaylist")!!
            }
        }

    }

    var resultEditarCancion = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                sistemaOSeleccionado = intent.getParcelableExtra<SistemaO>("PosPlaylist")!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_distro)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        sistemaOSeleccionado = intent.getParcelableExtra<SistemaO>("PosPlaylist")!!
        listViewCanciones()
        val txtNombrePlaylist=findViewById<TextView>(R.id.tv_nombreP_C)
        txtNombrePlaylist.setText("SistemaO: "+sistemaOSeleccionado.nombreSistemaO)

        val btnCrearCancion = findViewById<Button>(R.id.btn_crear_cancion2)
        btnCrearCancion.setOnClickListener {
            abrirActividadAddCancion(CrearDistro::class.java)
        }

        val btnVolverCancion = findViewById<Button>(R.id.btn_volver_cancion)
        btnVolverCancion.setOnClickListener {
            val intentAtrasCancion = Intent(this, InicioSistemaO::class.java)
            startActivity(intentAtrasCancion)
        }

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
        idItemSeleccionado = info.position
        Log.i("context-menu", "ID Distro ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        distroSeleccionado = adaptador!!.getItem(idItemSeleccionado)
        return when (item.itemId) {
            R.id.mi_editarCancion -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarCancion(EditarDistro::class.java)
                return true
            }
            R.id.mi_eliminarCancion -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val playlistSubColeccion= sistema.document("${sistemaOSeleccionado.idSisteO}")
                    .collection("Canciones")
                    .document("${distroSeleccionado!!.idDistro}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-Distro","Con exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-Distro","Fallido")
                    }
                listViewCanciones()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun listViewCanciones() {
        val playlistSubColeccion= sistema.document("${sistemaOSeleccionado.idSisteO}")
            .collection("Canciones")
            .whereEqualTo("idPlaylist","${sistemaOSeleccionado.idSisteO}")

        val canciones_lv = findViewById<ListView>(R.id.lv_canciones_lista)
        playlistSubColeccion.get().addOnSuccessListener { result ->
            var listaCanciones = arrayListOf<Distro>()
            for(document in result){
                listaCanciones.add(
                    Distro(
                        document.id.toString(),
                        document.data.get("idPlaylist").toString(),
                        document.data.get("nombreCancion").toString(),
                        document.data.get("artista").toString(),
                        document.data.get("duracion").toString().toInt(),
                        document.data.get("genero").toString(),
                        document.data.get("anioRelease").toString()
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                listaCanciones
            )
            canciones_lv.adapter=adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(canciones_lv)
        }
    }

    fun abrirActividadEditarCancion(
        clase: Class<*>
    ) {
        val intentEditarCancion = Intent(this, clase)
        intentEditarCancion.putExtra("cancion", distroSeleccionado)
        intentEditarCancion.putExtra("posicionPlaylisteditar",sistemaOSeleccionado)
        resultEditarCancion.launch(intentEditarCancion)
    }

    fun abrirActividadAddCancion(
        clase: Class<*>
    ) {
        val intentAddNewCancion = Intent(this, clase)
        intentAddNewCancion.putExtra("posicionPlaylist", sistemaOSeleccionado)
        resultAnadirCancion.launch(intentAddNewCancion)
    }

}