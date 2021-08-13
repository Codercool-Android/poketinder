package com.abeltarazona.poketinder.data

import java.io.Serializable

data class PokemonMock(
    val name: String,
    val img: String,
    val legendary: Boolean = false,
    val position: Int = -1
) : Serializable
