package com.example.rick_morty_app

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class Adapter(private val personajes: List<Personaje>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var onItemClickListener: (Personaje) -> Unit

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imagenPersonaje: CircleImageView = view.findViewById(R.id.imageViewImagenPersonaje)
        private val imagenGenero: ImageView = view.findViewById(R.id.imageViewGenero)
        private val nombre: TextView = view.findViewById(R.id.textViewNombre)
        private val status: TextView = view.findViewById(R.id.textViewStatus)
        private val especie: TextView = view.findViewById(R.id.textViewEspecie)

        @SuppressLint("SetTextI18n")
        fun bind(personaje: Personaje) {
            nombre.text = personaje.name
            status.text = "Status: " + personaje.status
            especie.text = "Species: " + personaje.species

            Glide.with(view.context)
                .load(personaje.image)
                .into(imagenPersonaje)

            when (personaje.species) {
                "Human" -> imagenGenero.setImageResource(R.drawable.human)
                else -> imagenGenero.setImageResource(R.drawable.alien)
            }

            view.setOnClickListener {
                onItemClickListener(personaje)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_personaje, parent, false))
    }

    override fun getItemCount(): Int {
        return personajes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personaje = personajes[position]
        holder.bind(personaje)
    }
}