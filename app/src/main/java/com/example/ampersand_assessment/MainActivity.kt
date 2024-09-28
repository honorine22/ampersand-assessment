package com.example.ampersand_assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.ampersand_assessment.ui.AppNavHost
import com.example.ampersand_assessment.ui.theme.AmpersandassessmentTheme
import com.example.ampersand_assessment.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmpersandassessmentTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val viewModel: MainViewModel = remember { MainViewModel() }
                    // Pass the navController and viewModel to the AppNavHost
                    AppNavHost(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}