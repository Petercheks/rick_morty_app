package com.example.rick_morty_app

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getPersonajes(@Url url: String): Response<Personajes>
}