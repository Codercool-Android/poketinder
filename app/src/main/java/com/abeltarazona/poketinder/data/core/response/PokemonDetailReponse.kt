package com.abeltarazona.poketinder.data.core.response

/**
 * Created by AbelTarazona on 24/08/2021
 */
data class PokemonDetailResponse(
    val base_experience: Int,
    val height: Int,
    val weight: Int,
    val stats: List<Stats>,
    val types: List<Types>,
    val abilities: List<Abilities>
) {
    data class Stats(
        val base_stat: Int,
        val stat: Stat
    ) {
        data class Stat(
            val name: String
        )
    }

    data class Types(
        val type: Type
    ) {
        data class Type(
            val name: String
        )
    }

    data class Abilities(
        val ability: Ability
    ) {
        data class Ability(
            val name: String
        )
    }
}