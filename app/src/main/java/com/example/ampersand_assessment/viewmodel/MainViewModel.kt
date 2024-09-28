package com.example.ampersand_assessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ampersand_assessment.network.Repository
import com.example.ampersand_assessment.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = Repository()

    // Change _items to LiveData
    private val _items = MutableLiveData<List<Item>>(emptyList())
    val items: LiveData<List<Item>> get() = _items

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading


    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val fetchedItems = repository.getItems()
                _items.value = fetchedItems
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching data: ${e.message}"
                _items.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }

    // Assuming you have a method to get an item by ID
    fun getItemById(id: Int): LiveData<Item?> {
        val liveData = MutableLiveData<Item?>()
        viewModelScope.launch {
            val fetchedItem = repository.getItemById(id) // Implement this in your repository
            liveData.postValue(fetchedItem)
        }
        return liveData
    }
}
