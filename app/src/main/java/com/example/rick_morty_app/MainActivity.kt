package com.example.rick_morty_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var buttonTodos: Button
    private lateinit var buttonHumanos: Button
    private lateinit var buttonAliens: Button
    private var personajesList = mutableListOf<Personaje>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTodos = findViewById(R.id.buttonAll)
        buttonHumanos = findViewById(R.id.buttonHumanos)
        buttonAliens = findViewById(R.id.buttonAliens)

        buttonTodos.setOnClickListener {
            getPersonajes()
        }

        buttonHumanos.setOnClickListener {
            getPersonajes(species = "humans")
        }

        buttonAliens.setOnClickListener {
            getPersonajes(species = "aliens")
        }

        recyclerView = findViewById(R.id.recyclerViewPersonajes)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(personajesList)
        recyclerView.adapter = adapter

        adapter.onItemClickListener = { personaje ->
            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("nombre", personaje.name)
            intent.putExtra("imagen_personaje", personaje.image)
            intent.putExtra("species", personaje.species)
            intent.putExtra("status", personaje.status)
            intent.putExtra("origen_name", personaje.origin.name)
            intent.putExtra("ubicacion_name", personaje.location.name)

            startActivity(intent)
        }

        getPersonajes()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getPersonajes(species: String? = null) {
        val path = when (species) {
            "humans" -> HUMANOS
            "aliens" -> ALIENS
            else -> TODOS
        }

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getPersonajes(path)
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    personajesList.clear()

                    val personajes = response?.results
                    personajes?.forEach {
                        personajesList.add(it)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val TODOS = "character"
        const val HUMANOS = "character?species=Human"
        const val ALIENS = "character?species=Alien"
    }
}