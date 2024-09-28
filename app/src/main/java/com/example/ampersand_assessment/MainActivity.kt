package com.example.ampersand_assessment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.ampersand_assessment.ui.screens.ListViewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val intent = Intent(this, ListViewActivity::class.java)
            startActivity(intent)
    }
}