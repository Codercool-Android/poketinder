package com.abeltarazona.poketinder.presentation.utils

import com.abeltarazona.poketinder.data.PokemonMock

class Mock {

    fun getPokemons() = listOf(
        PokemonMock(
            name = "Pikachu",
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png"
        ),
        PokemonMock(
            name = "Raichu",
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/026.png"
        ),
        PokemonMock(
            name = "Rayquaza",
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/384.png",
            legendary = true
        ),
        PokemonMock(
            name = "Hariyama",
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/297.png"
        ),
        PokemonMock(
            name = "Chikorita",
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/152.png"
        ),
        PokemonMock(
            name = "Arceus",
            img = "https://static.wikia.nocookie.net/espokemon/images/9/96/Arceus.png/revision/latest?cb=20150601113031",
            legendary = true
        ),

    )

    fun getOnboarding() = listOf(
        Onboarding(
            id = 1,
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png",
            namePokemon = "Squirtle",
            title = "Desliza hacia la derecha para acceder a la información de un Pokemón"
        ),
        Onboarding(
            id = 2,
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/150.png",
            namePokemon = "Mewtoo",
            title = "Guarda su información en tu teléfono tapeando sobre la Pokebola"
        ),
        Onboarding(
            id = 3,
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png",
            namePokemon = "Pikachu",
            title = "Desliza hacia la izquierda en caso desees ver otros Pokemones"
        )
    )

}

data class Onboarding(
    val id: Int,
    val img: String,
    val namePokemon: String,
    val title: String
)