package com.abeltarazona.poketinder.data

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
                .baseUrl("GlobalConstants.UrlBitel.URL_RETROFIT")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        /*fun getCategoryProducts(): CategoryStoresResponse? {
            initIfNull()
            val api: MarketApi = retrofit!!.create(MarketApi::class.java)
            val res: Response<CategoryStoresResponse> = api.callGetCategoriesProducts().execute()
            return res.body()
        }*/
    }

}