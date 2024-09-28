package com.example.ampersand_assessment.network
import com.example.ampersand_assessment.model.Item
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getItems(): List<Item>

    // Add this new method
    @GET("posts/{id}")  // Adjust the endpoint according to your API
    suspend fun getItemById(@Path("id") id: Int): Item
}
