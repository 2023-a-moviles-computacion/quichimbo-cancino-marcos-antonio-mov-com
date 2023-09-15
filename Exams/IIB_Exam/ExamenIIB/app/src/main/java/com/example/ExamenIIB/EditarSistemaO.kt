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

class EditarSistemaO : AppCompatActivity() {

    var sistemaOSeleccionado = SistemaO("", "", "", 0, "", 0)
    val db = Firebase.firestore
    val playlists = db.collection("SistemasO")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_editar_sistema)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        sistemaOSeleccionado = intent.getParcelableExtra<SistemaO>("PosPlaylist")!!

        val editarNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreP_editar2)
        val editarDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar2)
        val editarAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar2)
        val editarAutorPlaylist = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar2)
        val editarNumC = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar2)

        editarNombreP.setText(sistemaOSeleccionado.nombreSistemaO)
        editarDescripcion.setText(sistemaOSeleccionado.descripcionS.toString())
        editarAnioCreacion.setText(sistemaOSeleccionado.anioCreadoS.toString())
        editarAutorPlaylist.setText(sistemaOSeleccionado.fabricanteS.toString())
        editarNumC.setText(sistemaOSeleccionado.numDistros.toString())


        val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambios2)
        btnGuardarCambios.setOnClickListener {
            playlists.document("${sistemaOSeleccionado.idSisteO}").update(
                "nombreSistemaO" , editarNombreP.text.toString(),
                "descripcionSistemaO" , editarDescripcion.text.toString(),
                "anioCreacion" , editarAnioCreacion.text.toString(),
                "autorPSistemaO" , editarAutorPlaylist.text.toString(),
                "numDistros" , editarNumC.text.toString()
            )
            Toast.makeText(this,"SistemaO actualizado exitosamente", Toast.LENGTH_SHORT).show()

            val intentEditSucces = Intent(this, InicioSistemaO::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_editar2)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, InicioSistemaO::class.java)
            startActivity(intentEditCancel)
        }

    }

}