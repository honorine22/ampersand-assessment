package com.example.ampersand_assessment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ampersand_assessment.network.Repository
import com.example.ampersand_assessment.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = Repository()
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> get() = _items

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.value = true
                val fetchedItems = repository.getItems() // Make sure this returns List<Item>
                _items.value = fetchedItems
                _errorMessage.value = null

                // Debugging log to check the fetched data
                Log.d("MainViewModel", "Fetched items: ${fetchedItems.size}")
            } catch (e: Exception) {
                // Handle exception
                _items.value = emptyList()
                _errorMessage.value = e.message ?: "An error occurred"

                // Debugging log for error message
                Log.e("MainViewModel", "Error fetching data: ${e.message}")
            } finally {
                _loading.value = false // Stop loading
            }

        }
    }
}
