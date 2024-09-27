package com.example.ampersand_assessment.network
import com.example.ampersand_assessment.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getItems(): List<Item>
}
