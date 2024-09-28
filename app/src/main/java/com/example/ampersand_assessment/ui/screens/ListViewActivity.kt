package com.example.ampersand_assessment.ui.screens

import android.content.Intent
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.items.observe(this) { items ->
            itemAdapter = ItemAdapter(items) { item ->
                // Handle item click
                val intent = Intent(this, DetailsActivity::class.java).apply {
                    putExtra("ITEM_ID", item.id)
                }
                startActivity(intent)
            }
            recyclerView.adapter = itemAdapter
        }
    }
}
