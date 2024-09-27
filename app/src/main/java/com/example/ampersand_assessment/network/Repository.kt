package com.example.ampersand_assessment.network

import com.example.ampersand_assessment.model.Item
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call

class Repository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://your_api_base_url/") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getItems(): Call<List<Item>> {
        return apiService.getItems()
    }
}