package com.dewecod.mvvmhilt.net

import com.dewecod.mvvmhilt.response.PokemonResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon")
    fun getPokemons() : Observable<PokemonResponse>

}