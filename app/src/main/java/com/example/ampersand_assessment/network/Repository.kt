package com.example.ampersand_assessment.network

import com.example.ampersand_assessment.model.Item
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call

class Repository {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getItems(): List<Item> {
        // Make a network call and return the list of items
        return apiService.getItems() // Ensure that your ApiService returns a List<Item>
    }
}