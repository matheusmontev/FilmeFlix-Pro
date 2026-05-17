package com.seu.filmeflix.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "filmes")
data class Filme(
    @PrimaryKey val id: String,
    @SerializedName("original_title") val titulo: String,
    @SerializedName("overview") val sinopse: String,
    @SerializedName("vote_average") val nota: Double
)
