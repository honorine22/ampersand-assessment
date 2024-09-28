package com.example.ampersand_assessment.ui.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.ampersand_assessment.R
import com.example.ampersand_assessment.viewmodel.MainViewModel

class DetailsActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        titleTextView = findViewById(R.id.item_title)
        descriptionTextView = findViewById(R.id.item_description)

        val itemId = intent.getIntExtra("ITEM_ID", -1)

        // Observe item details
        viewModel.getItemById(itemId).observe(this) { item ->
            item?.let {
                titleTextView.text = it.title
                descriptionTextView.text = it.body
            }
        }
    }
}
