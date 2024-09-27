package com.example.ampersand_assessment.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.ampersand_assessment.ui.screens.DetailsView
import com.example.ampersand_assessment.ui.screens.ListView
import com.example.ampersand_assessment.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavHost(navController: NavHostController, viewModel: MainViewModel) {
    // Create a navigation host
    NavHost(navController = navController, startDestination = "list") {
        // List screen
        composable("list") {
            val items = viewModel.items.collectAsState().value
            val isLoading = viewModel.loading.collectAsState().value
            val errorMessage = viewModel.errorMessage.collectAsState().value

            // Pass the items to the ListView with navigation
            ListView(
                items = items,
                navController = navController,
                isLoading = isLoading,
                errorMessage = errorMessage
            )
        }

        // Details screen
        composable("details/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull() ?: 0
            val item = viewModel.items.collectAsState().value.find { it.id == itemId }

            // Navigate to DetailsView with proper null handling
            if (item != null) {
                DetailsView(item = item, onBackClick = { navController.popBackStack() })
            }
        }
    }
}
