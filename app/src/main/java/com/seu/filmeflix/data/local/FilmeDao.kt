package com.seu.filmeflix.data.local

import androidx.room.*
import com.seu.filmeflix.model.Filme

@Dao
interface FilmeDao {
    @Query("SELECT * FROM filmes")
    suspend fun buscarTodos(): List<Filme>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvarFilmes(filmes: List<Filme>)
}
