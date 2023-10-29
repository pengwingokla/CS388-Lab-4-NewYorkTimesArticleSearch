package com.example.lab_4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("articlesearch.json")
    fun getArticles(
        @Query("q") query: String, // Search query, e.g., "election"
        @Query("api-key") apiKey: String
    ): Call<ApiResponse>
}