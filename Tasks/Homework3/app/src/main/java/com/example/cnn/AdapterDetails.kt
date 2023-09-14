package com.example.cnn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterDetails(
    private val archivList: List<Details>,
    private val contexto: DetailsActivities,
    private val recycler: RecyclerView

) : RecyclerView.Adapter<AdapterDetails.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_settings_documento, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagen = archivList[position].imagenNoticia
        val nombre = archivList[position].nombreNoticia
        val almacenamiento = archivList[position].escritoPorNoticia
        val fecha = archivList[position].desarrolloNoticia
        holder.setData(imagen, nombre, almacenamiento, fecha)
    }

    override fun getItemCount(): Int {
        return archivList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView
        private val textView: TextView
        private val textView2: TextView
        private val textView3: TextView

        init {
            imageView = itemView.findViewById(R.id.imArchivo)
            textView = itemView.findViewById(R.id.tvnombreArchivo)
            textView2 = itemView.findViewById(R.id.tvalmacenamientoArchivo)
            textView3 = itemView.findViewById(R.id.tvfechaArchivo)
        }

        fun setData(imagen: Int, nombre: String?, almacenamiento: String?, fecha: String?) {
            imageView.setImageResource(imagen)
            textView.text = nombre
            textView2.text = almacenamiento
            textView3.text = fecha
        }
    }
}