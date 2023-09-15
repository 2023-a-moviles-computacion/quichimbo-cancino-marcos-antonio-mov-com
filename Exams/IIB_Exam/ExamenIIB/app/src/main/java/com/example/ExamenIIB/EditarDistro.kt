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

class EditarDistro : AppCompatActivity() {

    var sistemaOSeleccionado = SistemaO("", "", "", 0, "", 0)
    var distroSeleccionado = Distro("","", "", "", 0, "", "")
    val db = Firebase.firestore
    val sistemasO = db.collection("SistemasO")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_distro)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        sistemaOSeleccionado = intent.getParcelableExtra<SistemaO>("posicionPlaylisteditar")!!
        distroSeleccionado = intent.getParcelableExtra<Distro>("distro")!!

        val txtNombreC = findViewById<TextInputEditText>(R.id.txtIn_nombreC_editar)
        val txtartistaC = findViewById<TextInputEditText>(R.id.txtIn_artistaC_editar)
        val txtduracionC = findViewById<TextInputEditText>(R.id.txtIn_duracionC_editar)
        val txtgeneroC = findViewById<TextInputEditText>(R.id.txtIn_generoC_editar)
        val txtanioreleaseC = findViewById<TextInputEditText>(R.id.txtIn_anioC_editar)

                txtNombreC.setText(distroSeleccionado.nombreDistro)
                txtartistaC.setText(distroSeleccionado.arqui)
                txtduracionC.setText(distroSeleccionado.cores.toString())
                txtgeneroC.setText(distroSeleccionado.gestor)
                txtanioreleaseC.setText(distroSeleccionado.anioRelease)


        val btnEditarCancion = findViewById<Button>(R.id.btn_editar_cancion)
        btnEditarCancion.setOnClickListener {
            sistemasO.document("${sistemaOSeleccionado.idSisteO}")
                .collection("Distros")
                .document("${distroSeleccionado.idSis_Distro}")
                .update(
                    "nombreDistro" , txtNombreC.text.toString(),
                    "arquitectura" , txtartistaC.text.toString(),
                    "cores" , txtduracionC.text.toString(),
                    "gestor" , txtgeneroC.text.toString(),
                    "lanzamiento" , txtanioreleaseC.text.toString()
                )

            Toast.makeText(this,"Distro actualizada exitosamente", Toast.LENGTH_SHORT).show()
            val intentEditSucces = Intent(this, InicioDistro::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_cancion_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionPlaylisteditar",sistemaOSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}