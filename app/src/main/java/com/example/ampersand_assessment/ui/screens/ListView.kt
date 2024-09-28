package com.example.ampersand_assessment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.ampersand_assessment.model.Item
import com.example.ampersand_assessment.ui.theme.AccentColor
import com.example.ampersand_assessment.ui.theme.LightBackground
import com.example.ampersand_assessment.ui.theme.TextPrimary

@Composable
fun ListView(
    items: List<Item>,
    navController: NavController,
    isLoading: Boolean,
    errorMessage: String?
) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBackground)
                .padding(0.dp) // Remove any unnecessary padding
                .systemBarsPadding()
        ) {
            when {
                isLoading -> {
                    // Show a loading spinner
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage != null -> {
                    // Show the error message
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
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
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "No items found")
                    }
                }

                else -> {
                    // Show the list of items once data is loaded
                    LazyColumn {
                        items(items.size) { index ->
                            val item = items[index]

                            ListItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("details/${item.id}")
                                    }
                                    .background(AccentColor)
                                    .padding(12.dp),
                                headlineContent = {
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        color = TextPrimary
                                    )
                                },
                                supportingContent = {
                                    Column(
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Bottom,
                                        modifier = Modifier.padding(0.dp, 10.dp)
                                    ) {                                        Text(
                                            text = item.body,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.Black
                                        )
                                        IconButton(onClick = { navController.navigate("details/${item.id}") }) {
                                            Icon(
                                                Icons.Default.ArrowForward,
                                                tint = TextPrimary,
                                                contentDescription = "View Details"
                                            )
                                        }
                                    }

                                }
                            )
                        }
                    }
                }
            }
        }
}