package com.seu.filmeflix.data.repository

import com.seu.filmeflix.data.local.FilmeDao
import com.seu.filmeflix.data.remote.FilmeApiService
import com.seu.filmeflix.model.Filme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmeRepository(
    private val api: FilmeApiService,
    private val dao: FilmeDao
) {
    suspend fun obterFilmes(): List<Filme> = withContext(Dispatchers.IO) {
        val locais = dao.buscarTodos()
        if (locais.isNotEmpty()) {
            println("[REPO] Carregando do Cache Local...")
            return@withContext locais
        } else {
            try {
                println("[REPO] Buscando na API...")
                val remotos = api.listarFilmes()
                dao.salvarFilmes(remotos)
                return@withContext remotos
            } catch (e: Exception) {
                println("Erro de conexão: ${e.message}")
                return@withContext emptyList()
            }
        }
    }
}
