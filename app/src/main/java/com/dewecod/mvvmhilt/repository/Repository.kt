package com.dewecod.mvvmhilt.repository

import androidx.lifecycle.LiveData
import com.dewecod.mvvmhilt.db.PokeDao
import com.dewecod.mvvmhilt.model.Pokemon
import com.dewecod.mvvmhilt.net.ApiService
import com.dewecod.mvvmhilt.response.PokemonResponse
import io.reactivex.Observable
import javax.inject.Inject


class Repository @Inject constructor(
    private val pokeDao: PokeDao,
    private val apiService: ApiService
) {
    fun getPokemons(): Observable<PokemonResponse> = apiService.getPokemons()
    fun insertPokemon(pokemon: Pokemon) = pokeDao.insertPokemon(pokemon)
    fun deletePokemon(pokemonName: String) = pokeDao.deletePokemon(pokemonName)
    fun deleteAll() = pokeDao.deleteAll()
    fun getFavoritePokemon(): LiveData<List<Pokemon>> = pokeDao.getFavoritePokemons()
}