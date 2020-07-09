package com.dewecod.mvvmhilt.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dewecod.mvvmhilt.model.Pokemon
import com.dewecod.mvvmhilt.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PokemonViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    private val pokemonList = MutableLiveData<ArrayList<Pokemon>>()
    private var favoritePokemonList: LiveData<List<Pokemon>>? = null

    fun getPokemonList(): MutableLiveData<ArrayList<Pokemon>> = pokemonList

    @SuppressLint("CheckResult")
    fun loadPokemons() {
        repository.getPokemons()
            .subscribeOn(Schedulers.io())
            .map { response ->
                val list: ArrayList<Pokemon> = response.results
                for (position in response.results.indices) {
                    list[position].url =
                        "https://pokeres.bastionbot.org/images/pokemon/${position + 1}.png"
                }
                list
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                pokemonList.value = response
            }, {
                Log.e("PokemonViewModel", "loadPokemon: " + it.message)
            })
    }

    fun insertPokemon(pokemon: Pokemon?) {
        repository.insertPokemon(pokemon!!)
    }

    fun deletePokemon(pokemonName: String?) {
        repository.deletePokemon(pokemonName!!)
    }

    fun getFavoritePokemonList(): LiveData<List<Pokemon>>? = favoritePokemonList

    fun getFavoritePokemon() {
        favoritePokemonList = repository.getFavoritePokemon()
    }
}