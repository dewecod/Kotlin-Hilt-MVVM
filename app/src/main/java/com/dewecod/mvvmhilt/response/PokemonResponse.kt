package com.dewecod.mvvmhilt.response

import com.dewecod.mvvmhilt.model.Pokemon

class PokemonResponse(
    var count: Int,
    var next: String,
    var previous: String,
    var results: ArrayList<Pokemon>
)