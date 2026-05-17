package com.seu.filmeflix.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.seu.filmeflix.data.repository.FilmeRepository
import com.seu.filmeflix.model.Filme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmeViewModel(private val repository: FilmeRepository) : ViewModel() {

    private val _filmes = MutableStateFlow<List<Filme>>(emptyList())
    val filmes: StateFlow<List<Filme>> = _filmes

    init {
        carregarFilmes()
    }

    fun carregarFilmes() {
        viewModelScope.launch {
            val lista = repository.obterFilmes()
            _filmes.value = lista
        }
    }
}

class FilmeViewModelFactory(private val repository: FilmeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FilmeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
