package com.seu.filmeflix.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seu.filmeflix.R
import com.seu.filmeflix.model.Filme

class FilmeAdapter : ListAdapter<Filme, FilmeAdapter.FilmeViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
        private val tvSinopse = itemView.findViewById<TextView>(R.id.tvSinopse)
        private val tvNota = itemView.findViewById<TextView>(R.id.tvNota)

        fun bind(filme: Filme) {
            tvTitulo.text = filme.titulo
            tvSinopse.text = filme.sinopse
            tvNota.text = "Nota: ${filme.nota}"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Filme>() {
        override fun areItemsTheSame(oldItem: Filme, newItem: Filme) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Filme, newItem: Filme) = oldItem == newItem
    }
}
