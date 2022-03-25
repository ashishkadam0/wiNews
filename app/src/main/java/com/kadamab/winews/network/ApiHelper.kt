
package com.kadamab.winews.network

class ApiHelper(private val apiService: ApiService) {
   // get News dfrom ApiService
    suspend fun getNews() = apiService.getNewss()
}