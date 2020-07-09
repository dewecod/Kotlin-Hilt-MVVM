package com.dewecod.mvvmhilt.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dewecod.mvvmhilt.model.Pokemon

@Dao
interface PokeDao {
    @Insert
    fun insertPokemon(pokemon: Pokemon?)

    @Query("DELETE FROM pokemon WHERE name = :pokemonName")
    fun deletePokemon(pokemonName: String?)

    @Query("DELETE FROM pokemon")
    fun deleteAll()

    @Query("SELECT * FROM pokemon")
    fun getFavoritePokemons(): LiveData<List<Pokemon>>
}