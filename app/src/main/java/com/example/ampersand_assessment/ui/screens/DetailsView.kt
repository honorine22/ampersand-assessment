package com.example.ampersand_assessment.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ampersand_assessment.model.Item
import com.example.ampersand_assessment.ui.theme.AccentColor
import com.example.ampersand_assessment.ui.theme.TextPrimary


@Composable
fun DetailsView(item: Item, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.body,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )

        Button(onClick = onBackClick) {
            Text("Back")
        }
    }
}
