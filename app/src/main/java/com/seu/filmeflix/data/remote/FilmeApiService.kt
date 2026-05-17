package com.seu.filmeflix.data.remote

import com.seu.filmeflix.model.Filme
import retrofit2.http.GET

interface FilmeApiService {
    @GET("movies-list")
    suspend fun listarFilmes(): List<Filme>
}
