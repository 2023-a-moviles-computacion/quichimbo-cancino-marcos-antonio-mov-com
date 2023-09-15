package com.example.ExamenIIB

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CrearSistemaO : AppCompatActivity() {

    val db = Firebase.firestore
    val sistemaO = db.collection("SistemasO")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sistemao)
    }

    override fun onStart() {
        super.onStart()

        var txtInNombreP = findViewById<TextInputEditText>(R.id.txtIn_nombreP_editar2)
        var txtInDescripcion = findViewById<TextInputEditText>(R.id.txtIn_descripcionP_editar)
        var txtInAnioCreacion = findViewById<TextInputEditText>(R.id.txtIn_anioP_editar)
        var txtInAutorP = findViewById<TextInputEditText>(R.id.txtIn_autorP_editar)
        var txtInNumCancion = findViewById<TextInputEditText>(R.id.txtIn_numCP_editar)

        var btnCrearPlaylist = findViewById<Button>(R.id.btn_guardar_cambios)
        btnCrearPlaylist.setOnClickListener {
            var playlist = hashMapOf(
                "nombreSistemaO" to txtInNombreP.text.toString(),
                "descripcionSistemaO" to txtInDescripcion.text.toString(),
                "anioCreacion" to txtInAnioCreacion.text.toString(),
                "autorPSistemaO" to txtInAutorP.text.toString(),
                "numDistros" to txtInNumCancion.text.toString()
            )

            sistemaO.add(playlist).addOnSuccessListener {
                txtInNombreP.text!!.clear()
                txtInDescripcion.text!!.clear()
                txtInAnioCreacion.text!!.clear()
                txtInAutorP.text!!.clear()
                txtInNumCancion.text!!.clear()
                Toast.makeText(this,"SistemaO registrado con exito", Toast.LENGTH_SHORT).show();
                Log.i("Crear-SistemaO","Success")
            }.addOnSuccessListener {
                Log.i("Crear-SistemaO","Failed")
            }


            val intentAddSucces = Intent(this, InicioSistemaO::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarPlaylist = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarPlaylist.setOnClickListener {
            val intentAddCancel = Intent(this, InicioSistemaO::class.java)
            startActivity(intentAddCancel)
        }
    }

}