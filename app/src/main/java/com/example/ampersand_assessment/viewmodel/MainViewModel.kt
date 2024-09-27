package com.example.ampersand_assessment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ampersand_assessment.network.Repository
import com.example.ampersand_assessment.model.Item
import retrofit2.Call

class MainViewModel : ViewModel() {
    private val repository = Repository()

    fun getItems(): Call<List<Item>> {
        return repository.getItems()
    }
}
