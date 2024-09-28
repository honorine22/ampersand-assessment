package com.example.ampersand_assessment.network

import android.util.Log
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
        return try {
            apiService.getItems() // Make sure this method returns a valid list
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching items: ${e.message}")
            emptyList()  // Return an empty list in case of an error
        }
    }


    // Implement the method to fetch a single item by ID
    suspend fun getItemById(id: Int): Item? {
        return try {
            apiService.getItemById(id) // Fetch the item from the API
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching item by ID: ${e.message}")
            null  // Return null in case of an error
        }
    }
}