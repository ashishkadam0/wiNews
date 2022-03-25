package com.kadamab.winews.repository

import com.kadamab.winews.network.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getNews() = apiHelper.getNews()
}