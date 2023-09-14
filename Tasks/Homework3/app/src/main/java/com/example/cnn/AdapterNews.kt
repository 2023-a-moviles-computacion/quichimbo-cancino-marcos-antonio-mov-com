package com.example.cnn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterNews(
    private val listNews: List<News>,
    private val contexto: MainActivity,
    private val recycler: RecyclerView

) : RecyclerView.Adapter<AdapterNews.ViewHolder>() {

    var onItemClick : ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_details_deep, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagen = listNews[position].imagenNoticiaHome
        val nombre = listNews[position].nombreNoticiaHome
        val peso = listNews[position].hourAgoHome
        holder.setData(imagen, nombre, peso)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(listNews[position])
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView
        private val textView: TextView
        private val textView2: TextView


        init {
            imageView = itemView.findViewById(R.id.imDirectorio)
            textView = itemView.findViewById(R.id.tvnombreDirectorio)
            textView2 = itemView.findViewById(R.id.tvalmacenamientoDirectorio)

        }

        fun setData(imagen: Int, nombre: String?, peso: String?) {
            imageView.setImageResource(imagen)
            textView.text = nombre
            textView2.text = peso

        }
    }
}