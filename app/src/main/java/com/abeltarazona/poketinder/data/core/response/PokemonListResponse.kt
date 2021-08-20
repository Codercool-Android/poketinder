package com.abeltarazona.poketinder.data.core.response

import com.abeltarazona.poketinder.presentation.utils.getIdPokemonFromUrl

/**
 * Created by AbelTarazona on 19/08/2021
 */
data class PokemonListResponse(

    val count: Int,

    val results: List<PokemonResult>

) {

    data class PokemonResult(
        val name: String,
        val url: String
    ) {
        fun getPokemonImage(): String {
            val id = getIdPokemonFromUrl(url)
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
        }
    }

}

