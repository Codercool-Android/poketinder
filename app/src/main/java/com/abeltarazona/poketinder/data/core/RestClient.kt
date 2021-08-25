package com.abeltarazona.poketinder.data.core

import com.abeltarazona.poketinder.data.core.response.PokemonDetailResponse
import com.abeltarazona.poketinder.data.core.response.PokemonListResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by AbelTarazona on 21/07/2021
 */
class RestClient {

    companion object {

        private var retrofit: Retrofit? = null

        private fun initIfNull() {
            if (retrofit == null) {
                init()
            }
        }

        private fun init() {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(interceptor).build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        fun getPokemonList() : PokemonListResponse? {
            initIfNull()
            val api: PokemonApi = retrofit!!.create(PokemonApi::class.java)
            val res: Response<PokemonListResponse> =
                api.getPokemons().execute()
            return res.body()
        }

        fun getPokemonDetail(id: String) : PokemonDetailResponse? {
            initIfNull()
            val api: PokemonApi = retrofit!!.create(PokemonApi::class.java)
            val res: Response<PokemonDetailResponse> =
                api.getPokemonDetail(id).execute()
            return res.body()
        }
    }

}