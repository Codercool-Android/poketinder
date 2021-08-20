package com.abeltarazona.poketinder.data.core

import com.abeltarazona.poketinder.data.core.response.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by AbelTarazona on 19/08/2021
 */
interface PokemonApi {

    @GET("?limit=20")
    fun getPokemons() : Call<PokemonListResponse>

}