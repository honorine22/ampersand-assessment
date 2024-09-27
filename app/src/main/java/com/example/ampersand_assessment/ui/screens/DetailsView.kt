package com.example.ampersand_assessment.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ampersand_assessment.model.Item

@Composable
fun DetailsView(item: Item) {
    BasicText(
        text = "Title: ${item.title}\nDescription: ${item.description}",
        modifier = Modifier.fillMaxSize()
    )
}
