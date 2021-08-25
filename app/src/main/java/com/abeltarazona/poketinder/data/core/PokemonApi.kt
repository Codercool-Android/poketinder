package com.abeltarazona.poketinder.data.core

import com.abeltarazona.poketinder.data.core.response.PokemonDetailResponse
import com.abeltarazona.poketinder.data.core.response.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by AbelTarazona on 19/08/2021
 */
interface PokemonApi {

    @GET("?limit=20")
    fun getPokemons() : Call<PokemonListResponse>

    @GET("{id}")
    fun getPokemonDetail(@Path("id") id: String) : Call<PokemonDetailResponse>

}