package com.kadamab.winews.metwork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository abstracts the logic of fetching the data
 */
object RetrofitBuilder {

    private const val BASE_URL = "https://dl.dropboxusercontent.com/s/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}