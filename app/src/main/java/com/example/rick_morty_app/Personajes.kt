package com.example.rick_morty_app


data class Personajes (
    val results: List<Personaje>
)

data class Personaje(
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String
)

data class Origin(
    val name: String,
    val url: String
)

data class Location(
    val name: String,
    val url: String
)