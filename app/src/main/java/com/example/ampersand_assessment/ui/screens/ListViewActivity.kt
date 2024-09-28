package com.example.ampersand_assessment.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ampersand_assessment.R
import com.example.ampersand_assessment.viewmodel.MainViewModel

class ListViewActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var errorMessageTextView: TextView
    private lateinit var loadingIndicator: View

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recycler_view)
        errorMessageTextView = findViewById(R.id.error_message) // Add this to your layout
        loadingIndicator = findViewById(R.id.loading_indicator) // Add this to your layout

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe the items and loading state from the ViewModel
        viewModel.items.observe(this) { items ->
            loadingIndicator.visibility = View.GONE // Hide loading indicator
            if (items.isNotEmpty()) {
                itemAdapter = ItemAdapter(items) { item ->
                    // Handle item click
                    val intent = Intent(this, DetailsActivity::class.java).apply {
                        putExtra("ITEM_ID", item.id)
                    }
                    startActivity(intent)
                }
                recyclerView.adapter = itemAdapter
                recyclerView.visibility = View.VISIBLE
                errorMessageTextView.visibility = View.GONE
            } else {
                // Show error message if no items are found
                recyclerView.visibility = View.GONE
                errorMessageTextView.visibility = View.VISIBLE
                errorMessageTextView.text = "No items found. Please try again later."
            }
        }

        // Observe loading state to show/hide loading indicator
        viewModel.loading.observe(this) { isLoading ->
            loadingIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe error message to show if there is an error
        viewModel.errorMessage.observe(this) { errorMessage ->
            if (errorMessage != null) {
                loadingIndicator.visibility = View.GONE
                recyclerView.visibility = View.GONE
                errorMessageTextView.visibility = View.VISIBLE
                errorMessageTextView.text = errorMessage
            }
        }
    }
}
