package com.example.cnn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botton_menu_main = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)
        botton_menu_main.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_top_news -> {
                    // Lógica para manejar la selección de Home
                    true
                }
                R.id.menu_world -> {
                    // Lógica para manejar la selección de Search
                    true
                }
                R.id.menu_politics -> {
                    // Lógica para manejar la selección de Settings
                    true
                }
                else -> false
            }
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    // Lógica para manejar la selección de Home
                    true
                }
                R.id.menu_search -> {
                    // Lógica para manejar la selección de Search
                    true
                }
                R.id.menu_settings -> {
                    // Lógica para manejar la selección de Settings
                    true
                }
                else -> false
            }
        }

        var listaNews = arrayListOf<News>()
        listaNews.add(News(R.drawable.mackarti,"MCCarthy unleashes a new political twist","9h ago"))
        listaNews.add(News(R.drawable.generacionz,"Gen z's mental health struggles are different","9h ago"))
        listaNews.add(News(R.drawable.new2,"Movies you could not watcha aganin","1h ago",))
        listaNews.add(News(R.drawable.new1,"Mental issues with old people","3h ago",))

        val recyclerView = findViewById<RecyclerView>(R.id.rvDirectorio)
        initRecyclerView(listaNews, recyclerView)
    }

    private fun initRecyclerView(listaNews: ArrayList<News>, recyclerView: RecyclerView) {
        val adapter = AdapterNews(listaNews,this,recyclerView)
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
        adapter.onItemClick = {
            val intent = Intent(this, DetailsActivities::class.java)
            startActivity(intent)
        }
    }
}