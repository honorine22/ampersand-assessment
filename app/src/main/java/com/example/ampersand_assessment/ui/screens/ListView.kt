package com.example.ampersand_assessment.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ampersand_assessment.model.Item

@Composable
fun ListView(
    items: List<Item>,
    navController: NavController,
    isLoading: Boolean,
    errorMessage: String?
) {

    when {
        isLoading -> {
            // Show a loading spinner
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        errorMessage != null -> {
            // Show the error message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = errorMessage ?: "An unknown error occurred",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        items.isEmpty() -> {
            // Show a message if the items list is empty or null
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No items found")
            }
        }
        else -> {
            // Show the list of items once data is loaded
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(items.size) { index ->
                    val item = items[index]

                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Navigate to details screen with item ID
                                navController.navigate("details/${item.id}")
                            }
                            .padding(vertical = 8.dp),
                        headlineContent = { Text(item.title) },
                        supportingContent = { Text(item.body) }
                    )
                    HorizontalDivider() // Separate items with a divider
                }
            }
        }
    }
}