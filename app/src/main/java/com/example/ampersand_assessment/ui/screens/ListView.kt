package com.example.ampersand_assessment.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ampersand_assessment.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListView(viewModel: MainViewModel = viewModel(), navController: NavHostController) {
    val items = viewModel.items.collectAsState(initial = emptyList())
    val errorMessage = viewModel.errorMessage.collectAsState(initial = null)

    // Fetch items when the ListView is first composed
    LaunchedEffect(Unit) {
        viewModel.fetchItems()
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Items") })
    }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            errorMessage.value?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items.value) { item ->
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("details/${item.id}") // Navigate to DetailsView with item ID
                            },
                        headlineContent = { Text(item.title) },
                        supportingContent = { Text(item.description) }
                    )
                }
            }
        }
    }
}