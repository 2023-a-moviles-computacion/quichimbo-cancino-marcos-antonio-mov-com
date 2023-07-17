package com.example.a01_android_v5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreDescripcion (
    // crear este adaptador
    //hacer heredar de una clase que se usa para crear los recyclerView
    private val contexto: FRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreDescripcion.MyViewHolder>(){
    inner class MyViewHolder(view:View): RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val descripcionTextView: TextView
        val likesTextView: TextView
        val accionButton: Button
        var numeroLikes = 0
        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            descripcionTextView = view.findViewById(R.id.tv_descripcion)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionButton = view.findViewById<Button>(R.id.btn_dar_like)
            accionButton.setOnClickListener { anadirLike() }
        }
        fun anadirLike(){
            numeroLikes = numeroLikes +1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLIkes()
            //recyclerView.adapter?.notifyDataSetChanged()
        }
    }
    //cual layout vamos a settear

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }
    // set the data to the iteration
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.description
        holder.descripcionTextView.text = entrenadorActual.description
        holder.likesTextView.text = "0"
        holder.accionButton.text = "ID: ${entrenadorActual.id} Nombre:${entrenadorActual.nombre}"
    }
     //definir el tamaño del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}