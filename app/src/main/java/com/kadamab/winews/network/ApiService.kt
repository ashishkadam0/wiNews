package com.kadamab.winews.network

import com.kadamab.winews.model.NewsResponse
import retrofit2.http.GET

/**
 * Repository abstracts the logic of fetching the data
 */
interface ApiService {

    @GET("2iodh4vg0eortkl/facts.json")
    suspend fun getNewss(): NewsResponse

}