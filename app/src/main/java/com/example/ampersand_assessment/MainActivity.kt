package com.example.ampersand_assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    // Set up the Navigation Controller
                    val navController = rememberNavController()
                    // Create an instance of MainViewModel
                    val viewModel: MainViewModel = remember { MainViewModel() }
                    // Pass the navController and viewModel to your NavHost
                    AppNavHost(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}