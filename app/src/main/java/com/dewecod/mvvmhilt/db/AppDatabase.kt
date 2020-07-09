package com.dewecod.mvvmhilt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dewecod.mvvmhilt.model.Pokemon

@Database(entities = [(Pokemon::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokeDao(): PokeDao
}