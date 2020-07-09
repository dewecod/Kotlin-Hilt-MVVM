package com.dewecod.mvvmhilt.repository

import com.dewecod.mvvmhilt.db.PokeDao
import com.dewecod.mvvmhilt.net.ApiService
import com.dewecod.mvvmhilt.response.PokemonResponse
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(
    private val pokeDao: PokeDao,
    private val apiService: ApiService
) {
    fun getPokemons(): Observable<PokemonResponse> = apiService.getPokemons()
}