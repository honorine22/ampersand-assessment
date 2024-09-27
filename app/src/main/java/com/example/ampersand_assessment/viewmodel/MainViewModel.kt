package com.example.ampersand_assessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ampersand_assessment.network.Repository
import com.example.ampersand_assessment.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {
    private val repository = Repository()

    // StateFlow to hold the list of items
    private val _items = MutableStateFlow<List<Item>>(emptyList()) // Use List<Item> here
    val items: StateFlow<List<Item>> get() = _items

    // StateFlow to hold error messages
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    // Function to fetch items
    fun fetchItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchedItems = repository.getItems() // This should now return List<Item>
                withContext(Dispatchers.Main) {
                    _items.value = fetchedItems // Update UI state
                    _errorMessage.value = null // Clear previous error messages
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = "Network error. Please check your connection."
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = "Error fetching data: ${e.message()}"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = "An unexpected error occurred."
                }
            }
        }
    }
}
