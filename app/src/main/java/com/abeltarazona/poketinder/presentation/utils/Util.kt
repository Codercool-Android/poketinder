package com.abeltarazona.poketinder.presentation.utils

/**
 * Created by AbelTarazona on 17/08/2021
 */

fun formatNumberTo3Digits(number: Int): String = "#${"%03d".format(number)}"

fun getIdPokemonFromUrl(url: String) : String = url.split("/").toTypedArray()[6]