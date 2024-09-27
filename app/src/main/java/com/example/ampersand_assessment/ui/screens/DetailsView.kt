package com.example.ampersand_assessment.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ampersand_assessment.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(item: Item, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = item.title ?: "No Title Available",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = item.body,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}
