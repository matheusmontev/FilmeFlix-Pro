package com.seu.filmeflix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seu.filmeflix.model.Filme

@Database(entities = [Filme::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmeDao(): FilmeDao
}
