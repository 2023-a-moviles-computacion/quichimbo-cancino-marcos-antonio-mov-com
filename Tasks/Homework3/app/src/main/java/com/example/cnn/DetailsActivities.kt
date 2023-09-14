package com.example.cnn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class DetailsActivities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var listaArchivos = arrayListOf<Details>()
        listaArchivos.add(Details(R.drawable.makarti2,"Biden impeachment saga creates a wsts it is not.", "Written by:Salome", "apital Khartoum have reached their capacity,rganizati"))
        listaArchivos.add(Details(R.drawable.noticia2,"Ukraine war","Written by: Marcos Q","capital Khartoum have reached their capacity,rganizations warn of a looming cholera outbreak"))
        listaArchivos.add(Details(R.drawable.noticia3,"Poland bracing itself for"," Written by:Juanl Bvleskes","Khartoum have reached their capacity, aid workers say, le"))

        val recyclerView = findViewById<RecyclerView>(R.id.rvArchivo)
        initRecyclerView(listaArchivos, recyclerView)
    }

    private fun initRecyclerView(listaArchivos: ArrayList<Details>, recyclerView: RecyclerView) {
        val adapter = AdapterDetails(listaArchivos,this,recyclerView)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
}