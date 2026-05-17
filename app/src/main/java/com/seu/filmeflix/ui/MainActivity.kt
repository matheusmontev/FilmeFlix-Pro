package com.seu.filmeflix.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seu.filmeflix.R
import com.seu.filmeflix.data.local.AppDatabase
import com.seu.filmeflix.data.remote.RetrofitClient
import com.seu.filmeflix.data.repository.FilmeRepository
import com.seu.filmeflix.viewmodel.FilmeViewModel
import com.seu.filmeflix.viewmodel.FilmeViewModelFactory
import kotlinx.coroutines.launch
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "filmes-db"
        ).build()
    }

    private val repository by lazy {
        FilmeRepository(RetrofitClient.apiService, db.filmeDao())
    }

    private val viewModel: FilmeViewModel by viewModels {
        FilmeViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFilmes)
        val adapter = FilmeAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.filmes.collect { lista ->
                adapter.submitList(lista)
            }
        }
    }
}
