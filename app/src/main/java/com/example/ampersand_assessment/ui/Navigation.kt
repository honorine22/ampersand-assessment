package com.example.ampersand_assessment.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.ampersand_assessment.ui.screens.DetailsView
import com.example.ampersand_assessment.ui.screens.ListView
import com.example.ampersand_assessment.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AppNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListView(viewModel = viewModel, navController = navController) // Pass the ViewModel
        }
        composable("details/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toInt() ?: 0
            // Fetch the item based on the itemId
            val item = viewModel.items.value.find { it.id == itemId } // You should fetch the item appropriately
            if (item != null) {
                DetailsView(item = item, onBackClick = { navController.popBackStack() })
            }
        }
    }
}
