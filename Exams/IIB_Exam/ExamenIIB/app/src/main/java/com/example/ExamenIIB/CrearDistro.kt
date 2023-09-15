package com.example.ExamenIIB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearDistro : AppCompatActivity() {

    var sistemaOSeleccionado = SistemaO("", "", "", 0, "", 0)
    val db = Firebase.firestore
    val sisteO = db.collection("SistemasO")
    val distros = db.collection("Distros")
    var idDistroSelect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_distro)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        sistemaOSeleccionado = intent.getParcelableExtra<SistemaO>("posicionPlaylist")!!
        val playlistSubColeccion = sisteO.document("${sistemaOSeleccionado.idSisteO}")
            .collection("Distros")


        var txtNombre = findViewById<TextInputEditText>(R.id.txtIn_nombreC_crear)
        var txtArtista = findViewById<TextInputEditText>(R.id.txtIn_artistaC_crear)
        var txtDuracion = findViewById<TextInputEditText>(R.id.txtIn_duracionC_crear)
        var txtGenero= findViewById<TextInputEditText>(R.id.txtIn_generoC_crear)
        var txtAnioRelease = findViewById<TextInputEditText>(R.id.txtIn_anioC_crear)

        Log.i("posPlaylist", "${sistemaOSeleccionado.idSisteO}")

        var btnAddCancion= findViewById<Button>(R.id.btn_crear_cancion)
        btnAddCancion.setOnClickListener {
            var cancion = hashMapOf(
                "idSistemaO" to sistemaOSeleccionado.idSisteO,
                "nombreDistro" to txtNombre.text.toString(),
                "arquitectura" to txtArtista.text.toString(),
                "cores" to txtDuracion.text.toString(),
                "gestor" to txtGenero.text.toString(),
                "lanzamiento" to txtAnioRelease.text.toString()
            )

            playlistSubColeccion.add(cancion).addOnSuccessListener {
                Toast.makeText(this, "Distro registrada exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Distro", "Con exito")
            }.addOnFailureListener {
                Log.i("Crear-Distro", "Fallido")
            }

            val intentAddSucces = Intent(this, InicioDistro::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarCancion = findViewById<Button>(R.id.btn_cancelar_cancion_crear)
        btnCancelarCancion.setOnClickListener {
            respuesta()
        }
    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posPlaylist", sistemaOSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}