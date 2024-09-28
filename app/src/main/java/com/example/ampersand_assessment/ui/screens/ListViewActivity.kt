package com.example.ampersand_assessment.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.ampersand_assessment.R
import com.example.ampersand_assessment.viewmodel.MainViewModel

class ListViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.items.observe(this) { items ->
            if (items.isNotEmpty()) {
                recyclerView.adapter = ItemAdapter(items) { item ->
                    // Navigate to details
                    val intent = Intent(this, DetailsActivity::class.java)
                    intent.putExtra("itemId", item.id)
                    startActivity(intent)
                }
            }
        }
    }
}
