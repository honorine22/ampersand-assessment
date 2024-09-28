package com.example.ampersand_assessment.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ampersand_assessment.model.Item
import com.example.ampersand_assessment.ui.theme.AmpersandassessmentTheme
import com.example.ampersand_assessment.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the passed itemId from the Intent
        val itemId = intent?.getIntExtra("itemId", 0) ?: 0

        setContent {
            AmpersandassessmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel: MainViewModel = viewModel()
                    val item by mainViewModel.getItemById(itemId).collectAsState(initial = null)
                    val isLoading by mainViewModel.loading.collectAsState()

                    // Display the DetailsView based on loading state and item
                    if (isLoading) {
                        LoadingView()
                    } else if (item != null) {
                        DetailsView(item = item!!)
                    } else {
                        ErrorView()
                    }
                }
            }
        }
    }
}

@Composable
fun DetailsView(item: Item) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = item.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.body, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Item not found",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}
