package com.example.ampersand_assessment.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ampersand_assessment.model.Item
import com.example.ampersand_assessment.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ListView(viewModel: MainViewModel = viewModel()) {
    var items by remember { mutableStateOf(listOf<Item>()) }

    // Fetch items
    LaunchedEffect(Unit) {
        viewModel.getItems().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    items = response.body() ?: listOf()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { item ->
            Text(
                text = item.title,
                modifier = Modifier.clickable {
                    // Navigate to DetailView with item.id
                }
            )
        }
    }
}