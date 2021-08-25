package com.abeltarazona.poketinder.data.core.response

import com.abeltarazona.poketinder.presentation.utils.getIdPokemonFromUrl
import java.io.Serializable

/**
 * Created by AbelTarazona on 19/08/2021
 */
data class PokemonListResponse(

    val count: Int,

    val results: List<Pokemon>

) {

    data class Pokemon(
        val name: String,
        val url: String
    ) : Serializable {
        fun getPokemonId() = getIdPokemonFromUrl(url)

        fun getPokemonImage(): String =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${getPokemonId()}.png"
    }

}

