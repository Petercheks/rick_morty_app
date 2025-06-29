package com.example.rick_morty_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.example.rick_morty_app.databinding.ActivityDetailBinding
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity() {
    private lateinit var imagenPersonaje: CircleImageView
    private lateinit var nombre: TextView
    private lateinit var status: TextView
    private lateinit var especie: TextView
    private lateinit var origen: TextView
    private lateinit var ubicacion: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imagenPersonaje = findViewById(R.id.detailImageViewPersonaje)
        nombre = findViewById(R.id.detailTextViewNombre)
        status = findViewById(R.id.detailTextViewStatus)
        especie = findViewById(R.id.detailTextViewSpecies)
        origen = findViewById(R.id.detailTextViewOrigin)
        ubicacion = findViewById(R.id.detailTextViewLocation)

        val bundle = intent.extras

        val nombreIntent = bundle?.getString("nombre", "")
        val statusIntent = bundle?.getString("status", "")
        val especieIntent = bundle?.getString("species", "")
        val origenIntent = bundle?.getString("origen_name", "")
        val ubicacionIntent = bundle?.getString("ubicacion_name", "")
        val imagenPersonajeIntent = bundle?.getString("imagen_personaje", "")

        nombre.text = nombreIntent
        status.text = "Estatus: $statusIntent"
        especie.text = "Especie: $especieIntent"
        origen.text = "Origen: $origenIntent"
        ubicacion.text = "Ubicacion actual: $ubicacionIntent"

        Glide.with(applicationContext)
            .load(imagenPersonajeIntent)
            .into(imagenPersonaje)
    }
}